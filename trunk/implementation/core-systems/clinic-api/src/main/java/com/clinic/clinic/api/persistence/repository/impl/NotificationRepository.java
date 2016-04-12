package com.clinic.clinic.api.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.INotificationRepository;

@Repository
public class NotificationRepository extends AbsRepositoryImpl<NotificationEntity, Integer>
	implements INotificationRepository {
	
}
