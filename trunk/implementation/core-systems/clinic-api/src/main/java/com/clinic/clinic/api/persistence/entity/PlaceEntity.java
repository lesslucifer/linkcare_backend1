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
@Table(name = "place")
@NamedQuery(name = "PlaceEntity.findAll", query = "Select p From PlaceEntity p")
public class PlaceEntity extends NameCodeDescEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5603418194352203393L;
	@Column(name = "node", nullable = true, length = 1000)
	private String node;
	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;
	@Column(name = "email", nullable = true)
	private String email;
	@Column(name = "website", nullable = true)
	private String website;
	@Column(name = "fax", nullable = true)
	private String fax;
	@Column(name = "daily_open_time", nullable = true)
	private Long dailyOpenTime;
	@Column(name = "daily_close_time", nullable = true)
	private Long dailyCloseTime;
	@Column(name = "image_url", nullable = true)
	private String avatar;
	@Column(name = "status", nullable = true)
	private Boolean status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place" )
	private List<AccountEntity> accounts = new ArrayList<AccountEntity>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	private AddressEntity address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cost_id", nullable = true)
	private CostEntity cost;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getDailyOpenTime() {
		return dailyOpenTime;
	}

	public void setDailyOpenTime(Long dailyOpenTime) {
		this.dailyOpenTime = dailyOpenTime;
	}

	public Long getDailyCloseTime() {
		return dailyCloseTime;
	}

	public void setDailyCloseTime(Long dailyCloseTime) {
		this.dailyCloseTime = dailyCloseTime;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

    public CostEntity getCost() {
        return cost;
    }

    public void setCost(CostEntity cost) {
        this.cost = cost;
    }
}
