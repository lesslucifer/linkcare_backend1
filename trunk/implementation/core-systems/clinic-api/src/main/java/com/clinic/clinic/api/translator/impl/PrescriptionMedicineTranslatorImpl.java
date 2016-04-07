package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.PrescriptionMedicineEntity;
import com.clinic.clinic.common.dto.biz.PrescriptionMedicineDto;

public class PrescriptionMedicineTranslatorImpl extends AbstractTranslatorImpl<PrescriptionMedicineDto, PrescriptionMedicineEntity> {

	public static final PrescriptionMedicineTranslatorImpl INST = new PrescriptionMedicineTranslatorImpl();
	
	public PrescriptionMedicineTranslatorImpl() {
		super(PrescriptionMedicineDto.class, PrescriptionMedicineEntity.class);
	}
}
