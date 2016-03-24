package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@NamedQuery(name = "AddressEntity.findAll", query = "Select a From AddressEntity a")
public class AddressEntity extends TraceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1088997403290097984L;

	@Column(name = "house_number", length = 45)
	private String houseNumber;
	@Column(name = "street", length = 200)
	private String street;
	@Column(name = "hamlet", length = 45)
	private String hamlet;
	@Column(name = "ward", length = 45)
	private String ward;
	@Column(name = "district", length = 45)
	private String district;
	@Column(name = "city", length = 45)
	private String city;

	@Column(name = "longtitude", nullable = true)
	private Double longtitude;
    @Column(name = "latitude", nullable = true)
    private Double latitude;
    
    

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
