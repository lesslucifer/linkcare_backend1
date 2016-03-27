package com.clinic.clinic.api.persistence.repository.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.repository.IAccountTimingsRepository;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;

@Repository
public final class AccountTimingsRepository extends AbsRepositoryImpl<AccountTimingsEntity, Integer> implements IAccountTimingsRepository {

	@Override
	public AccountTimingsEntity updateAccountTimings(AccountEntity account, AccountTimingsDto dto) {
		AccountTimingsEntity entity = new AccountTimingsEntity();
		entity.setAccount(account);
		entity.setBeginDate(dto.getBeginDate());

		return save(entity);
	}

	@Override
	public AccountTimingsEntity getLastestAccountTimings(final Integer accountId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT acc_t FROM AccountTimingsEntity acc_t ");
		sb.append("WHERE account_id = :account_id ");
		sb.append("ORDER BY begin_date DESC ");

		Query q = getEntityManager().createQuery(sb.toString(), AccountTimingsEntity.class);
		q.setParameter("account_id", accountId);
		q.setMaxResults(1);
		
		List<?> result = q.getResultList();
		if (result == null || result.isEmpty()) {
			return null;
		}

		return (AccountTimingsEntity) result.get(0);
	}

}
