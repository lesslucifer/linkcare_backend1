package com.clinic.clinic.api.translator.impl;

import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.dto.biz.AppointmentBookingDto;
import com.clinic.clinic.common.exception.BizlogicException;

public class AppointmentBookingTranslator extends AbstractTranslatorImpl<AppointmentBookingDto, AppointmentBookingEntity> {
	
	public static final AppointmentBookingTranslator INST = new AppointmentBookingTranslator();
	
	public AppointmentBookingTranslator() {
		super(AppointmentBookingDto.class, AppointmentBookingEntity.class);
	}
	
	@Override
	public void dtoToEntity(AppointmentBookingDto dto, AppointmentBookingEntity ent) {
		BizlogicException.throwEx(HttpStatus.INTERNAL_SERVER_ERROR.value(), IBizErrorCode.NOT_SUPPORTED, "Translation not supported",
				AppointmentBookingDto.class.getSimpleName(), AppointmentBookingEntity.class.getSimpleName());
	}
	
	@Override
	public void entityToDto(AppointmentBookingEntity ent, AppointmentBookingDto dto) {
		super.entityToDto(ent, dto);
		
		dto.setBooker(TraceTranslatorImpl.INSTANCE.getDto(ent.getBooker()));
		dto.setMedicar(TraceTranslatorImpl.INSTANCE.getDto(ent.getMedicar()));
		dto.setAddress(TraceTranslatorImpl.INSTANCE.getDto(ent.getAddress()));
		dto.setPatient(AppointmentPatientTranslator.INSTANCE.getDto(ent.getPatient()));
	}
}
