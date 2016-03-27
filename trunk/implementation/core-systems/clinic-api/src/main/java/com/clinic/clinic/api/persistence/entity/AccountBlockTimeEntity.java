package com.clinic.clinic.api.persistence.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public final class AccountBlockTimeEntity extends DeleteableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1738901393212746205L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account;

	@Column(name = "begin", nullable = false)
	private Date beginDateTime;

	@Column(name = "end", nullable = false)
	private Date endDateTime;

	@Column(name = "length", nullable = false)
	private int lengthInMinutes;

	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public Date getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(Date beginDateTime) {
		this.beginDateTime = beginDateTime;
	}
}
