package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

public class PrescriptionMedicineDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1127337757079761252L;

	@NotNull
	private String name;

	@NotNull
	private Integer quantityMorning;

	@NotNull
	private Integer quantityNoon;

	@NotNull
	private Integer quantityAfternoon;

	@NotNull
	private Integer quantityNight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantityMorning() {
		return quantityMorning;
	}

	public void setQuantityMorning(Integer quantityMorning) {
		this.quantityMorning = quantityMorning;
	}

	public Integer getQuantityNoon() {
		return quantityNoon;
	}

	public void setQuantityNoon(Integer quantityNoon) {
		this.quantityNoon = quantityNoon;
	}

	public Integer getQuantityAfternoon() {
		return quantityAfternoon;
	}

	public void setQuantityAfternoon(Integer quantityAfternoon) {
		this.quantityAfternoon = quantityAfternoon;
	}

	public Integer getQuantityNight() {
		return quantityNight;
	}

	public void setQuantityNight(Integer quantityNight) {
		this.quantityNight = quantityNight;
	}
}
