package com.clinic.clinic.api.bizlogic.service;

import com.clinic.clinic.common.dto.biz.MedicarProfileDto;

public interface IMedicarProfileService {
	MedicarProfileDto getMedicarProfile(Integer accountId);
	void updateMedicarProfile(Integer accountId, MedicarProfileDto dto);
}
