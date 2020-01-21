package com.clinic.clinic.api.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.common.dto.biz.NotificationDto;

public class NotificationTranslatorImpl extends AbstractTranslatorImpl<NotificationDto, NotificationEntity> {
	public static final NotificationTranslatorImpl INST = new NotificationTranslatorImpl();
	
	@Autowired
	IAccountRepository accRepo;
	
	public NotificationTranslatorImpl() {
		super(NotificationDto.class, NotificationEntity.class);
	}
	
	@Override
	public void dtoToEntity(NotificationDto dto, NotificationEntity ent) {
		super.dtoToEntity(dto, ent);
		dto.setSender(TraceTranslatorImpl.INSTANCE.getDto(ent.getSender()));
	}
	
	@Override
	public void entityToDto(NotificationEntity ent, NotificationDto dto) {
		super.entityToDto(ent, dto);
		
		if (dto.getSender() != null && dto.getSender().getId() != null) {
			ent.setSender(accRepo.getReference(AccountEntity.class, dto.getSender().getId()));
		}
	}
}