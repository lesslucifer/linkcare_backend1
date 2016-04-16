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
	
	@Column(name = "quant_total")
	private int quantityTotal;
	
	@Column
	private String instr;

	@Column(name = "quant_morning")
	private double quantityMorning;
	
	@Column(name = "quant_noon")
	private double quantityNoon;
	
	@Column(name = "quant_afternoon")
	private double quantityAfternoon;

	@Column(name = "quant_night")
	private double quantityNight;

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

	public int getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(int quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public String getInstr() {
		return instr;
	}

	public void setInstr(String instr) {
		this.instr = instr;
	}

	public double getQuantityMorning() {
		return quantityMorning;
	}

	public void setQuantityMorning(double quantityMorning) {
		this.quantityMorning = quantityMorning;
	}

	public double getQuantityNoon() {
		return quantityNoon;
	}

	public void setQuantityNoon(double quantityNoon) {
		this.quantityNoon = quantityNoon;
	}

	public double getQuantityAfternoon() {
		return quantityAfternoon;
	}

	public void setQuantityAfternoon(double quantityAfternoon) {
		this.quantityAfternoon = quantityAfternoon;
	}

	public double getQuantityNight() {
		return quantityNight;
	}

	public void setQuantityNight(double quantityNight) {
		this.quantityNight = quantityNight;
	}
}
