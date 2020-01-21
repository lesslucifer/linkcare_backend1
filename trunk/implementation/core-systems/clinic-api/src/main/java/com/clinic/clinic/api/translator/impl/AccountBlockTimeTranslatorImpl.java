package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.common.dto.biz.AccountBlockTimeDto;

public class AccountBlockTimeTranslatorImpl extends AbstractTranslatorImpl<AccountBlockTimeDto, AccountBlockTimeEntity> {
	public static final AccountBlockTimeTranslatorImpl INST = new AccountBlockTimeTranslatorImpl();
	
	private AccountBlockTimeTranslatorImpl() {
		super(AccountBlockTimeDto.class, AccountBlockTimeEntity.class);
	}
	
	@Override
	public void entityToDto(AccountBlockTimeEntity ent, AccountBlockTimeDto dto) {
		super.entityToDto(ent, dto);
		dto.setBegin(ent.getBeginDateTime());
		dto.setLength(ent.getLengthInMinutes());
	}
	
	@Override
	public void dtoToEntity(AccountBlockTimeDto dto, AccountBlockTimeEntity ent) {
		super.dtoToEntity(dto, ent);
		ent.setBeginDateTime(dto.getBegin());
		ent.setLengthInMinutes(dto.getLength());
	}
}
