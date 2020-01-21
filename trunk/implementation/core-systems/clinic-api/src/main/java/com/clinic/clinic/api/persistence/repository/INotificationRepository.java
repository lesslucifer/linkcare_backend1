package com.clinic.clinic.api.persistence.repository;

import java.util.List;

import com.clinic.clinic.api.persistence.entity.NotificationEntity;

public interface INotificationRepository extends IRepository<NotificationEntity, Integer> {
	List<Integer> getNotifications(Integer accountId);
	List<NotificationEntity> getNotifications(Integer accountId, List<Integer> notifs);
}
