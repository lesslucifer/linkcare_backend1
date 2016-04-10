package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.ITimingsService;
import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.api.persistence.entity.AccountCustomTimingsEntity;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.repository.IAccountBlockTimeRepository;
import com.clinic.clinic.api.persistence.repository.IAccountCustomTimingsRepository;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAccountTimingsRepository;
import com.clinic.clinic.api.persistence.repository.IAppointmentBookingRepository;
import com.clinic.clinic.api.persistence.repository.ITimingsRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.AccountTimingsTranslator;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;
import com.clinic.clinic.common.dto.biz.TimingsDayDto;
import com.clinic.clinic.common.dto.biz.TimingsDto;
import com.clinic.clinic.common.dto.biz.TimingsSlotDto;

@ApplicationService
public final class TimingsServiceImpl extends AbsService implements ITimingsService {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimingsServiceImpl.class);

    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private IAccountTimingsRepository accTimingsRepo;
    @Autowired
    private ITimingsRepository timingsRepo;
    @Autowired
    private IAccountBlockTimeRepository accBlockTimeRepo;
    @Autowired
    private IAppointmentBookingRepository appointmentBookingRepo;
    @Autowired
    private IAccountCustomTimingsRepository accCustomTimingsRepo;

    private ITranslator<AccountTimingsDto, AccountTimingsEntity> accTimingsTrans = AccountTimingsTranslator.INSTANCE;

	@Override
	public AccountTimingsDto updateAccountTimings(Integer accountId, AccountTimingsDto dto) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        AccountTimingsDto retValue = null;
        try {
        	if (!isTimingsValid(dto.getTimings())) {
        		throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.TIMINGS_EMPTY, "Invalid! Empty timings");
        	}

        	sortTimings(dto.getTimings());
        	if (isTimingsConflict(dto.getTimings())) {
        		throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.TIMINGS_CONFLICT, "Timings Conflicted!");
        	}
        	
        	AccountEntity account = accountRepo.findFirstEntity(IDbConstants.FIELD_ID, accountId, true);
        	if (account == null) {
        		throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Unknown user", accountId);
        	}
        	
        	AccountTimingsEntity accTimings = accTimingsRepo.updateAccountTimings(account, dto);
        	// new Timin
        	// timingsRepo.sa
        	/*List<TimingsEntity> timings =*/ timingsRepo.insertTimings(accTimings, dto.getTimings());
        	
        	// Update appointments time

        	retValue = dto;
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        return retValue;
	}
	
	private void sortTimings(List<TimingsDto> timings) {
		timings.sort((t1, t2) -> Integer.compare(t1.getBeginTime(), t2.getBeginTime()));
	}
	
	private boolean isTimingsValid(List<TimingsDto> timings) {
		return !timings.isEmpty() && timings.stream().allMatch(t -> t.getLength() > 0);
	}

	private boolean isTimingsConflict(List<TimingsDto> sortedTimings) {
		for (int i = 1; i < sortedTimings.size(); ++i) {
			TimingsDto prev = sortedTimings.get(i - 1);
			TimingsDto cur = sortedTimings.get(i);
			
			if (prev.getEnd() >= cur.getBeginTime()) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public AccountTimingsDto getAccountTimings(Integer accountId) {
		AccountTimingsEntity ent = accTimingsRepo.getLastestAccountTimings(accountId);
		if (ent == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Cannot find doctor's timings", accountId);
		}
		
		return accTimingsTrans.getDto(ent);
	}
	
	@Override
	public List<TimingsDayDto> getTimingDaySlots(Integer accountId, String day, int range) {
		
		AccountTimingsEntity ent = accTimingsRepo.getLastestAccountTimings(accountId);
		ent.getTimings().size(); // get all timings

		final LocalDate startDay = LocalDate.parse(day, DateTimeFormatter.ofPattern(IConstants.DateForMat_DDMMYYYY));
		final LocalDate endDay = startDay.plusDays(range);
		
		final List<AccountBlockTimeEntity> blockTimes = accBlockTimeRepo.getBlockTime(accountId, startDay.atStartOfDay(), endDay.atStartOfDay());
		blockTimes.sort(AccountBlockTimeEntity::compareTo);
		
		final List<AppointmentBookingEntity> activeAppointments = appointmentBookingRepo.getActiveAppointments(accountId, startDay, endDay);
		activeAppointments.sort(AppointmentBookingEntity::compareTo);
		
		final List<AccountCustomTimingsEntity> listCustomTimings = accCustomTimingsRepo.getCustomTimings(accountId, startDay, endDay);
		final TreeMap<LocalDate, TreeMap<Integer, AccountCustomTimingsEntity>> customTimings = new TreeMap<>();
		listCustomTimings.forEach((ct) -> {
			TreeMap<Integer, AccountCustomTimingsEntity> subMap = customTimings.get(ct.getDate());
			if (subMap == null) {
				subMap = new TreeMap<>();
				customTimings.put(ct.getDate(), subMap);
			}
			
			subMap.put(ct.getBegin(), ct);
		});

		return IntStream.range(0, range).mapToObj(i -> {
			LocalDate date = startDay.plusDays(i);
			List<TimingsSlotDto> slots = ent.getTimings().stream().map(t -> {
				int nSlot = t.getLength() / IConstants.SLOT_TIME;
				return IntStream.range(0, nSlot).mapToObj(slotOffset -> {
					TimingsSlotDto slot = new TimingsSlotDto();
					slot.setTime(t.getBeginTime() + slotOffset * IConstants.SLOT_TIME);
					slot.setAvailable(isTimeAvailable(date, slot.getTime(), slot.getTime() + IConstants.SLOT_TIME,
							blockTimes, activeAppointments));
					slot.setType(t.getType());
					return slot;
				});
			}).flatMap(s -> s).collect(Collectors.toList());
			
			TimingsDayDto timingsDay = new TimingsDayDto();
			timingsDay.setDay(date);
			timingsDay.setSlots(slots);
			
			return timingsDay;
		}).collect(Collectors.toList());
	}
	
	@Override
	public boolean isTimeAvailable(LocalDate date, int begin, int end,
			final List<AccountBlockTimeEntity> blockTimes, 
			final List<AppointmentBookingEntity> activeAppointments) {

		boolean result = activeAppointments.stream().allMatch((ab) -> {
			if (ab.getDate().compareTo(date) == 0) {
				return ((begin < ab.getTime() && end <= ab.getTime()) || begin >= ab.getEnd());
			}
			
			return true;
		});
		
		if (!result) {
			return false;
		}

		final LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.of(end / 60, end % 60));
		final LocalDateTime endDateTime = startDateTime.plusMinutes(IConstants.SLOT_TIME);
		return blockTimes.stream().allMatch((bt) -> {
			if (startDateTime.compareTo(bt.getEndDateTime()) > 0) {
				return true;
			}

			return ((startDateTime.compareTo(bt.getBeginDateTime()) < 0) && 
					(endDateTime.compareTo(bt.getBeginDateTime()) < 0));
		});
	}
}
