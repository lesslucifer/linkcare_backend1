package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.MedicarProfileEntity;
import com.clinic.clinic.common.dto.biz.MedicarProfileDto;

public class MedicarProfileTranslatorImpl extends AbstractTranslatorImpl<MedicarProfileDto, MedicarProfileEntity> {

	public static final MedicarProfileTranslatorImpl INST = new MedicarProfileTranslatorImpl();
	
	public MedicarProfileTranslatorImpl() {
		super(MedicarProfileDto.class, MedicarProfileEntity.class);
	}
}
