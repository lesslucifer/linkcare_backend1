package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescription_medicine")
@NamedQuery(name = "PrescriptionMedicineEntity.findAll", query = "Select a From PrescriptionMedicineEntity a")
public class PrescriptionMedicineEntity extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6402967410615972143L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription_id")
	private PrescriptionEntity prescription;
	
	@Column
	private String name;

	@Column(name = "quant_morning")
	private int quantityMorning;
	
	@Column(name = "quant_noon")
	private int quantityNoon;
	
	@Column(name = "quant_afternoon")
	private int quantityAfternoon;

	@Column(name = "quant_night")
	private int quantityNight;

	public PrescriptionEntity getPrescription() {
		return prescription;
	}

	public void setPrescription(PrescriptionEntity prescription) {
		this.prescription = prescription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantityMorning() {
		return quantityMorning;
	}

	public void setQuantityMorning(int quantityMorning) {
		this.quantityMorning = quantityMorning;
	}

	public int getQuantityNoon() {
		return quantityNoon;
	}

	public void setQuantityNoon(int quantityNoon) {
		this.quantityNoon = quantityNoon;
	}

	public int getQuantityAfternoon() {
		return quantityAfternoon;
	}

	public void setQuantityAfternoon(int quantityAfternoon) {
		this.quantityAfternoon = quantityAfternoon;
	}

	public int getQuantityNight() {
		return quantityNight;
	}

	public void setQuantityNight(int quantityNight) {
		this.quantityNight = quantityNight;
	}
}
