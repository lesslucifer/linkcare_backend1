package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.AppointmentPatientEntity;
import com.clinic.clinic.common.dto.biz.AppointmentPatientDto;

public final class AppointmentPatientTranslator extends AbstractTranslatorImpl<AppointmentPatientDto, AppointmentPatientEntity> {

	public static final AppointmentPatientTranslator INSTANCE = new AppointmentPatientTranslator();
	
	private AppointmentPatientTranslator() {
		super(AppointmentPatientDto.class, AppointmentPatientEntity.class);
	}

}
