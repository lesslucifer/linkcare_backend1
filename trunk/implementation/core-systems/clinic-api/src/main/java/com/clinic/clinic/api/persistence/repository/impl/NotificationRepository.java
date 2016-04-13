package com.clinic.clinic.api.persistence.repository.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.INotificationRepository;

@Repository
public class NotificationRepository extends AbsRepositoryImpl<NotificationEntity, Integer>
	implements INotificationRepository {
	
	@Override
	public List<Integer> getNotifications(Integer accountId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ne.id FROM NotificationEntity ne ");
		sb.append("WHERE ne.owner.id = :accountId");
		
		TypedQuery<Integer> q = getEntityManager().createQuery(sb.toString(), Integer.class);
		q.setParameter("accountId", accountId);

		List<Integer> result = q.getResultList();
		if (result == null) {
			result = Collections.emptyList();
		}
		
		return result;
	}
	
	@Override
	public List<NotificationEntity> getNotifications(Integer accountId, List<Integer> notifs) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ne FROM NotificationEntity ne ");
		sb.append("WHERE ne.owner.id = :accountId ");
		sb.append("AND ne.id IN :notifs");
		
		TypedQuery<NotificationEntity> q = getEntityManager().createQuery(sb.toString(), NotificationEntity.class);
		q.setParameter("accountId", accountId);
		q.setParameter("notifs", notifs);

		List<NotificationEntity> result = q.getResultList();
		if (result == null) {
			result = Collections.emptyList();
		}
		
		return result;
	}
}
