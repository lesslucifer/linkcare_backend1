package com.clinic.clinic.api.bizlogic.service;

import com.clinic.clinic.common.dto.biz.MedicarProfileDto;
import com.clinic.clinic.common.dto.biz.MedicarRegisterDto;

public interface IMedicarProfileService {
	MedicarProfileDto getMedicarProfile(Integer accountId);
	void registerMedicar(String type, MedicarRegisterDto dto);
	void updateMedicarProfile(Integer accountId, MedicarProfileDto dto);
}
