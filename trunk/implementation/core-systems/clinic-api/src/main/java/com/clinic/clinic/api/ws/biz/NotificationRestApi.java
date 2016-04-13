package com.clinic.clinic.api.ws.biz;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.INotificationService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;

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
}
