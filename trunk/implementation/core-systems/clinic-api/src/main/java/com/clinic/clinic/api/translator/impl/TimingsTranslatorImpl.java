package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.TimingsEntity;
import com.clinic.clinic.common.dto.biz.TimingsDto;

public class TimingsTranslatorImpl extends AbstractTranslatorImpl<TimingsDto, TimingsEntity> {

	public static final TimingsTranslatorImpl INSTANCE = new TimingsTranslatorImpl();
	
	private TimingsTranslatorImpl() {
		super(TimingsDto.class, TimingsEntity.class);
	}
}
