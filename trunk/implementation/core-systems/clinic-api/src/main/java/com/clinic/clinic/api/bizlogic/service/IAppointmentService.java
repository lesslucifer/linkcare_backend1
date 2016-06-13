package com.clinic.clinic.api.bizlogic.service;

import java.time.LocalDate;
import java.util.List;

import com.clinic.clinic.common.dto.TraceDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;

public interface IAppointmentService {
	List<AppointmentBookingDto> getAppointments(int requester, List<Integer> appointmentIds);
	AppointmentBookingDto bookAppointment(int booker, AppointmentBookingRequestDto dto);
	void approveAppointment(int medicar, int appointmentId);
	void rejectAppointment(int medicar, int appointmentId);
	void cancelAppointment(int medicar, int appointmentId, String reason);
	void startAppointment(int medicar, int appointmentId);
	List<TraceDto> getMedicarAppointments(int medicar, LocalDate date);
	List<TraceDto> getMedicarAppointmentsByType(int medicar, LocalDate date, int type);
	int countMedicarAppointmentsByType(int medicar, LocalDate date, int type);
	List<TraceDto> getAppointmentByStatus(int medicar, int status);
}