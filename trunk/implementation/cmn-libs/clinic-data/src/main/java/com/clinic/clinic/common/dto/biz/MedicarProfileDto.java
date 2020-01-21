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
	
	@NotNull
	private String graduatedSchool = "";

	@NotNull
	private Integer graduatedYear = 1990;

	@NotNull
	private Integer graduatedLevel = 0;

	private String masterSchool = null;

	private Integer masterYear = null;

	private Integer masterType = null;

	private String workingPlace = null;

	private String study = null;

	private Long expiredTime;

	private Integer overloadedAppointments;
	
	private String referrer;

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

	public Integer getMasterYear() {
		return masterYear;
	}

	public void setMasterYear(Integer masterYear) {
		this.masterYear = masterYear;
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

	public Long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getOverloadedAppointments() {
		return overloadedAppointments;
	}

	public void setOverloadedAppointments(Integer overloadedAppointments) {
		this.overloadedAppointments = overloadedAppointments;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
}
