package com.clinic.clinic.common.dto.biz;

import java.time.LocalDate;

import net.sf.oval.constraint.NotNull;

public final class AppointmentPatientDto {
	
	@NotNull
	private String name;
	@NotNull
	private LocalDate birth;
	@NotNull
	private String phone;
	@NotNull
	private Byte gender;
	@NotNull
	private String address;
	@NotNull
	private String symtoms;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getBirth() {
		return birth;
	}
	
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte getGender() {
		return gender;
	}
	
	public void setGender(byte gender) {
		this.gender = gender;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getSymtoms() {
		return symtoms;
	}
	
	public void setSymtoms(String symtoms) {
		this.symtoms = symtoms;
	}
}
