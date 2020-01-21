package com.clinic.clinic.api.persistence.repository;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;

public interface IAccountTimingsRepository extends IRepository<AccountTimingsEntity, Integer> {
	AccountTimingsEntity getLastestAccountTimings(final Integer accountId);
	AccountTimingsEntity updateAccountTimings(AccountEntity account, AccountTimingsDto dto);
}
