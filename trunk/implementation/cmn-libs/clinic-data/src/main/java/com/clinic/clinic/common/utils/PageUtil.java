/**==============================================================================
 * MAPER JSC (www.maper.vn) Proprietary.
 * Copyright 2016 MAPER JSC.
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
 * Project name  : maper-data<br>
 * File name     : PageUtil.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Jan 8, 2016				minh.nguyen				Initial<br>
 * </p>
 *
 * @author minh.nguyen
 *=============================================================================*/
package com.clinic.clinic.common.utils;


/**
 * <p>
 * Util to set pageIndex, limit, sort to Page.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
public class PageUtil {
    
    private String sortExp;
    
    private Integer pageIndex;
    
    private Integer limit;

    
    public PageUtil(String sortExp, Integer pageIndex, Integer limit) {
        super();
        this.sortExp = sortExp;
        this.pageIndex = pageIndex;
        this.limit = limit;
    }

    /**
     * <p>Returns current value of sortExp attribute.</p>
     *
     * @return the sortExp
     */
    public String getSortExp() {
        return sortExp;
    }

    /**
     * <p>Sets value of sortExp attribute.</p>
     *
     * @param sortExp the sortExp to set
     */
    public void setSortExp(String sortExp) {
        this.sortExp = sortExp;
    }

    /**
     * <p>Returns current value of pageIndex attribute.</p>
     *
     * @return the pageIndex
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * <p>Sets value of pageIndex attribute.</p>
     *
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * <p>Returns current value of limit attribute.</p>
     *
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * <p>Sets value of limit attribute.</p>
     *
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
           
}
