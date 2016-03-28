package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.AccountDto;

public class AccountTranslatorImpl extends AbstractTranslatorImpl<AccountDto, AccountEntity>
        implements
            ITranslator<AccountDto, AccountEntity> {

	public AccountTranslatorImpl() {
		super(AccountDto.class, AccountEntity.class);
		// TODO Auto-generated constructor stub
	}
}
