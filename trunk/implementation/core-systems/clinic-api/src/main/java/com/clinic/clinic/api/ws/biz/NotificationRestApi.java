package com.clinic.clinic.api.ws.biz;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.INotificationService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.utils.Utils;

import net.sf.oval.constraint.NotNull;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class NotificationRestApi extends AbsRestApi {
	
	@Autowired
	INotificationService notifServ;
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_NOTIFICATIONS, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getAccountNotifications(@RequestHeader("sess") String session,
    		HttpServletResponse response) {
		Integer accountId = auth().authSession(session);
		
		return notifServ.getNotifications(accountId);
	}
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_NOTIFICATIONS_SINGLE, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getAccountNotificationsById(@RequestHeader("sess") String session,
			@PathVariable("notif_id") String notifIds,
    		HttpServletResponse response) {
		Integer accountId = auth().authSession(session);
		
		List<Integer> ids = Arrays.stream(notifIds.split(","))
				.map(Integer::parseInt).collect(Collectors.toList());
		
		return notifServ.getNotifications(accountId, ids);
	}
	

	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_NOTIFICATIONS_SINGLE_READ, method = RequestMethod.PUT, produces = {
    "application/json" })
	public Object setAccountNotificationsRead(@RequestHeader("sess") String session,
			@PathVariable("notif_id") String notifIds,
    		HttpServletResponse response) {
		Integer accountId = auth().authSession(session);
		
		List<Integer> ids = Arrays.stream(notifIds.split(","))
				.map(Integer::parseInt).collect(Collectors.toList());
		
		notifServ.setNotificationsRead(accountId, ids);
		return Utils.mkMap();
	}
	
	// for testing only
	public static class NotificationDto {
		@NotNull
		public String receiver_login_name;
		@NotNull
		public String content;
	}
	
	@Autowired
	IAccountRepository accRepo;
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_NOTIFICATIONS, method = RequestMethod.POST, produces = {
    "application/json" })
	public Object sendNotification(@RequestHeader("sess") String session,
			@RequestBody NotificationDto dto,
    		HttpServletResponse response) {
		
		validate(dto);
		Integer accountId = auth().authSession(session);
		
		AccountEntity receiver = accRepo.findAccountByLoginName(dto.receiver_login_name);
		if (receiver != null) {
			notifServ.sendMessage(accountId, receiver.getId(), NotificationEntity.TYPE_MSG, dto.content);
		}
		
		return Utils.mkMap();
	}
}
