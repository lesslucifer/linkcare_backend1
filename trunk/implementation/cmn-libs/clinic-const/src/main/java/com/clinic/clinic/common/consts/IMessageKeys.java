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
 * Project name  : clinic-const<br>
 * File name     : IMessageKeys.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 2, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.consts;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public interface IMessageKeys {
    /**
     * #### Common messages ####
     */
    static final String MESSAGE = "message";
    
    static final String MSG_ERROR_GENERIC_EXCEPTION = "msg.error.generic.exception";
    
    static final String MSG_ERROR_ENTITY_NOT_FOUND = "msg.error.entity.not.found";
    
    static final String MSG_ERROR_CODE_DUPLCATED = "msg.error.code.duplicated";
    
    static final String MESSAGE_ERROR_ACCOUNT_LOGINNAME_DUPLICATE = "msg.error.loginName.duplicate";
    static final String MESSAGE_ERROR_ACCOUNT_EMAIL_DUPLICATE = "msg.error.account.email.duplicate";
    static final String MESSAGE_ERROR_ACCOUNT_PHONENUMBER_DUPLICATE = "msg.error.account.phonenumber.duplicate";
    
    static final String MSG_ERROR_SYSTEM = "message_system_error";
    
    /**
     * #### Auth messages ####
     */
    static final String MSG_ERROR_AUTH_ROLE_NOT_FOUND = "auth.role.not.found";
    static final String MSG_ERROR_AUTH_RIGHT_NOT_FOUND = "auth.right.not.found";

    static final String MSG_ERROR_AUTH_LOGIN_NOT_FOUND = "auth.login.not.found";
    static final String MSG_ERROR_AUTH_PWD_WRONG = "auth.pwd.wrong";
    static final String MSG_ERROR_AUTH_ACC_INACTIVE = "auth.acc.inactive";
    
    static final String MSG_ERROR_AUTH_SESSION_TIMEOUT = "auth.session.timeout";
    
    static final String MESSAGE_FORGET_PWD_FAILED = "forget_pwd_failed";
    static final String MESSAGE_FORGET_PWD_OK = "forget_pwd_ok";
    
    static final String MESSAGE_CHANGE_PWD_SUCCESS = "change_pwd_success";

    /**
     * #### Web services - Upload image ####
     */
    static final String MSG_ERROR_UPLOAD_IMG_OVER_LIMIT = "msg.error.upload.img.over.limit";
    /**
     * #### Web services - RestCountryApi ####
     */
    static final String MSG_INFO_UPDATE_COUNTRY_SUCCESSFUL = "msg.info.update.country.successful";
    static final String MSG_INFO_DELETE_COUNTRY_SUCCESSFUL = "msg.info.delete.country.successful";
    
    /**
     * #### Web services - RestCsutomerApi ####
     */
    static final String MESSAGE_LOCATION_CREATE_FAILED = "location_create_failed";
    static final String MESSAGE_LOCATION_CREATE_OK = "location_create_ok";
    static final String MESSAGE_CREATE_OK = "create_object_ok";
    static final String MESSAGE_CREATE_FAIL = "create_object_fail";
    static final String MESSAGE_UPDATE_OK = "update_object_ok";
    static final String MESSAGE_UPDATE_FAIL = "update_object_fail";
    
    static final String MSG_INFO_UPDATE_CUSTOMER_SUCCESSFUL = "msg.info.update.customer.successful";
    static final String MSG_INFO_DELETE_CUSTOMER_SUCCESSFUL = "msg.info.delete.customer.successful";
    
}
