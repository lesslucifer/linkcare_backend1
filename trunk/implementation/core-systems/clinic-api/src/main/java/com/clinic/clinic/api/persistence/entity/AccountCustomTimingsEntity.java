package com.clinic.clinic.api.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "custom_timings")
@NamedQuery(name = "AccountCustomTimingsEntity.findAll", query = "Select a From AccountCustomTimingsEntity a")
public class AccountCustomTimingsEntity extends DeleteableEntity {

	private static final long serialVersionUID = -8990499160256200492L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account;
	
	@Column
	private LocalDate date;

	@Column
	private int begin;

	@Column
	private int length;
	
	@Column
	private int end;
	
	// type IAppointmentPosition
	@Column
	private int type;

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
