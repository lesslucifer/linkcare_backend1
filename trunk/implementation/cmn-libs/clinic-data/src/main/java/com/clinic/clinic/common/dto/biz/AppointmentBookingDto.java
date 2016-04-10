package com.clinic.clinic.common.dto.biz;

import java.time.LocalDate;

import com.clinic.clinic.common.dto.TraceDto;

public final class AppointmentBookingDto extends TraceDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7956521828569668258L;
	
	private TraceDto booker;
	private TraceDto medicar;
	private boolean isAtHome;
	private AddressDto address;
	private LocalDate date;
	private int time;
	private int duration;
	private int status;
	private AppointmentPatientDto patient;

	public TraceDto getBooker() {
		return booker;
	}
	
	public void setBooker(TraceDto booker) {
		this.booker = booker;
	}
	
	public TraceDto getMedicar() {
		return medicar;
	}
	
	public void setMedicar(TraceDto medicar) {
		this.medicar = medicar;
	}
	
	public boolean isAtHome() {
		return isAtHome;
	}
	
	public void setAtHome(boolean isAtHome) {
		this.isAtHome = isAtHome;
	}
	
	public AddressDto getAddress() {
		return address;
	}
	
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public AppointmentPatientDto getPatient() {
		return patient;
	}
	
	public void setPatient(AppointmentPatientDto patient) {
		this.patient = patient;
	}
}