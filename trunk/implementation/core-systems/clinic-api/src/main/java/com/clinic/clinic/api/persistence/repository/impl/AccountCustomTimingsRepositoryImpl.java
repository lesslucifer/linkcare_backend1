package com.clinic.clinic.api.persistence.repository.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountCustomTimingsEntity;
import com.clinic.clinic.api.persistence.repository.IAccountCustomTimingsRepository;

@Repository
public final class AccountCustomTimingsRepositoryImpl extends AbsRepositoryImpl<AccountCustomTimingsEntity, Integer> 
	implements IAccountCustomTimingsRepository {

	@Override
	public List<AccountCustomTimingsEntity> getCustomTimings(Integer accountId, LocalDate from, LocalDate to) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ac FROM AccountCustomTimingsEntity ac ");
		sb.append("WHERE account_id = :accountId AND ");
		sb.append("ac.date BETWEEN :from AND :to");
		
		TypedQuery<AccountCustomTimingsEntity> q = getEntityManager().createQuery(sb.toString(), AccountCustomTimingsEntity.class);
		q.setParameter("accountId", accountId);
		q.setParameter("from", from);
		q.setParameter("to", to);

		return q.getResultList();
	}

}
