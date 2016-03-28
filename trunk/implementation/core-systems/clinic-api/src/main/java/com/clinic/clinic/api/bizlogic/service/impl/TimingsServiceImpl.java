package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.ITimingsService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAccountTimingsRepository;
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
        	if (!isTimingsConflict(dto.getTimings())) {
        		throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.TIMINGS_CONFLICT, "Timings Conflicted!");
        	}
        	
        	AccountEntity account = accountRepo.findFirstEntity(IDbConstants.FIELD_ID, accountId, true);
        	if (account == null) {
        		throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Unknown user", accountId);
        	}
        	
        	AccountTimingsEntity accTimings = accTimingsRepo.updateAccountTimings(account, dto);
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
		timings.sort((t1, t2) -> Integer.compare(t1.getBegin(), t2.getBegin()));
	}
	
	private boolean isTimingsValid(List<TimingsDto> timings) {
		return !timings.isEmpty() && timings.stream().allMatch(t -> t.getLength() > 0);
	}

	private boolean isTimingsConflict(List<TimingsDto> sortedTimings) {
		for (int i = 1; i < sortedTimings.size(); ++i) {
			TimingsDto prev = sortedTimings.get(i - 1);
			TimingsDto cur = sortedTimings.get(i);
			
			if (prev.getEnd() <= cur.getBegin()) {
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
		return IntStream.range(0, range).mapToObj(i -> {
			LocalDate date = startDay.plusDays(i);
			List<TimingsSlotDto> slots = ent.getTimings().stream().map(t -> {
				int nSlot = t.getLength() / IConstants.SLOT_TIME;
				return IntStream.range(0, nSlot).mapToObj(slotOffset -> {
					TimingsSlotDto slot = new TimingsSlotDto();
					slot.setTime(t.getBeginTime() + slotOffset * IConstants.SLOT_TIME);
					slot.setAvailable(true);
					return slot;
				});
			}).flatMap(s -> s).collect(Collectors.toList());
			
			TimingsDayDto timingsDay = new TimingsDayDto();
			timingsDay.setDay(date);
			timingsDay.setSlots(slots);
			
			return timingsDay;
		}).collect(Collectors.toList());
	}
}
