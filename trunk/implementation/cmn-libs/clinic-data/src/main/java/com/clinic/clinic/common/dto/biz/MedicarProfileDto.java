package com.clinic.clinic.common.dto.biz;

import com.clinic.clinic.common.dto.TraceDto;

import net.sf.oval.constraint.NotNull;

public class MedicarProfileDto extends TraceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4994991275299643198L;

	@NotNull
	private String licenseNumber = "";
	
	private String graduatedSchool = "";
	
	private Integer graduatedYear = null;

	private Integer masterYear = null;

	private String masterSpec = "";

	private Integer doctorYear = null;

	private String doctorSpec = "";

	private Integer specialist1Year = null;

	private String specialist1Spec = "";

	private Integer specialist2Year = null;

	private String specialist2Spec = "";

	private Integer otherSpecialistYear = null;

	private String otherSpecialistSpec = "";

	private String otherTraining = "";

	@NotNull
	private int level = 0;

	@NotNull
	private int clinicPrice = 0;

	@NotNull
	private int patientHomePrice = 0;

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
