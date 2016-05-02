package com.clinic.clinic.api.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
@NamedQuery(name = "NotificationEntity.findAll", query = "Select a From NotificationEntity a")
public class NotificationEntity extends IdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2670418326745850980L;

	public static final int TYPE_MSG = 0; // params: []
	public static final int TYPE_APPOINTMENT_BOOKING = 1; // params: [appointment_id, patient_id]
	public static final int TYPE_APPOINTMENT_APPROVED = 2; // params: [appointment_id, doctor_id]
	public static final int TYPE_APPOINTMENT_REJECTED = 3; // params: [appointment_id, doctor_id]
	public static final int TYPE_APPOINTMENT_CANCELLED = 4; // params: [appointment_id, canceller_id, cancellee_id]
	public static final int TYPE_APPOINTMENT_FINISHED = 5; // params: [appointment_id, prescription_id, doctor_id]

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private AccountEntity owner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender", nullable = true)
	private AccountEntity sender;
	
	@Column
	private String content;
	
	@Column
	private String params;
	
	@Column
	private int type;
	
	@Column
	private LocalDateTime time;
	
	@Column(name = "is_read")
	private boolean isRead;

	public AccountEntity getOwner() {
		return owner;
	}

	public void setOwner(AccountEntity owner) {
		this.owner = owner;
	}

	public AccountEntity getSender() {
		return sender;
	}

	public void setSender(AccountEntity sender) {
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
