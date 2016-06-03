package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;

public class MedicarRegisterDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8170111922765950031L;
	
	private UserRegisterDto userProfile;
	private MedicarProfileDto medicarProfile;
	
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
}
