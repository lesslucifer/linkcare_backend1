package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.INotificationService;
import com.clinic.clinic.api.conf.RestApiConf;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.DeviceEntity;
import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IDeviceRepository;
import com.clinic.clinic.api.persistence.repository.INotificationRepository;
import com.clinic.clinic.api.translator.impl.NotificationTranslatorImpl;
import com.clinic.clinic.common.dto.biz.NotificationDto;
import com.clinic.clinic.common.utils.Utils;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

import redis.clients.jedis.Jedis;

@ApplicationService
public class NotificationService extends AbsService implements INotificationService {
	private static Executor EXEC = Executors.newCachedThreadPool();
	private static Jedis jedis;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
	
//	private static final String GCM_API_KEY = "AIzaSyASHqd2szI4Uqhzfr783KnfJONtrjoAat4";
	private static final String GCM_API_KEY = "AIzaSyC1gVSLQMuGFFo2IUtBJpSALWTw3BJVM2Y";

	@Autowired
	INotificationRepository notifRepo;
	
	@Autowired
	IDeviceRepository deviceRepo;
	
	@Autowired
	IAccountRepository accRepo;
	
	public NotificationService() {
		super();
		
		jedis = new Jedis();
	}
	
	@Override
	public List<Integer> getNotifications(Integer accountId) {
		return notifRepo.getNotifications(accountId);
	}

	@Override
	public void sendMessage(String app, Integer sender, Integer receiver, Integer type, String content, Object... params) {
		NotificationEntity entity = new NotificationEntity();
		if (sender != null) {
			entity.setSender(accRepo.getReference(AccountEntity.class, sender));
		}
		
		AccountEntity receiverEnt = accRepo.getOne(receiver);
		String jsonParams = JSONArray.toJSONString(Arrays.asList(params));
		this.sendNotification(app, sender, receiverEnt.getId(), content, type, jsonParams);
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
	
	private void sendNotification(final String app, final Integer sender, final Integer receiver, final String content, final Integer type, final String params) {
		EXEC.execute(() -> {
			String data = JSONObject.toJSONString(Utils.mkMap(
				"app", app,
				"receiver", receiver,
				"sender", sender,
				"content", content,
				"type", type,
				"params", params
			));
			jedis.lpush("lc:q:notif", data);
		});
 	}
	
	private void sendGCMNotification(List<DeviceEntity> devices, String content, Integer type, String params) {
		if (devices.isEmpty()) {
			return;
		}
		
		Sender sender = new Sender(GCM_API_KEY);
		List<String> data = devices.stream().map(d -> d.getDeviceToken()).collect(Collectors.toList());
		
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
			LOGGER.debug(String.format("GCM total: %d; success: %d; failure: %d", result.getTotal(), result.getSuccess(), result.getFailure()));
			
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
	
	private void sendAPNSNotification(List<DeviceEntity> devices, String app, String content, Integer type, String params) {
		String plainContent = content.replaceAll("(\\<[^\\>]*\\>)", "");
		try {
			final String p12FileName = app + ".p12";
			final String p12Path = RestApiConf.getInstance().getApnsP12Dir() + p12FileName;
			LOGGER.error(String.format("SEND APNS for %s; P12 File: %s", devices.toString(), p12Path));

			ApnsService service =
				    APNS.newService()
				    .withCert(p12Path, "c0nc0ndotVN")
				    .withAppleDestination(!RestApiConf.getInstance().isApnsSandbox())
				    .build();
			
			String payload = APNS.newPayload().alertBody(plainContent)
					.sound("default")
					.customField("type", type)
					.customField("params", params)
					.build();
			for (DeviceEntity device : devices) {
				String token = device.getDeviceToken();
				service.push(token, payload);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.toString());
		}
	}
}
