package com.clinic.clinic.api.persistence.entity;

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

	public static final int TYPE_MSG = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private AccountEntity owner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender", nullable = true)
	private AccountEntity sender;
	
	@Column
	private String content;
	
	@Column
	private int type;
	
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	
}