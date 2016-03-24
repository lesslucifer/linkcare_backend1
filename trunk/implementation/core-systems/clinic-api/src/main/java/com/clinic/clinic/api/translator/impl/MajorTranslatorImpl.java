package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.MajorEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.MajorDto;

public class MajorTranslatorImpl extends AbstractTranslatorImpl<MajorDto, MajorEntity> implements ITranslator<MajorDto, MajorEntity> {

	@Override
	public MajorDto getDto(MajorEntity ent) {
		MajorDto dto = new MajorDto();
		this.entityToDto(ent, dto);
		return dto;
	}

	@Override
	public MajorEntity getEntity(MajorDto dto) {
		MajorEntity ent = new MajorEntity();
		this.dtoToEntity(dto, ent);
		return ent;
	}

}
