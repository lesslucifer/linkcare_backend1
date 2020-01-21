package com.clinic.clinic.api.translator.impl;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.persistence.entity.TraceEntity;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.dto.TraceDto;
import com.clinic.clinic.common.exception.BizlogicException;

public class TraceTranslatorImpl extends AbstractTranslatorImpl<TraceDto, TraceEntity> {

	public static final TraceTranslatorImpl INSTANCE = new TraceTranslatorImpl();

	private TraceTranslatorImpl() {
		super(TraceDto.class, TraceEntity.class);
	}

	@Override
	public TraceEntity getEntity(TraceDto dto) {
		BizlogicException.throwEx(HttpStatus.INTERNAL_SERVER_ERROR.value(), IBizErrorCode.NOT_SUPPORTED, "Translation not supported",
				TraceDto.class.getSimpleName(), TraceEntity.class.getSimpleName());
		return null;
	}

	@Override
	public void dtoToEntity(TraceDto dto, TraceEntity ent) {
		BizlogicException.throwEx(HttpStatus.INTERNAL_SERVER_ERROR.value(), IBizErrorCode.NOT_SUPPORTED, "Translation not supported",
				TraceDto.class.getSimpleName(), TraceEntity.class.getSimpleName());
	}

	@Override
	public List<TraceEntity> getEntityList(List<? extends TraceDto> dtoList) {
		BizlogicException.throwEx(HttpStatus.INTERNAL_SERVER_ERROR.value(), IBizErrorCode.NOT_SUPPORTED, "Translation not supported",
				TraceDto.class.getSimpleName(), TraceEntity.class.getSimpleName());
		return null;
	}
}
