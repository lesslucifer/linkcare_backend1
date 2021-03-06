/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2016 CLINIC JSC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 *
 * This software is the confidential and proprietary information of 
 * clinic J.S.C ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license  
 * agreement, nondisclosure agreement or contract entered into with 
 * clinic providing access to this software.
 *
 * Project name  : clinic-api<br>
 * File name     : AccountServiceImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IAccountService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AddressEntity;
import com.clinic.clinic.api.persistence.entity.RoleEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAddressRepository;
import com.clinic.clinic.api.persistence.repository.IRoleRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.AccountTranslatorImpl;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.dto.biz.AccountCustomDto;
import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.dto.biz.AccountFilterDto;
import com.clinic.clinic.common.dto.biz.UserProfileDto;
import com.clinic.clinic.common.dto.biz.UserRegisterDto;
import com.clinic.clinic.common.exception.BizlogicException;
import com.clinic.clinic.common.utils.StringUtil;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
@ApplicationService
public class AccountServiceImpl extends AbsService implements IAccountService {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    
    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private IRoleRepository roleRepo;
    @Autowired
    private IAddressRepository addressRepo;
    
    private ITranslator<AccountDto, AccountEntity> accountTrans = new AccountTranslatorImpl();

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAccountService#getAccountByMajor(org.springframework.data.domain.Pageable, java.lang.String)
     */
    @Override
    public Page<AccountDto> getAccountBySubcategory(Pageable range, Integer subcategoryId) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<AccountDto> retValue = null;
        try {
            List<AccountEntity> ents = accountRepo.findAccountBySubcategoryId(range, subcategoryId).getContent();
            retValue = new PageImpl<>(accountTrans.getDtoList(ents));
        } catch (BizlogicException be) {
            LOGGER.error("Error", be);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retValue;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAccountService#getAccountAndClinic(com.clinic.clinic.common.dto.biz.AccountFilterDto)
     */
    @Override
    public Page<AccountCustomDto> getAccountAndClinic(Pageable range, AccountFilterDto accountfilterDto) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<AccountCustomDto> retValue = null;
        try {
            List<AccountCustomDto> dtos = accountRepo.findAccountAndClinic(range, accountfilterDto).getContent();
            retValue = new PageImpl<AccountCustomDto>(dtos);
        } catch (BizlogicException be) {
            LOGGER.error("Error", be);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retValue;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAccountService#getAccountAndHome(com.clinic.clinic.common.dto.biz.AccountFilterDto)
     */
    @Override
    public Page<AccountCustomDto> getAccountAndHome(Pageable range, AccountFilterDto accountfilterDto) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<AccountCustomDto> retValue = null;
        try {
            List<AccountCustomDto> dtos = accountRepo.findAccountAndHome(range, accountfilterDto).getContent();
            retValue = new PageImpl<AccountCustomDto>(dtos);
        } catch (BizlogicException be) {
            LOGGER.error("Error", be);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retValue;
        
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAccountService#login(com.clinic.clinic.common.dto.biz.AccountDto)
     */
    @Override
    public AccountDto login(String loginName, String pass) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        AccountDto retDto = null;
        try {
            AccountEntity ent = accountRepo.findFirstEntity(IDbConstants.FIELD_ACC_ACCOUNT_LOGIN_NAME, loginName, false);
            if(null != ent) {
                if(ent.getHashedPassword().equals(StringUtil.getHashedText(pass))) {
                    retDto = accountTrans.getDto(ent); 
                }
            }
            
        } catch (BizlogicException be) {
            LOGGER.error("error", be);
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retDto;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAccountService#getProfile(java.lang.String)
     */
    @Override
    public UserProfileDto getProfile(Integer accountId) throws BizlogicException {
        
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        UserProfileDto retDto = null;
        try {
            AccountEntity ent = accountRepo.getOne(accountId);
            retDto = new UserProfileDto();
            retDto.setId(ent.getId());
            String name = ent.getLastName() + " " + ent.getFirstName();
            if(null != ent.getMidleName()) {
                name = ent.getLastName() + " " + ent.getMidleName() + " " + ent.getFirstName();
            }
            retDto.setFullName(name);
            retDto.setIdCard(ent.getIdCard());
            retDto.setAvatar(ent.getAvatar());
            
            retDto.setBirthDay(LocalDateTime.ofInstant(Instant.ofEpochMilli(ent.getBirthday()), TimeZone
                    .getDefault().toZoneId()).toLocalDate());
            
            retDto.setSex(Short.parseShort(ent.getGender().toString()));
            
            retDto.setPhoneNumber(ent.getPhoneNumber());
            retDto.setEmail(ent.getEmail());
            
            if(null != ent.getAddress()) {
                retDto.setAddress(ent.getAddress().getAddress());
                retDto.setLatitude(ent.getAddress().getLatitude());
                retDto.setLongtitude(ent.getAddress().getLongtitude());
            }
        } catch (BizlogicException be) {
            LOGGER.error("error", be);
            throwBizlogicException(500, IBizErrorCode.ADDRESS, "less one field null", be.getMessage());
        } catch (Exception e) {
            LOGGER.error("error", e);
            throwBizlogicException(500, IBizErrorCode.UNKNOWN_ERROR, e.getMessage());
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retDto;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAccountService#userRegister(com.clinic.clinic.common.dto.biz.UserRegisterDto)
     */
    @Override
    public AccountEntity userRegister(UserRegisterDto userRegister) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        try {
            AccountEntity a = accountRepo.findAccountByLoginName(userRegister.getLoginName());
            if(a == null) {
                AccountEntity a2 = accountRepo.findAccountByEmail(userRegister.getEmail());
                if(a2 == null){
                    AccountEntity accountEnt = new AccountEntity();
                    // LoginName
                    accountEnt.setLoginName(userRegister.getLoginName());
                    accountEnt.setCode(getUUID());
                    // Email
                    accountEnt.setEmail(userRegister.getEmail());
                    accountEnt.setActiveFlag(IConstants.ACCOUNT_ACTIVED_FLAG);
                    // Phone number
                    accountEnt.setPhoneNumber(userRegister.getPhoneNumber());
                    // Name
                    accountEnt.setFirstName(userRegister.getFirstName());
                    accountEnt.setLastName(userRegister.getLastName());
                    accountEnt.setMidleName(userRegister.getMidleName());
                    accountEnt.setGender(userRegister.getGender());
                    // Todo Convert Birthday
                    if(null != userRegister.getBirthday() && userRegister.getBirthday().length() > 0) {
                        Long birthdayInLong = this.parsesDatetime(userRegister.getBirthday());
                        if(birthdayInLong == null) {
                            throwBizlogicException(500, IBizErrorCode.BIRTHDAY_WRONG, "Birthday error format", "dd-MM-yyyy");
                        }
                        accountEnt.setBirthday(birthdayInLong);
                    }
                    accountEnt.setIdCard(userRegister.getIdCard());
                    if(null != userRegister.getPassport() && !userRegister.getPassport().isEmpty()) {
                        accountEnt.setPassport(userRegister.getPassport());
                    }
                    accountEnt.setHashedPassword(StringUtil.getHashedText(userRegister.getPassword()));
                    
                    accountEnt.setIsDeleted(IDbConstants.FALSE);
                    
                    accountEnt.setBeginActiveTime(System.currentTimeMillis());
                    
                    accountEnt.setCreatedDatetime(System.currentTimeMillis());
                    accountEnt.setLastUpdated(System.currentTimeMillis());
                    
                    AddressEntity addressEnt = new AddressEntity();
                    if(null != userRegister.getAddress()) {
                        addressEnt.setAddress(userRegister.getAddress());
                    }
                    if(null != userRegister.getLatitude()) {
                        addressEnt.setLatitude(userRegister.getLatitude());
                    }
                    if(null != userRegister.getLongtitude()) {
                        addressEnt.setLongtitude(userRegister.getLongtitude());
                    }
                    addressEnt = addressRepo.save(addressEnt);
                    
                    accountEnt.setAddress(addressEnt);
                    
                    RoleEntity roleEnt = roleRepo.findByCode(IDbConstants.ACCOUNT_PATIENT_ROLE, IDbConstants.FALSE);
                    
                    if(!roleRepo.exists(roleEnt.getId())) {
                        throwBizlogicException(500, IBizErrorCode.ACCOUNT_EXISTS, "Not role patient");
                    }
                    
                    List<RoleEntity> ls = new ArrayList<RoleEntity>();
                    
                    ls.add(roleEnt);
                    accountEnt.setRoles(ls);
                    
                    return accountRepo.save(accountEnt);
                } else {
                    throwBizlogicException(500, IBizErrorCode.ACCOUNT_EMAIL_EXISTS, "Email exists", userRegister.getEmail());
                }
            } else {
                throwBizlogicException(500, IBizErrorCode.ACCOUNT_EXISTS, "Login name exists", userRegister.getLoginName());
            }
            
        } catch (BizlogicException be) {
            throwBizlogicException(500, IBizErrorCode.ACCOUNT_PATIENT_REGISTER, be.toString());
        } catch (Exception e) {
            throwBizlogicException(500, IBizErrorCode.UNKNOWN_ERROR, "Unknown error", e.toString());
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        return null;
    }
    
    private Long parsesDatetime(String birthday) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatter.parse(birthday);
        } catch (ParseException e) {
            LOGGER.error("Exception", e);
            throwBizlogicException(500, IBizErrorCode.BIRTHDAY_WRONG, "Birthday error format", "dd-MM-yyyy");
        }
        long dateInLong = date.getTime();
        return dateInLong;
    }
}
