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
 * File name     : CalendarServiceImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 17, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clinic.clinic.api.bizlogic.service.ICalendarService;
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
public class CalendarServiceImpl extends AbsService implements ICalendarService {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.ICalendarService#createCalendarClinicWithinAccountId(java.lang.Integer)
     */
    @Override
    public Integer createCalendarClinicWithinAccountId(Integer accountId) throws BizlogicException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.ICalendarService#createCalendarHomeWithinAccountId(java.lang.Integer)
     */
    @Override
    public Integer createCalendarHomeWithinAccountId(Integer accountId) throws BizlogicException {
        // TODO Auto-generated method stub
        return null;
    }
}
