package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.AccountDto;

public class AccountTranslatorImpl extends AbstractTranslatorImpl<AccountDto, AccountEntity>
        implements
            ITranslator<AccountDto, AccountEntity> {

	public static final AccountTranslatorImpl INST = new AccountTranslatorImpl();

	public AccountTranslatorImpl() {
		super(AccountDto.class, AccountEntity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void entityToDto(AccountEntity ent, AccountDto dto) {
		// TODO Auto-generated method stub
		super.entityToDto(ent, dto);
		dto.setSubcategory(SubcategoryTranslatorImpl.INST.getDto(ent.getSubcategory()));
	}
	
	
}
