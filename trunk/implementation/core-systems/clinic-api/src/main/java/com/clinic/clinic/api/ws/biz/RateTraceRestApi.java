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
 * File name     : RateTraceRestApi.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Apr 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.ws.biz;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.IRateTraceService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
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
@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class RateTraceRestApi extends AbsRestApi {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateTraceRestApi.class);
    
    @Autowired
    private IRateTraceService rateTraceService;
    
    @RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_PARTIENT_RATING, method = RequestMethod.POST, produces = {
    "application/json" })
    public RateTraceDto rating(@RequestHeader("sess") String sess,
            @RequestParam(value = "mark", required = IDbConstants.TRUE) Double mark,
            @RequestParam(value = "comment", required = IDbConstants.TRUE) String comment,
            @RequestBody AppointmentBookingDto appointmentBookingDto,
            HttpServletResponse response) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Integer accountId = auth().authSession(sess);
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Account Login" + accountId);
        }
        auth().authRight(accountId, IDbConstants.RIGHT_RATING);
        RateTraceDto ret = null;
        try {
            ret = rateTraceService.rating(appointmentBookingDto, mark, comment, accountId);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return ret;
    }
}
