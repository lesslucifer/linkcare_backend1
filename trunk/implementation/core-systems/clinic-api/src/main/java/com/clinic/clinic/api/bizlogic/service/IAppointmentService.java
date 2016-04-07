package com.clinic.clinic.api.bizlogic.service;

import java.time.LocalDate;
import java.util.List;

import com.clinic.clinic.common.dto.TraceDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;

public interface IAppointmentService {
	List<AppointmentBookingDto> getAppointments(Integer requester, List<Integer> appointmentIds);
	AppointmentBookingDto bookAppointment(Integer booker, AppointmentBookingRequestDto dto);
	void approveAppointment(Integer medicar, Integer appointmentId);
	void rejectAppointment(Integer medicar, Integer appointmentId);
	void cancelAppointment(Integer medicar, Integer appointmentId);
	void startAppointment(Integer medicar, Integer appointmentId);
	List<TraceDto> getMedicarAppointment(Integer medicar, LocalDate date);
}