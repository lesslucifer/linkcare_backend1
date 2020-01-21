package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.PrescriptionDoctorNoteEntity;
import com.clinic.clinic.common.dto.biz.DoctorNoteDto;

public class PrescriptionDoctorNoteTranslatorImpl extends AbstractTranslatorImpl<DoctorNoteDto, PrescriptionDoctorNoteEntity> {
	public static final PrescriptionDoctorNoteTranslatorImpl INST = new PrescriptionDoctorNoteTranslatorImpl();
	
	public PrescriptionDoctorNoteTranslatorImpl() {
		super(DoctorNoteDto.class, PrescriptionDoctorNoteEntity.class);
	}
}
