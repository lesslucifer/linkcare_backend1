package com.clinic.clinic.api.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import com.clinic.clinic.api.persistence.entity.AddressEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;

public interface IAppointmentBookingRepository extends IRepository<AppointmentBookingEntity, Integer> {
	AppointmentBookingEntity addAppointment(Integer booker, AddressEntity addressId, int time, int dur, AppointmentBookingRequestDto bookingDto);
	
	List<AppointmentBookingEntity> getActiveAppointments(Integer medicarId, LocalDate date);
	List<AppointmentBookingEntity> getActiveAppointments(Integer medicarId, LocalDate dateFrom, LocalDate dateTo);
	boolean hasActiveAppointment(Integer medicarId, LocalDate date, int timeFrom, int timeTo);
	List<AppointmentBookingEntity> getActiveAppointmentsAtHome(Integer medicarId, LocalDate date, boolean atHome);
	int countActiveAppointmentsAtHome(Integer medicarId, LocalDate date, boolean atHome);
	
	List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate date, int timeFrom, int timeTo);
	boolean hasApprovedBooking(Integer medicarId, LocalDate date, int timeFrom, int timeTo);
	
	List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate dateFrom, LocalDate dateTo);
	boolean hasProcessingAppointment(Integer medicarId);
}