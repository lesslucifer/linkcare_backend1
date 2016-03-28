package com.clinic.clinic.api.bizlogic.service;

import java.util.List;

import com.clinic.clinic.common.dto.biz.AccountTimingsDto;
import com.clinic.clinic.common.dto.biz.TimingsDayDto;

public interface ITimingsService {
	AccountTimingsDto getAccountTimings(Integer accountId);
	AccountTimingsDto updateAccountTimings(Integer accountId, AccountTimingsDto dto);
	
	List<TimingsDayDto> getTimingDaySlots(Integer accountId, String day, int range);
}
