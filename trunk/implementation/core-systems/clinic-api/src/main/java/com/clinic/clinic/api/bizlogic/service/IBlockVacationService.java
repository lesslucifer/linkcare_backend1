package com.clinic.clinic.api.bizlogic.service;

import java.time.LocalDateTime;
import java.util.List;

import com.clinic.clinic.common.dto.biz.AccountBlockTimeDto;

public interface IBlockVacationService {
	List<AccountBlockTimeDto> getBlockVacations(Integer accountId, LocalDateTime start, LocalDateTime end);
	List<AccountBlockTimeDto> getBlockVacations(Integer accountId, List<Integer> ids);
	AccountBlockTimeDto addBlockVacation(Integer accountId, AccountBlockTimeDto dto);
	AccountBlockTimeDto deleteBlockVacation(Integer accountId, Integer blockTimeId);
}