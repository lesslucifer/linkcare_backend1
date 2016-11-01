package com.clinic.clinic.common.dto.biz;

import java.time.LocalDate;

import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.NotNull;

public class AppointmentBookingRequestDto {
	@NotNull
	private Integer medicar;
	@NotNull
	private Boolean atHome;
	@NotNull
	private LocalDate date;
	@NotNull
	private Integer subCategory;
	@NotNull
	private Integer time;

	@NotNull
	@AssertValid
	private AppointmentPatientDto patient;
	
	public int getMedicar() {
		return medicar;
	}
	
	public void setMedicar(int medicar) {
		this.medicar = medicar;
	}
	
//	public boolean isAtHome() {
//		return atHome;
//	}
	
	public void setAtHome(boolean atHome) {
		this.atHome = atHome;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}

	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public AppointmentPatientDto getPatient() {
		return patient;
	}
	
	public void setPatient(AppointmentPatientDto patient) {
		this.patient = patient;
	}
}
