/**==============================================================================
 * MAPER JSC (www.maper.vn) Proprietary.
 * Copyright 2015 MAPER JSC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 *
 * This software is the confidential and proprietary information of 
 * MAPER J.S.C ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license  
 * agreement, nondisclosure agreement or contract entered into with 
 * MAPER providing access to this software.
 *
 * Project name  : maper-api<br>
 * File name     : HibernateAwareObjectMapper.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Nov 13, 2015		Logan Phan			Initial<br>
 * </p>
 *
 * @author Logan Phan
 *=============================================================================*/
package com.clinic.clinic.api.translator;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.clinic.clinic.common.utils.JsonJava8TimeSerialization;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
@SuppressWarnings("serial")
public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper() {
        registerModule(new Hibernate4Module());

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new JsonJava8TimeSerialization.LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new JsonJava8TimeSerialization.LocalDateDeserializer());
        module.addSerializer(LocalDateTime.class, new JsonJava8TimeSerialization.LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new JsonJava8TimeSerialization.LocalDateTimeDeserializer());
        registerModule(module);
    }
}
