package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.INotificationService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.INotificationRepository;
import com.clinic.clinic.api.translator.impl.NotificationTranslatorImpl;
import com.clinic.clinic.common.dto.biz.NotificationDto;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

@ApplicationService
public class NotificationService extends AbsService implements INotificationService {
	
	private static final String API_KEY = "AIzaSyDnlqbUJZfch-5A2biHNtrJ-9wJjfCUz8A";

	@Autowired
	INotificationRepository notifRepo;
	
	@Autowired
	IAccountRepository accRepo;
	
	@Override
	public List<Integer> getNotifications(Integer accountId) {
		return notifRepo.getNotifications(accountId);
	}

	@Override
	public void sendMessage(Integer sender, Integer receiver, Integer type, String content, Object... params) {
		NotificationEntity entity = new NotificationEntity();
		if (sender != null) {
			entity.setSender(accRepo.getReference(AccountEntity.class, sender));
		}
		
		AccountEntity receiverEnt = accRepo.getOne(receiver);
		String jsonParams = JSONArray.toJSONString(Arrays.asList(params));
		if (receiverEnt != null) {
			entity.setOwner(receiverEnt);
			entity.setType(type);
			entity.setContent(content);
			entity.setParams(jsonParams);
			entity.setTime(LocalDateTime.now());
			notifRepo.save(entity);
			
			// try to send notification
			if (receiverEnt.getDeviceToken() != null) {
				this.sendNotification(receiverEnt.getDeviceToken(), content, type, jsonParams);
			}
		}
	}
	
	@Override
	public List<NotificationDto> getNotifications(Integer accountId, List<Integer> notifs) {
		List<NotificationEntity> entities = notifRepo.getNotifications(accountId, notifs);
		return NotificationTranslatorImpl.INST.getDtoList(entities);
	}
	
	@Override
	public void setNotificationsRead(Integer accountId, List<Integer> notifs) {
		List<NotificationEntity> notifEnts = notifRepo.getNotifications(accountId, notifs);
		for (NotificationEntity notif : notifEnts) {
			notif.setRead(true);
		}
		
		notifRepo.save(notifEnts);
	}
	
	private void sendNotification(final String deviceToken, final String content, final Integer type, final String params) {
		if (deviceToken == null) {
			return;
		}
		
		Sender sender = new Sender(API_KEY);
		List<String> data = Arrays.asList(new String[] {deviceToken});
		
		String plainContent = content.replaceAll("(\\<[^\\>]*\\>)", "");

		Message message = new Message.Builder()
		.timeToLive(30)
		.delayWhileIdle(true)
		.addData("message", plainContent)
		.addData("type", String.valueOf(type))
		.addData("params", params)
		.build();
		
		try {
			// use this for multicast messages.  The second parameter
			// of sender.send() will need to be an array of register ids.
			MulticastResult result = sender.send(message, data, 1);
			
			if (result.getResults() != null) {
				int canonicalRegId = result.getCanonicalIds();
				if (canonicalRegId != 0) {
					
				}
			} else {
				int error = result.getFailure();
				System.out.println("Broadcast failure: " + error);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
