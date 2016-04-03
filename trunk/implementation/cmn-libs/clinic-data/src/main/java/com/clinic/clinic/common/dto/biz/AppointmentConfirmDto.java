package com.clinic.clinic.common.dto.biz;

import net.sf.oval.constraint.NotNull;

public class AppointmentConfirmDto {
	@NotNull
	private Integer appointment_id;

	public Integer getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(Integer appointment_id) {
		this.appointment_id = appointment_id;
	}
}
