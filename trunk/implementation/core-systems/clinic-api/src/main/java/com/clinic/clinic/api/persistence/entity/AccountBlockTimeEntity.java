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
@Table(name = "account_block_time")
@NamedQuery(name = "AccountBlockTimeEntity.findAll", query = "Select a From AccountBlockTimeEntity a")
public final class AccountBlockTimeEntity extends DeleteableEntity implements Comparable<AccountBlockTimeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1738901393212746205L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account;

	@Column(name = "begin", nullable = false)
	private LocalDateTime beginDateTime;

	@Column(name = "length", nullable = false)
	private int lengthInMinutes;

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public LocalDateTime getEndDateTime() {
		return beginDateTime.plusMinutes(this.lengthInMinutes);
	}

	public LocalDateTime getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(LocalDateTime beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	@Override
	public int compareTo(AccountBlockTimeEntity bt) {
		int ret = beginDateTime.compareTo(bt.beginDateTime);
		if (ret != 0) {
			return ret;
		}
		
		return Integer.compare(lengthInMinutes, bt.lengthInMinutes);
	}

	@Override
	public String toString() {
		return "AccountBlockTimeEntity [beginDateTime=" + beginDateTime + ", endDateTime=" + getEndDateTime() + "]";
	}
}
