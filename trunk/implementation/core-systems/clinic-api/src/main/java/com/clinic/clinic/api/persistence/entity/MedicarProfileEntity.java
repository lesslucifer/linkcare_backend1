package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicar_profile")
@NamedQuery(name = "MedicarProfileEntity.findAll", query = "Select a From MedicarProfileEntity a")
public class MedicarProfileEntity extends TraceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3390975997062609293L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private AccountEntity account;
	
	@Column(name = "license_number", length = 256)
	private String licenseNumber;

	@Column(name = "graduated_school", nullable = false)
	private String graduatedSchool;

	@Column(name = "graduated_year", nullable = false)
	private Integer graduatedYear;

	@Column(name = "graduated_level", nullable = false)
	private Integer graduatedLevel;

	@Column(name = "master_school", nullable = true)
	private String masterSchool;

	@Column(name = "master_year", nullable = true)
	private Integer masterYear;

	@Column(name = "master_type", nullable = true)
	private Integer masterType;

	@Column(name = "working_place", nullable = true)
	private String workingPlace;

	@Column(name = "study", nullable = true)
	private String study;
	
	@Column(name = "clinic_price")
	private int clinicPrice;
	
	@Column(name = "patient_home_price")
	private int patientHomePrice;

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getGraduatedSchool() {
		return graduatedSchool;
	}

	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}

	public Integer getGraduatedYear() {
		return graduatedYear;
	}

	public void setGraduatedYear(Integer graduatedYear) {
		this.graduatedYear = graduatedYear;
	}

	public Integer getMasterYear() {
		return masterYear;
	}

	public void setMasterYear(Integer masterYear) {
		this.masterYear = masterYear;
	}

	public Integer getGraduatedLevel() {
		return graduatedLevel;
	}

	public void setGraduatedLevel(Integer graduatedLevel) {
		this.graduatedLevel = graduatedLevel;
	}

	public String getMasterSchool() {
		return masterSchool;
	}

	public void setMasterSchool(String masterSchool) {
		this.masterSchool = masterSchool;
	}

	public Integer getMasterType() {
		return masterType;
	}

	public void setMasterType(Integer masterType) {
		this.masterType = masterType;
	}

	public String getWorkingPlace() {
		return workingPlace;
	}

	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public int getClinicPrice() {
		return clinicPrice;
	}

	public void setClinicPrice(int clinicPrice) {
		this.clinicPrice = clinicPrice;
	}

	public int getPatientHomePrice() {
		return patientHomePrice;
	}

	public void setPatientHomePrice(int patientHomePrice) {
		this.patientHomePrice = patientHomePrice;
	}
}
