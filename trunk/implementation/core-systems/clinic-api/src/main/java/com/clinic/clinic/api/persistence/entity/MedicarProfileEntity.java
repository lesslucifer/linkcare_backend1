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

	@Column(name = "graduated_school", nullable = true)
	private String graduatedSchool;

	@Column(name = "graduated_year", nullable = true)
	private Integer graduatedYear;

	@Column(name = "master_year", nullable = true)
	private Integer masterYear;

	@Column(name = "master_spec", nullable = true)
	private String masterSpec;

	@Column(name = "doctor_year", nullable = true)
	private Integer doctorYear;

	@Column(name = "doctor_spec", nullable = true)
	private String doctorSpec;

	@Column(name = "specialist_1_year", nullable = true)
	private Integer specialist1Year;

	@Column(name = "specialist_1_spec", nullable = true)
	private String specialist1Spec;

	@Column(name = "specialist_2_year", nullable = true)
	private Integer specialist2Year;

	@Column(name = "specialist_2_spec", nullable = true)
	private String specialist2Spec;

	@Column(name = "other_specialist_year", nullable = true)
	private Integer otherSpecialistYear;

	@Column(name = "other_specialist_spec", nullable = true)
	private String otherSpecialistSpec;

	@Column(name = "other_training", nullable = true)
	private String otherTraining;
	
	@Column(name = "level")
	private int level;
	
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

	public String getMasterSpec() {
		return masterSpec;
	}

	public void setMasterSpec(String masterSpec) {
		this.masterSpec = masterSpec;
	}

	public Integer getDoctorYear() {
		return doctorYear;
	}

	public void setDoctorYear(Integer doctorYear) {
		this.doctorYear = doctorYear;
	}

	public String getDoctorSpec() {
		return doctorSpec;
	}

	public void setDoctorSpec(String doctorSpec) {
		this.doctorSpec = doctorSpec;
	}

	public Integer getSpecialist1Year() {
		return specialist1Year;
	}

	public void setSpecialist1Year(Integer specialist1Year) {
		this.specialist1Year = specialist1Year;
	}

	public String getSpecialist1Spec() {
		return specialist1Spec;
	}

	public void setSpecialist1Spec(String specialist1Spec) {
		this.specialist1Spec = specialist1Spec;
	}

	public Integer getSpecialist2Year() {
		return specialist2Year;
	}

	public void setSpecialist2Year(Integer specialist2Year) {
		this.specialist2Year = specialist2Year;
	}

	public String getSpecialist2Spec() {
		return specialist2Spec;
	}

	public void setSpecialist2Spec(String specialist2Spec) {
		this.specialist2Spec = specialist2Spec;
	}

	public Integer getOtherSpecialistYear() {
		return otherSpecialistYear;
	}

	public void setOtherSpecialistYear(Integer otherSpecialistYear) {
		this.otherSpecialistYear = otherSpecialistYear;
	}

	public String getOtherSpecialistSpec() {
		return otherSpecialistSpec;
	}

	public void setOtherSpecialistSpec(String otherSpecialistSpec) {
		this.otherSpecialistSpec = otherSpecialistSpec;
	}

	public String getOtherTraining() {
		return otherTraining;
	}

	public void setOtherTraining(String otherTraining) {
		this.otherTraining = otherTraining;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
