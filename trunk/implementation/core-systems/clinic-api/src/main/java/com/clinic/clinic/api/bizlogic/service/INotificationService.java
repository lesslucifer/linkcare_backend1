package com.clinic.clinic.api.bizlogic.service;

import java.util.List;

import com.clinic.clinic.common.dto.biz.NotificationDto;

public interface INotificationService {
	static final String USER_APP = "LINKCARE";
	static final String DOCTOR_APP = "LINKCAREP"; 
	
	List<Integer> getNotifications(Integer accountId);
	List<NotificationDto> getNotifications(Integer accountId, List<Integer> notifs);
	void setNotificationsRead(Integer accountId, List<Integer> notifs);
	void sendMessage(String app, Integer sender, Integer receiver, Integer type, String content, Object... params);
}
