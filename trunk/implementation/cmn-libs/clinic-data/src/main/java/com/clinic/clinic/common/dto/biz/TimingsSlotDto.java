package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;

public class TimingsSlotDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1986793525955773900L;
	
	private int time;
	private boolean available;
	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
