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
 * Project name  : clinic-data<br>
 * File name     : AnnualFeeDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 2, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.dto.NameDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class AnnualFeeDto extends NameDto implements Serializable {

    private static final long serialVersionUID = 820851724951143614L;
    
    private Long price;
    
    private List<AccountDto> accounts = new ArrayList<AccountDto>();

    /**
     * <p>Default constructor (no parameter) of this class.</p>
     *
     * @param price
     */
    public AnnualFeeDto(Long price) {
        super();
        this.price = price;
    }
    
    /**
     * <p>Default constructor (no parameter) of this class.</p>
     *
     */
    public AnnualFeeDto() {
    }

    /**
     * <p>Returns current value of price attribute.</p>
     *
     * @return the price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * <p>Sets value of price attribute.</p>
     *
     * @param price the price to set
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * <p>Returns current value of accounts attribute.</p>
     *
     * @return the accounts
     */
    public List<AccountDto> getAccounts() {
        return accounts;
    }

    /**
     * <p>Sets value of accounts attribute.</p>
     *
     * @param accounts the accounts to set
     */
    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }
    
}
