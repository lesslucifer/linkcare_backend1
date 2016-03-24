package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "annual_fee")
@NamedQuery(name = "AnnualFeeEntity", query = "Select a From AnnualFeeEntity a")
public class AnnualFeeEntity extends NameEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7986294989513279746L;

	@Column(name = "price", nullable = true)
	private Long price;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annualFee")
	private List<AccountEntity> accounts = new ArrayList<AccountEntity>();
}