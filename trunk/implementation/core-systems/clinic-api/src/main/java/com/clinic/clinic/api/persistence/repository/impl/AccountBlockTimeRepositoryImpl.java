package com.clinic.clinic.api.persistence.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.api.persistence.repository.IAccountBlockTimeRepository;

@Repository
public final class AccountBlockTimeRepositoryImpl extends AbsRepositoryImpl<AccountBlockTimeEntity, Integer>
	implements IAccountBlockTimeRepository {

	@Override
	public List<AccountBlockTimeEntity> getBlockTime(Integer accountId, LocalDateTime from, LocalDateTime to) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT bt FROM AccountBlockTimeEntity bt ");
		sb.append("WHERE account_id = :accountId AND ");
		sb.append("NOT (");
		sb.append("(bt.beginDateTime < :from AND ");
		sb.append("bt.beginDateTime + 60 * bt.lengthInMinutes < :from) OR ");
		sb.append("(bt.beginDateTime > :to)");
		sb.append(")");
		
		TypedQuery<AccountBlockTimeEntity> q = getEntityManager().createQuery(sb.toString(), AccountBlockTimeEntity.class);
		q.setParameter("accountId", accountId);
		q.setParameter("from", from);
		q.setParameter("to", to);

		return q.getResultList();
	}
	
}