package com.clinic.clinic.api.translator.impl;

import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.persistence.entity.PrescriptionEntity;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.dto.biz.PrescriptionDto;
import com.clinic.clinic.common.exception.BizlogicException;

public class PrescriptionTranslatorImpl extends AbstractTranslatorImpl<PrescriptionDto, PrescriptionEntity> {

	public static final PrescriptionTranslatorImpl INST = new PrescriptionTranslatorImpl();
	
	private PrescriptionTranslatorImpl() {
		super(PrescriptionDto.class, PrescriptionEntity.class);
	}

	@Override
	public void dtoToEntity(PrescriptionDto dto, PrescriptionEntity ent) {
		BizlogicException.throwEx(HttpStatus.INTERNAL_SERVER_ERROR.value(), IBizErrorCode.NOT_SUPPORTED, "Translation not supported",
				PrescriptionDto.class.getSimpleName(), PrescriptionEntity.class.getSimpleName());
	}
	
	@Override
	public void entityToDto(PrescriptionEntity ent, PrescriptionDto dto) {
		super.entityToDto(ent, dto);
		
		dto.setBooking(TraceTranslatorImpl.INSTANCE.getDto(ent.getBooking()));
		dto.setDoctorNote(PrescriptionDoctorNoteTranslatorImpl.INST.getDto(ent.getDoctorNote()));
		dto.setMedicines(PrescriptionMedicineTranslatorImpl.INST.getDtoList(ent.getMedicines()));
	}
}
