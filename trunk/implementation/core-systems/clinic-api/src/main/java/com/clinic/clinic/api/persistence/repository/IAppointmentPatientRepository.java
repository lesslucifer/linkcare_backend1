package com.clinic.clinic.api.persistence.repository;

import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentPatientEntity;
import com.clinic.clinic.common.dto.biz.AppointmentPatientDto;

public interface IAppointmentPatientRepository extends IRepository<AppointmentPatientEntity, AppointmentBookingEntity> {
	AppointmentPatientEntity addNewPatientAppointment(AppointmentBookingEntity appointmentBooking, AppointmentPatientDto dto);
}
