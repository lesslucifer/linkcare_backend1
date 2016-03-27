package com.clinic.clinic.api.bizlogic.service;

import java.sql.Date;

import com.clinic.clinic.common.dto.biz.AccountTimingsDto;

public interface ITimingsService {
	AccountTimingsDto getAccountTimings(Integer accountId);
	AccountTimingsDto updateAccountTimings(Integer accountId, AccountTimingsDto dto);
}
