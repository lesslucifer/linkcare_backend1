package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TimingsDayDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7787376247510465631L;
	
	private LocalDate day = LocalDate.now();
	private List<TimingsSlotDto> slots = Collections.emptyList();

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}
	
	public List<TimingsSlotDto> getSlots() {
		return slots;
	}
	
	public void setSlots(List<TimingsSlotDto> slots) {
		this.slots = slots;
	}
}
