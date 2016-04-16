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
	private Integer quantityTotal;
	
	@NotNull
	private String instr;

	@NotNull
	private Double quantityMorning;

	@NotNull
	private Double quantityNoon;

	@NotNull
	private Double quantityAfternoon;

	@NotNull
	private Double quantityNight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(Integer quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public String getInstr() {
		return instr;
	}

	public void setInstr(String instr) {
		this.instr = instr;
	}

	public Double getQuantityMorning() {
		return quantityMorning;
	}

	public void setQuantityMorning(Double quantityMorning) {
		this.quantityMorning = quantityMorning;
	}

	public Double getQuantityNoon() {
		return quantityNoon;
	}

	public void setQuantityNoon(Double quantityNoon) {
		this.quantityNoon = quantityNoon;
	}

	public Double getQuantityAfternoon() {
		return quantityAfternoon;
	}

	public void setQuantityAfternoon(Double quantityAfternoon) {
		this.quantityAfternoon = quantityAfternoon;
	}

	public Double getQuantityNight() {
		return quantityNight;
	}

	public void setQuantityNight(Double quantityNight) {
		this.quantityNight = quantityNight;
	}
}
