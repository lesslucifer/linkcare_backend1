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
	
	private Integer subcategory;
	
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
}