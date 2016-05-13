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
 * File name     : DatetimeUtils.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		May 13, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class DatetimeUtils {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatetimeUtils.class);
    
    public static Long parsesDatetime(String birthday) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatter.parse(birthday);
        } catch (ParseException e) {
            LOGGER.error("Exception", e);
        }
        long dateInLong = date.getTime();
        return dateInLong;
    }
}
