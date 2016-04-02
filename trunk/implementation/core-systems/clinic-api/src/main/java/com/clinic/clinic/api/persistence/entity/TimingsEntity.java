package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "timings")
@NamedQuery(name = "TimingsEntity.findAll", query = "Select a From TimingsEntity a")
public final class TimingsEntity extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1250626562233132119L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_timings_id", nullable = false)
	private AccountTimingsEntity accountTimings;

	@Column(name = "begin", nullable = false)
	private int beginTime;
	
	@Column(name = "length", nullable = false)
	private int length;
	
	// type IAppointmentPosition
	@Column
	private int type;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
	
	public int getEndTime() {
		return beginTime + length;
	}
	
	public String getBeginText() {
		return String.format("%02d:%02d", (getBeginTime() / 60), (getBeginTime() % 60));
	}
	
	public String getEndText() {
		final int endTime = getEndTime();
		return String.format("%02d:%02d", (endTime / 60), (endTime % 60));
	}

	public AccountTimingsEntity getAccountTimings() {
		return accountTimings;
	}

	public void setAccountTimings(AccountTimingsEntity accountTimings) {
		this.accountTimings = accountTimings;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
