package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account_timings")
@NamedQuery(name = "AccountTimingsEntity.findAll", query = "Select a From AccountTimingsEntity a")
public final class AccountTimingsEntity extends TraceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7170813554554449239L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTimings")
	private List<TimingsEntity> timings = new ArrayList<TimingsEntity>();

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public List<TimingsEntity> getTimings() {
		return timings;
	}

	public void setTimings(List<TimingsEntity> timings) {
		this.timings = timings;
	}
	
}
