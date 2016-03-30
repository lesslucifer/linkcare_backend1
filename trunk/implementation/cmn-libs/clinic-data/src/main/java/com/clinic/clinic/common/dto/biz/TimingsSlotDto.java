package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;

public class TimingsSlotDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1986793525955773900L;
	
	private int time;
	private boolean available;
	
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
