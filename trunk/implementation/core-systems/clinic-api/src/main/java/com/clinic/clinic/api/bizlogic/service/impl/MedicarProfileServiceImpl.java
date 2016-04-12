package com.clinic.clinic.api.bizlogic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IMedicarProfileService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.MedicarProfileEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IMedicarProfileRepository;
import com.clinic.clinic.api.translator.impl.MedicarProfileTranslatorImpl;
import com.clinic.clinic.common.dto.biz.MedicarProfileDto;

@ApplicationService
public class MedicarProfileServiceImpl extends AbsService implements IMedicarProfileService {

	@Autowired
	IMedicarProfileRepository medicarProfileRepo;
	
	@Autowired
	IAccountRepository accRepo;
	
	@Override
	public MedicarProfileDto getMedicarProfile(Integer accountId) {
		MedicarProfileEntity entity = medicarProfileRepo.getByAccountId(accountId);
		
		if (entity != null) {
			return MedicarProfileTranslatorImpl.INST.getDto(entity);
		}
		
		return new MedicarProfileDto();
	}

	@Override
	public void updateMedicarProfile(Integer accountId, MedicarProfileDto dto) {
		MedicarProfileEntity entity = medicarProfileRepo.getByAccountId(accountId);
		
		if (entity == null) {
			entity = new MedicarProfileEntity();
			entity.setAccount(accRepo.getReference(AccountEntity.class, accountId));
		}
		
		MedicarProfileTranslatorImpl.INST.dtoToEntity(dto, entity);
		medicarProfileRepo.save(entity);
	}
}
