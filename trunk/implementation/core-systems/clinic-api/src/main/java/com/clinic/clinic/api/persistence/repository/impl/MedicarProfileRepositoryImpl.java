package com.clinic.clinic.api.persistence.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.MedicarProfileEntity;
import com.clinic.clinic.api.persistence.repository.IMedicarProfileRepository;

@Repository
public class MedicarProfileRepositoryImpl extends AbsRepositoryImpl<MedicarProfileEntity, Integer>
	implements IMedicarProfileRepository {
	
	@Override
	public MedicarProfileEntity getByAccountId(Integer accountId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT dp FROM MedicarProfileEntity dp ");
		sb.append("WHERE dp.account.id = :accountId");
		
		TypedQuery<MedicarProfileEntity> q = getEntityManager().createQuery(sb.toString(), MedicarProfileEntity.class);
		q.setParameter("accountId", accountId);
		q.setMaxResults(1);

		List<MedicarProfileEntity> result = q.getResultList();
		if (result == null || result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
	}
	
}
