package com.clinic.clinic.api.bizlogic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IAccountService;
import com.clinic.clinic.api.bizlogic.service.IMedicarProfileService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AddressEntity;
import com.clinic.clinic.api.persistence.entity.MedicarProfileEntity;
import com.clinic.clinic.api.persistence.entity.PlaceEntity;
import com.clinic.clinic.api.persistence.entity.RoleEntity;
import com.clinic.clinic.api.persistence.entity.SubcategoryEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAddressRepository;
import com.clinic.clinic.api.persistence.repository.IMedicarProfileRepository;
import com.clinic.clinic.api.persistence.repository.IPlaceRepository;
import com.clinic.clinic.api.persistence.repository.IRoleRepository;
import com.clinic.clinic.api.persistence.repository.ISubcategoryRepository;
import com.clinic.clinic.api.translator.impl.MedicarProfileTranslatorImpl;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.dto.biz.MedicarProfileDto;
import com.clinic.clinic.common.dto.biz.MedicarRegisterDto;
import com.clinic.clinic.common.dto.biz.PlaceRegisterDto;

@ApplicationService
public class MedicarProfileServiceImpl extends AbsService implements IMedicarProfileService {

	@Autowired
	IAccountService accServ;
	
	@Autowired
	ISubcategoryRepository subcateRepo;
	
	@Autowired
	IAddressRepository addressRepo;
	
	@Autowired
	IPlaceRepository placeRepo;
	
	@Autowired
	IRoleRepository roleRepo;
	
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
	
	@Override
	public void registerMedicar(String type, MedicarRegisterDto dto) {
		SubcategoryEntity subcate = subcateRepo.findOne(dto.getSubcategory());
		if (subcate == null) {
			throwBizlogicException(IBizErrorCode.OBJECT_NOT_FOUND, String.format("Subcategory %d not found", dto.getSubcategory()));
		}
		
		RoleEntity role = roleRepo.findByCode(type, false);
		if (role == null) {
			throwBizlogicException(IBizErrorCode.OBJECT_NOT_FOUND, String.format("Role %s are invalid", type));
		}
		
		AccountEntity acc = accServ.userRegister(dto.getUserProfile());
		if (acc == null) {
			throwBizlogicException("Cannot create user");
		}
		
		acc.setSubcategory(subcate);
		acc.getRoles().add(role);
		accRepo.save(acc);
		
		this.updateMedicarProfile(acc.getId(), dto.getMedicarProfile());
		
		PlaceRegisterDto clinic = dto.getClinic();
		if (clinic != null) {
			AddressEntity clinicAddress = new AddressEntity();
			clinicAddress.setAddress(clinic.getAddress());
			clinicAddress.setLongtitude(clinic.getLongitude());
			clinicAddress.setLatitude(clinic.getLatitude());
			clinicAddress = addressRepo.save(clinicAddress);
			
			PlaceEntity place = new PlaceEntity();
			place.setAddress(clinicAddress);
			place.setName(clinic.getName());
			place.setDescription(clinic.getDescription());
			place.setPhoneNumber(clinic.getPhoneNumber());
			place.setEmail(clinic.getEmail());
			place.setWebsite(clinic.getWebsite());
			place = placeRepo.save(place);
			
			acc.setPlace(place);
			accRepo.save(acc);
		}
	}
}
