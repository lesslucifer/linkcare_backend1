package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;

public class AccountTimingsTranslator extends AbstractTranslatorImpl<AccountTimingsDto, AccountTimingsEntity> {


    @Override
	public AccountTimingsDto getDto(AccountTimingsEntity ent) {
        AccountTimingsDto dto = new AccountTimingsDto();
        this.entityToDto(ent, dto);
        return dto;
    }

    @Override
    public AccountTimingsEntity getEntity(AccountTimingsDto dto) {
        AccountTimingsEntity ent = new AccountTimingsEntity();
        this.dtoToEntity(dto, ent);
        return ent;
    }
}
