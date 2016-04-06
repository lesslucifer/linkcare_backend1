package com.clinic.clinic.api.persistence.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_booking")
@NamedQuery(name = "AppointmentBookingEntity.findAll", query = "Select a From AppointmentBookingEntity a")
public final class AppointmentBookingEntity extends TraceEntity implements Comparable<AppointmentBookingEntity> {

	public static final int STATUS_WAITING = 0;
	public static final int STATUS_APPROVED = 1;
	public static final int STATUS_REJECTED = 2;
	public static final int STATUS_PROCESSING = 3;
	public static final int STATUS_CANCELLED = 4;
	public static final int STATUS_FINISHED = 5;
	public static final int STATUS_RATED = 6;
	
	public static final List<Integer> ACTIVE_STATUSES = Collections.unmodifiableList(
			Arrays.asList(STATUS_WAITING, STATUS_APPROVED, STATUS_PROCESSING));
	public static final List<Integer> INACTIVE_STATUSES = Collections.unmodifiableList(
			Arrays.asList(STATUS_REJECTED, STATUS_CANCELLED, STATUS_FINISHED, STATUS_RATED));
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3534479390240276585L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booker")
	private AccountEntity booker;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medicar")
	private AccountEntity medicar;
	
	@Column(name = "home")
	private boolean isAtHome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	private AddressEntity address;
	
	@Column
	private int cost;

	@Column
	private LocalDate date;
	
	@Column
	private int time;
	
	@Column
	private int duration;
	
	@Column
	private int status;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "appointmentBooking")
	private AppointmentPatientEntity patient;

	public AccountEntity getBooker() {
		return booker;
	}

	public void setBooker(AccountEntity booker) {
		this.booker = booker;
	}
	
	public AccountEntity getMedicar() {
		return medicar;
	}

	public void setMedicar(AccountEntity medicar) {
		this.medicar = medicar;
	}

	public boolean isAtHome() {
		return isAtHome;
	}

	public void setAtHome(boolean isAtHome) {
		this.isAtHome = isAtHome;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
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

	public int getEnd() {
		return time + duration;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public AppointmentPatientEntity getPatient() {
		return patient;
	}

	public void setPatient(AppointmentPatientEntity patient) {
		this.patient = patient;
	}

	@Override
	public int compareTo(AppointmentBookingEntity o) {
		int ret = date.compareTo(o.date);
		if (ret != 0) {
			return ret;
		}
		
		ret = Integer.compare(time, o.time);
		if (ret != 0) {
			return ret;
		}
		
		return Integer.compare(duration, o.duration);
	}
}
