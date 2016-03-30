package com.clinic.clinic.api.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_patient")
@NamedQuery(name = "AppointmentPatientEntity.findAll", query = "Select a From AppointmentPatientEntity a")
public class AppointmentPatientEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4509118726141944822L;
	
	@Id
	@Column
	private Integer appointment_id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id")
	private AppointmentBookingEntity appointmentBooking;
	
	@Column
	private String name;
	
	@Column
	private LocalDate birth;
	
	@Column
	private byte gender;
	
	@Column
	private String address;
	
	@Column
	private String symtoms;

	public Integer getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(Integer appointment_id) {
		this.appointment_id = appointment_id;
	}

	public AppointmentBookingEntity getAppointmentBooking() {
		return appointmentBooking;
	}

	public void setAppointmentBooking(AppointmentBookingEntity appointmentBooking) {
		this.appointmentBooking = appointmentBooking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSymtoms() {
		return symtoms;
	}

	public void setSymtoms(String symtoms) {
		this.symtoms = symtoms;
	}
}
