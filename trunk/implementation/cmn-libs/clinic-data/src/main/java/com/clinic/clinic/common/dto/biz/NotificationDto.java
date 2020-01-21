package com.clinic.clinic.common.dto.biz;

import java.time.LocalDateTime;

import com.clinic.clinic.common.dto.IdDto;
import com.clinic.clinic.common.dto.TraceDto;

import net.sf.oval.constraint.NotNull;

public class NotificationDto extends IdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7537560744782710751L;

	private TraceDto sender;
	
	@NotNull
	private String content;
	
	private String params;
	
	private LocalDateTime time;

	private int type;
	
	private boolean isRead;

	public TraceDto getSender() {
		return sender;
	}

	public void setSender(TraceDto sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
