package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

public class MedicarRegisterDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8170111922765950031L;
	
	@NotNull
	private UserRegisterDto userProfile;

	@NotNull
	private MedicarProfileDto medicarProfile;
	
	private PlaceRegisterDto clinic;
	
	@NotNull
	private Integer subcategory;
	
	@NotNull
	private String referrer;
	
	public UserRegisterDto getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserRegisterDto userProfile) {
		this.userProfile = userProfile;
	}
	public MedicarProfileDto getMedicarProfile() {
		return medicarProfile;
	}
	public void setMedicarProfile(MedicarProfileDto medicarProfile) {
		this.medicarProfile = medicarProfile;
	}
	public Integer getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Integer subcategory) {
		this.subcategory = subcategory;
	}
	public PlaceRegisterDto getClinic() {
		return clinic;
	}
	public void setClinic(PlaceRegisterDto clinic) {
		this.clinic = clinic;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
}