package com.clinic.clinic.api.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentPatientEntity;
import com.clinic.clinic.api.persistence.repository.IAppointmentPatientRepository;
import com.clinic.clinic.api.translator.impl.AppointmentPatientTranslator;
import com.clinic.clinic.common.dto.biz.AppointmentPatientDto;

@Repository
public final class AppointmentPatientRepositoryImpl extends AbsRepositoryImpl<AppointmentPatientEntity, AppointmentBookingEntity> 
	implements IAppointmentPatientRepository {

	@Override
	public AppointmentPatientEntity addNewPatientAppointment(AppointmentBookingEntity appointmentBooking,
			AppointmentPatientDto dto) {
		AppointmentPatientEntity entity = AppointmentPatientTranslator.INSTANCE.getEntity(dto);
		entity.setAppointment_id(appointmentBooking.getId());
		entity.setAppointmentBooking(appointmentBooking);
		entity = getJpaRepo().save(entity);
		
		if (entity != null) {
			appointmentBooking.setPatient(entity);
		}
		
		return entity;
	}

}