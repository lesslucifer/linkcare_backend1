package com.clinic.clinic.api.bizlogic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.INotificationService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.INotificationRepository;
import com.clinic.clinic.api.translator.impl.NotificationTranslatorImpl;
import com.clinic.clinic.common.dto.biz.NotificationDto;

@ApplicationService
public class NotificationService extends AbsService implements INotificationService {

	@Autowired
	INotificationRepository notifRepo;
	
	@Autowired
	IAccountRepository accRepo;
	
	@Override
	public List<Integer> getNotifications(Integer accountId) {
		return notifRepo.getNotifications(accountId);
	}

	@Override
	public void sendMessage(Integer sender, Integer receiver, String content) {
		NotificationEntity entity = new NotificationEntity();
		if (sender != null) {
			entity.setSender(accRepo.getReference(AccountEntity.class, sender));
		}
		
		entity.setOwner(accRepo.getReference(AccountEntity.class, receiver));
		entity.setType(NotificationEntity.TYPE_MSG);
		entity.setContent(content);
		notifRepo.save(entity);
	}
	
	@Override
	public List<NotificationDto> getNotifications(Integer accountId, List<Integer> notifs) {
		List<NotificationEntity> entities = notifRepo.getNotifications(accountId, notifs);
		return NotificationTranslatorImpl.INST.getDtoList(entities);
	}
}
