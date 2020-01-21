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

	@Column(name = "address")
	private String address;

	@Column(name = "longtitude", nullable = true)
	private Double longtitude;
    @Column(name = "latitude", nullable = true)
    private Double latitude;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
