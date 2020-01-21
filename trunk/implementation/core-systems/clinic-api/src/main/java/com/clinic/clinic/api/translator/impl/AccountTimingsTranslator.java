package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;

public class AccountTimingsTranslator extends AbstractTranslatorImpl<AccountTimingsDto, AccountTimingsEntity> {

	public static final AccountTimingsTranslator INSTANCE = new AccountTimingsTranslator();
	
	private AccountTimingsTranslator() {
		super(AccountTimingsDto.class, AccountTimingsEntity.class);
	}

	@Override
	public void entityToDto(AccountTimingsEntity ent, AccountTimingsDto dto) {
		super.entityToDto(ent, dto);
		dto.setTimings(TimingsTranslatorImpl.INSTANCE.getDtoList(ent.getTimings()));
	}

	@Override
	public void dtoToEntity(AccountTimingsDto dto, AccountTimingsEntity ent) {
		super.dtoToEntity(dto, ent);
//		ent.setTimings(TimingsTranslatorImpl.INSTANCE.getEntityList(dto.getTimings()));
	}
}
