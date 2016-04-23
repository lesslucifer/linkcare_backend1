package com.clinic.clinic.api.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;

public interface IAppointmentBookingRepository extends IRepository<AppointmentBookingEntity, Integer> {

	List<AppointmentBookingEntity> getActiveAppointments(Integer medicarId, LocalDate date);
	List<AppointmentBookingEntity> getActiveAppointments(Integer medicarId, LocalDate dateFrom, LocalDate dateTo);
	boolean hasActiveAppointment(Integer medicarId, LocalDate date, int timeFrom, int timeTo);
	List<AppointmentBookingEntity> getActiveAppointmentsAtHome(Integer medicarId, LocalDate date, boolean atHome);
	int countActiveAppointmentsAtHome(Integer medicarId, LocalDate date, boolean atHome);
	
	List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate date, int timeFrom, int timeTo);
	boolean hasApprovedBooking(Integer medicarId, LocalDate date, int timeFrom, int timeTo);
	
	List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate dateFrom, LocalDate dateTo);
	Integer getProcessingAppointment(Integer medicarId);

	default boolean isPatientHaveRecentWaitingAppointment(Integer patientId, long now) {
		return isPatientHaveRecentWaitingAppointment(patientId, now, 1440);
	}
	boolean isPatientHaveRecentWaitingAppointment(Integer patientId, long now, int recentDuration);
	
	List<AppointmentBookingEntity> getAppointmentsByStatus(Integer medicar, Integer status);
	
	List<AppointmentBookingEntity> findAppointmentBookingByBookerAndMedicar(Integer medicar, Integer booker);
}