/**
 * =============================================================================
 * = CLINIC JSC (www.clinic.vn) Proprietary. Copyright 2016 CLINIC JSC.
 * UNPUBLISHED WORK ALL RIGHTS RESERVED This software is the confidential and
 * proprietary information of clinic J.S.C ("Proprietary Information"). Any use,
 * reproduction, distribution or disclosure of the software or Proprietary
 * Information, in whole or in part, must comply with the terms of the license
 * agreement, nondisclosure agreement or contract entered into with clinic
 * providing access to this software. Project name : clinic-api<br>
 * File name : RateTraceServiceImpl.java<br>
 * <p>
 * Changes History <br>
 * Date Person Reason<br>
 * Apr 7, 2016 Vuong Do Initial<br>
 * </p>
 *
 * @author Vuong Do
 *         =====================================================================
 *         ========
 */
package com.clinic.clinic.api.bizlogic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IRateTraceService;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.RateEntity;
import com.clinic.clinic.api.persistence.entity.RateTraceEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAppointmentBookingRepository;
import com.clinic.clinic.api.persistence.repository.IRateRepository;
import com.clinic.clinic.api.persistence.repository.IRateTraceRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.RateTraceTranslator;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.biz.AppointmentBookingDto;
import com.clinic.clinic.common.dto.biz.RateTraceDto;

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
public class RateTraceServiceImpl extends AbsService implements IRateTraceService {
    
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateTraceServiceImpl.class);
    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private IRateRepository rateRepo;
    @Autowired
    private IAppointmentBookingRepository aptBookingRepo;
    @Autowired
    private IRateTraceRepository rateTraceRepo;
    private ITranslator<RateTraceDto, RateTraceEntity> rateTraceTrans = RateTraceTranslator.INSTANCE; 
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IRateTraceService#rating(com.clinic.clinic.common.dto.biz.AppointmentBookingDto)
     */
    @Override
    public RateTraceDto rating(AppointmentBookingDto appointmentBookingDto, Double mark, String comment, Integer partientId) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        RateTraceDto ret = null;
        try {
            AppointmentBookingEntity aptBookingEnt = aptBookingRepo.getOne(appointmentBookingDto.getId());
            if(null == aptBookingEnt) {
                throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.APPOINTMENT_NOT_FOUNT, "Unknown appointment booking");
            }
            Integer medicarId = aptBookingEnt.getMedicar().getId();
            RateEntity rateEnt = rateRepo.findRateEntityByMedicarId(medicarId);
            if(null == rateEnt) {
                throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.RATING_NOT_FOUNT, "Unknown medicar", medicarId);
            }
            // AppointmentBookingEntity aptBookingEnt = aptBookingRepo.getOne(appointmentBookingDto.getId());
            
            if(null != aptBookingEnt && aptBookingEnt.getStatus() != AppointmentBookingEntity.STATUS_FINISHED) {
                throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_NOT_FINISHED, "Appointment isn't finished", aptBookingEnt.getId());
            }
            Integer count = rateEnt.getCount();
            Double x = (((rateEnt.getMark() * rateEnt.getCount()) + mark) / ++count);
            rateEnt.setCount(count);
            rateEnt.setMark(x);
            rateRepo.save(rateEnt);
            
            RateTraceEntity rateTraceEnt = new RateTraceEntity();
            rateTraceEnt.setCreatedBy(partientId);
            rateTraceEnt.setCreatedDatetime(System.currentTimeMillis());
            rateTraceEnt.setLastUpdatedBy(partientId);
            rateTraceEnt.setLastUpdated(System.currentTimeMillis());
            rateTraceEnt.setMark(mark);
            rateTraceEnt.setComment(comment);
            rateTraceEnt.setAppointmentBooking(aptBookingEnt);
            rateTraceEnt.setPartient(accountRepo.getOne(partientId));
            rateTraceRepo.save(rateTraceEnt);
            
            ret = rateTraceTrans.getDto(rateTraceEnt);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return ret;
    }
    
}
