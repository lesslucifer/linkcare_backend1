package com.clinic.clinic.api.persistence.repository.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.api.persistence.repository.IAccountBlockTimeRepository;

@Repository
public final class AccountBlockTimeRepositoryImpl extends AbsRepositoryImpl<AccountBlockTimeEntity, Integer>
	implements IAccountBlockTimeRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountBlockTimeEntity> getBlockTime(Integer accountId, LocalDateTime from, LocalDateTime to) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM `account_block_time` as bt ");
		sb.append("WHERE bt.account_id = :accountId AND ");
		sb.append("bt.is_deleted = 0 AND ");
		sb.append("NOT (");
		sb.append("DATE_ADD(bt.begin, INTERVAL bt.length MINUTE) < :from OR ");
		sb.append("bt.begin > :to");
		sb.append(")");
		
		Query q = getEntityManager().createNativeQuery(sb.toString(), AccountBlockTimeEntity.class);
		q.setParameter("accountId", accountId);
		q.setParameter("from", Date.from(from.atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE);
		q.setParameter("to", Date.from(to.atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE);

		return (List<AccountBlockTimeEntity>) q.getResultList();
	}
	
	@Override
	public List<AccountBlockTimeEntity> getBlockTime(Integer accountId, List<Integer> ids) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT bt FROM AccountBlockTimeEntity bt ");
		sb.append("WHERE bt.account.id = :accountId AND ");
		sb.append("bt.isDeleted = 0 AND ");
		sb.append("bt.id IN :ids");
		
		TypedQuery<AccountBlockTimeEntity> q = getEntityManager().createQuery(sb.toString(), AccountBlockTimeEntity.class);
		q.setParameter("accountId", accountId);
		q.setParameter("ids", ids);

		return q.getResultList();
	}
	
	@Override
	public boolean hasBlockTime(Integer accountId, LocalDateTime from, LocalDateTime to) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT 1 FROM `account_block_time` as bt ");
		sb.append("WHERE bt.account_id = :accountId AND ");
		sb.append("bt.is_deleted = 0 AND ");
		sb.append("NOT (");
		sb.append("DATE_ADD(bt.begin, INTERVAL bt.length MINUTE) < :from OR ");
		sb.append("bt.begin > :to");
		sb.append(")");

		Query q = getEntityManager().createNativeQuery(sb.toString(), AccountBlockTimeEntity.class);
		q.setParameter("accountId", accountId);
		q.setParameter("from", Date.from(from.atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE);
		q.setParameter("to", Date.from(to.atZone(ZoneId.systemDefault()).toInstant()), TemporalType.DATE);

		List<?> result = q.getResultList();
		return result != null && !result.isEmpty();
	}
}
