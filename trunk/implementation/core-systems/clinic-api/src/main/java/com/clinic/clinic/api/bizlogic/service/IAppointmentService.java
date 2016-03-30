package com.clinic.clinic.api.bizlogic.service;

import com.clinic.clinic.common.dto.biz.AppointmentBookingDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;
import com.clinic.clinic.common.dto.biz.AppointmentConfirmDto;

public interface IAppointmentService {
	AppointmentBookingDto bookAppointment(Integer booker, AppointmentBookingRequestDto dto);
	void confirmAppointment(Integer booker, AppointmentConfirmDto dto);
}
