package com.clinic.clinic.api.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
	
	@Column(name = "begin_date", nullable = false)
	private LocalDate beginDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTimings")
	private List<TimingsEntity> timings = new ArrayList<TimingsEntity>();

	public LocalDate getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

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
