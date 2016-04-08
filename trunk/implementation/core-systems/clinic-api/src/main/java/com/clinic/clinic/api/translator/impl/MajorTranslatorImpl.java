package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.MajorEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.MajorDto;

public class MajorTranslatorImpl extends AbstractTranslatorImpl<MajorDto, MajorEntity> implements ITranslator<MajorDto, MajorEntity> {

	
	public MajorTranslatorImpl() {
		super(MajorDto.class, MajorEntity.class);
	}

}
