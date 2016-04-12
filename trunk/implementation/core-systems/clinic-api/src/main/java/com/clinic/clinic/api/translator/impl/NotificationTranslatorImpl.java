package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.common.dto.biz.NotificationDto;

public class NotificationTranslatorImpl extends AbstractTranslatorImpl<NotificationDto, NotificationEntity> {
	public static final NotificationTranslatorImpl INST = new NotificationTranslatorImpl();
	
	public NotificationTranslatorImpl() {
		super(NotificationDto.class, NotificationEntity.class);
	}
}
