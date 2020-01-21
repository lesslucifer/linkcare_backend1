package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.ITimingsService;
import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.TimingsEntity;
import com.clinic.clinic.api.persistence.repository.IAccountBlockTimeRepository;
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
import com.clinic.clinic.common.utils.Utils;

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
//    @Autowired
//    private IAccountCustomTimingsRepository accCustomTimingsRepo;

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
        	
        	/*List<TimingsEntity> timings =*/ timingsRepo.insertTimings(accTimings, dto.getTimings());
        	
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
	public List<TimingsDayDto> getTimingDaySlots(Integer accountId, String day, int range,
			final Predicate<Integer> typeFilter, int durSlot) {
		
		AccountTimingsEntity ent = accTimingsRepo.getLastestAccountTimings(accountId);
		if (ent == null) {
			return Collections.emptyList();
		}
		
		ent.getTimings().size(); // get all timings
		final List<TimingsEntity> timings = ent.getTimings().stream().filter((t) -> typeFilter.test(t.getType())).collect(Collectors.toList());

		final LocalDate startDay = LocalDate.parse(day, DateTimeFormatter.ofPattern(IConstants.DateForMat_DDMMYYYY));
		final LocalDate endDay = startDay.plusDays(range);
		
		final List<AccountBlockTimeEntity> blockTimes = accBlockTimeRepo.getBlockTime(accountId, startDay.atStartOfDay(), endDay.atStartOfDay());
		blockTimes.sort(AccountBlockTimeEntity::compareTo);
		
		final List<AppointmentBookingEntity> activeAppointments = appointmentBookingRepo.getActiveAppointments(accountId, startDay, endDay);
		activeAppointments.sort(AppointmentBookingEntity::compareTo);
		
		final LocalDate toDay = LocalDate.now();
		final LocalTime now = LocalTime.now();
		final int timeNow = now.getHour() * 60 + now.getMinute();
		return IntStream.range(0, range).mapToObj(i -> {
			LocalDate date = startDay.plusDays(i);
			List<TimingsSlotDto> slots = timings.stream().map(t -> {
				int nSlot = t.getLength() / IConstants.SLOT_TIME;
				return IntStream.range(0, nSlot).mapToObj(slotOffset -> {
					TimingsSlotDto slot = new TimingsSlotDto();
					slot.setTime(t.getBeginTime() + slotOffset * IConstants.SLOT_TIME);
					int dateCompare = date.compareTo(toDay);
					boolean isAvail = (dateCompare > 0 || (dateCompare == 0 && slot.getTime() >= timeNow)) && isTimeAvailable(date, slot.getTime(), slot.getTime() + durSlot * IConstants.SLOT_TIME,
							blockTimes, activeAppointments);
					slot.setAvailable(isAvail);
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
	public Object countTimingDaySlots(Integer accountId, String day) {
		List<TimingsDayDto> dtos = this.getTimingDaySlots(accountId, day, 1, i -> true, 1);
		
		int clinic = 0;
		int patitentHome = 0;
		for (TimingsDayDto dto : dtos) {
			for (TimingsSlotDto slot : dto.getSlots()) {
				if (slot.getType() == 0) {
					++clinic;
				}
				else {
					++patitentHome;
				}
			}
		}
		
		return Utils.mkMap("0", clinic, "1", patitentHome);
	}
	
	@Override
	public boolean isTimeAvailable(LocalDate date, int begin, int end,
			final List<AccountBlockTimeEntity> blockTimes, 
			final List<AppointmentBookingEntity> activeAppointments) {
		
		// if time is not valid -> not avail
		final int MINUTES_PER_DAY = 60 * 24;
		if (begin >= MINUTES_PER_DAY || end >= MINUTES_PER_DAY) {
			return false;
		}

		boolean result = activeAppointments.stream().allMatch((ab) -> {
			if (ab.getDate().compareTo(date) == 0) {
				return ((begin < ab.getTime() && end <= ab.getTime()) || begin >= ab.getEnd());
			}
			
			return true;
		});
		
		if (!result) {
			return false;
		}

		final LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.of(begin / 60, begin % 60));
		final LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.of(end / 60, end % 60));
		return blockTimes.stream().allMatch((bt) -> {
			if (startDateTime.compareTo(bt.getEndDateTime()) > 0) {
				return true;
			}

			return ((startDateTime.compareTo(bt.getBeginDateTime()) < 0) && 
					(endDateTime.compareTo(bt.getBeginDateTime()) < 0));
		});
	}
}
