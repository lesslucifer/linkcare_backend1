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
 * File name     : RateServiceImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Apr 10, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IRateService;
import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.entity.RateEntity;
import com.clinic.clinic.api.persistence.repository.IRateRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.AccountTimingsTranslator;
import com.clinic.clinic.api.translator.impl.RateTraceTranslator;
import com.clinic.clinic.api.translator.impl.RateTranslatorImpl;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;
import com.clinic.clinic.common.dto.biz.RateDto;
import com.clinic.clinic.common.exception.BizlogicException;

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
public class RateServiceImpl extends AbsService implements IRateService {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateServiceImpl.class);
    @Autowired
    private IRateRepository rateRepo;
    
    private ITranslator<RateDto, RateEntity> rateTrans = RateTranslatorImpl.INSTANCE;
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IRateService#getMarkMedicarByMedicarId(java.lang.Integer)
     */
    @Override
    public RateDto getMarkMedicarByMedicarId(Integer medicarId) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        RateDto ret = null;
        try {
            RateEntity rateEnt = rateRepo.findRateEntityByMedicarId(medicarId);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD_NORMAL);
            }
            ret = rateTrans.getDto(rateEnt);
        } catch (BizlogicException be){
            throwBizlogicException(500, IBizErrorCode.RATING_NOT_FOUNT, be.getMessage(), medicarId.toString());
            LOGGER.error(IConstants.END_METHOD_ERROR);
        } catch (Exception e) {
            LOGGER.error(IConstants.END_METHOD_ERROR);
            throwBizlogicException(500, IBizErrorCode.UNKNOWN_ERROR, e.getMessage());
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return ret;
    }
}