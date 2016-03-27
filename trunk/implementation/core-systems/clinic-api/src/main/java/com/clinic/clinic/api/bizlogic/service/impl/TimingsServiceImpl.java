package com.clinic.clinic.api.bizlogic.service.impl;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.ITimingsService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.entity.TimingsEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAccountTimingsRepository;
import com.clinic.clinic.api.persistence.repository.ITimingsRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.AccountTimingsTranslator;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;
import com.clinic.clinic.common.dto.biz.TimingsDto;
import com.clinic.clinic.common.exception.BizlogicException;

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

    private ITranslator<AccountTimingsDto, AccountTimingsEntity> accTimingsTrans = new AccountTimingsTranslator();

	@Override
	public AccountTimingsDto updateAccountTimings(Integer accountId, AccountTimingsDto dto) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        AccountTimingsDto retValue = null;
        try {
        	if (!isTimingsValid(dto.getTimings())) {
        		throw new BizlogicException("Invalid! Empty timings");
        	}

        	sortTimings(dto.getTimings());
        	if (!isTimingsConflict(dto.getTimings())) {
        		throw new BizlogicException("Timings Conflicted!");
        	}
        	
        	AccountEntity account = accountRepo.findFirstEntity(IDbConstants.FIELD_ID, accountId, true);
        	if (account == null) {
        		throw new BizlogicException("Unknown account");
        	}
        	
        	if (!accountRepo.isAccountHasRight(accountId, IDbConstants.RIGHT_UPDATE_TIMINGS)) {
        		throw new BizlogicException("Permission denied!");
        	}
        	
        	AccountTimingsEntity accTimings = accTimingsRepo.updateAccountTimings(account, dto);
        	List<TimingsEntity> timings = timingsRepo.insertTimings(accTimings, dto.getTimings());
        	
        	// Update appointments time

        	retValue = dto;
        } catch (BizlogicException be) {
            LOGGER.error("Error", be);
        } catch (Exception e) {
            LOGGER.error("Error", e);
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
		return timings.stream().allMatch(t -> t.getLength() > 0);
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
		return accTimingsTrans.getDto(ent);
	}
}
