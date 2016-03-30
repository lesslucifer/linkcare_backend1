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
 * File name     : AccountRestApi.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.ws.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.IAccountService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.AccountCustomDto;
import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.dto.biz.AccountFilterDto;
import com.clinic.clinic.common.dto.biz.SubcategoryDto;
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
@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class AccountRestApi extends AbsRestApi {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestApi.class);
    
    @Autowired
    private IAccountService accountService;
    
    /**
     *  At Clinic
     *  No condition view limit.
     *  Get all doctor (nurse) by sub-category.
     *  User select option go to the clinic.  
     * */
    @RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_ACCOUNT_SUBCATEGORY_SUBCATEGORYID, method = RequestMethod.GET, produces = {
    "application/json" })
    public Page<AccountDto> getAccountBySubcateId(@PathVariable("subcategoryId") Integer subcateId ,@RequestParam(value = IRestApiConstants.SORTING_PARAM_NAME_SORT, required = IDbConstants.FALSE) String sortExp,
            @RequestParam(value = IRestApiConstants.PAGING_PARAM_NAME_PAGE, required = IDbConstants.FALSE, defaultValue = "0") Integer viewPageNo,
            @RequestParam(value = IRestApiConstants.PAGING_PARAM_NAME_LIMIT, required = IDbConstants.FALSE, defaultValue = "10") Integer maxRecPerPage,
            HttpServletResponse response) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<AccountDto> retVal = null;
        try {
            /**
             * Call business service to acquire data.
             */
            // Extract "sort expression" from query string
            final Sort sortingExp = parseSortParam(sortExp);

            // Build "pageable" parameter from query string
            final Pageable range = buildPageableParam(viewPageNo, maxRecPerPage, sortingExp);

            retVal = accountService.getAccountBySubcategory(range, subcateId);

            /**
             * Paging navigation processing.
             */
            final int offset = viewPageNo * maxRecPerPage;
            final int limit = offset + maxRecPerPage;
            final long totalNumber = retVal.getContent().size();
            final String resourceName = SubcategoryDto.class.getCanonicalName().toLowerCase();
            final List<String> naviUriLinks = new ArrayList<String>();
            buildPagingResponseHeader(offset, limit, totalNumber, resourceName, maxRecPerPage, naviUriLinks, response);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retVal;
    }
    
    @RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_ACCOUNT_FILTER, method = RequestMethod.POST, produces = {
    "application/json" })
    public Page<AccountCustomDto> filterAccount(@RequestBody AccountFilterDto accountfilterDto,
            @RequestParam(value = IRestApiConstants.SORTING_PARAM_NAME_SORT, required = IDbConstants.FALSE) String sortExp,
            @RequestParam(value = IRestApiConstants.PAGING_PARAM_NAME_PAGE, required = IDbConstants.FALSE, defaultValue = "0") Integer viewPageNo,
            @RequestParam(value = IRestApiConstants.PAGING_PARAM_NAME_LIMIT, required = IDbConstants.FALSE, defaultValue = "10") Integer maxRecPerPage,
            HttpServletResponse response) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<AccountCustomDto> retVal = null;
        try {
            /**
             * Call business service to acquire data.
             */
            // Extract "sort expression" from query string
            final Sort sortingExp = parseSortParam(sortExp);

            // Build "pageable" parameter from query string
            final Pageable range = buildPageableParam(viewPageNo, maxRecPerPage, sortingExp);

            if(null == accountfilterDto.getLatitude() && null == accountfilterDto.getLongtitude()) {
                retVal = accountService.getAccountAndClinic(range, accountfilterDto);
            } else {
                retVal = accountService.getAccountAndHome(range, accountfilterDto);
            }

            /**
             * Paging navigation processing.
             */
            final int offset = viewPageNo * maxRecPerPage;
            final int limit = offset + maxRecPerPage;
            final long totalNumber = retVal.getContent().size();
            final String resourceName = AccountCustomDto.class.getCanonicalName().toLowerCase();
            final List<String> naviUriLinks = new ArrayList<String>();
            buildPagingResponseHeader(offset, limit, totalNumber, resourceName, maxRecPerPage, naviUriLinks, response);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        return retVal;
    }
    
    @RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_ACCOUNT_LOGIN, method = RequestMethod.POST, produces = {
    "application/json" })
    public AccountDto login(@RequestBody String loginName, String pass) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        AccountDto retVal = null;
        try {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Debug", StringUtil.getHashedText(pass));
            }
            retVal = accountService.login(loginName, pass);
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retVal;
    }
}
