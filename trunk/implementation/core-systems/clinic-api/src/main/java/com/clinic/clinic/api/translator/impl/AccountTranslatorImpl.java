package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.AccountDto;

public class AccountTranslatorImpl extends AbstractTranslatorImpl<AccountDto, AccountEntity>
        implements
            ITranslator<AccountDto, AccountEntity> {

    @Override
    public AccountDto getDto(AccountEntity ent) {
        AccountDto dto = new AccountDto();
        this.entityToDto(ent, dto);
        return dto;
    }

    @Override
    public AccountEntity getEntity(AccountDto dto) {
        AccountEntity ent = new AccountEntity();
        this.dtoToEntity(dto, ent);
        return ent;
    }
}
