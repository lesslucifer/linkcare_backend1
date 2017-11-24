package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "balance")
@NamedQuery(name = "BalanceEntity.findAll", query = "Select b From BalanceEntity b ")
public class BalanceEntity extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3255807303125533137L;
	/**
	 * 
	 */
	
	@Column(name="balance")
	private double balance;

	@Column(name="last_updated")
	private int last_updated;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable = false)
    private AccountEntity account;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(int last_updated) {
		this.last_updated = last_updated;
	}
}
