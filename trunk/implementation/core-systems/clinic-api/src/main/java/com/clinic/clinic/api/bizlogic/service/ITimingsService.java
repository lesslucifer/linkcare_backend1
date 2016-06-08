package com.clinic.clinic.api.bizlogic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;
import com.clinic.clinic.common.dto.biz.TimingsDayDto;

public interface ITimingsService {
    
	AccountTimingsDto getAccountTimings(Integer accountId);
	
	AccountTimingsDto updateAccountTimings(Integer accountId, AccountTimingsDto dto);
	
	List<TimingsDayDto> getTimingDaySlots(Integer accountId, String day, int range,
			Predicate<Integer> typeFilter, int durSlot);

	Object countTimingDaySlots(Integer accountId, String day);
	
	boolean isTimeAvailable(LocalDate date, int begin, int end,
			final List<AccountBlockTimeEntity> blockTimes, 
			final List<AppointmentBookingEntity> approvedAppointments);
}
