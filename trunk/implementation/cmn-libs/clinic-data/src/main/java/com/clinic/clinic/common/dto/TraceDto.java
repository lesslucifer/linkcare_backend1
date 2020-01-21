/**==============================================================================
 * clinic JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 clinic JSC.
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
 * File name     : IdDto.java<br>
 * <p>
 * Changes History <br>
 *      Date                Person              Reason<br>
 *      Aug 20, 2015        Vuong Do               Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto;

import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.utils.TimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>
 * Domain class contains only lastUpdated, lastUpdatedBy and isDeleted attributes.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
public class TraceDto extends IdDto {
    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = -6852384703387704784L;
    
    @JsonIgnore
    private Long createdDatetime;
    @JsonIgnore
    private Integer createdBy;
    
    private Long lastUpdated;
    @JsonIgnore
    private Integer lastUpdatedBy;
    	
    @JsonIgnore
    private Boolean isDeleted = new Boolean(false);
    
    /**
     * <p>Default constructor of this class.</p>
     */
    public TraceDto() {
    }
    

    /**
     * <p>Returns current value of createdDatetime attribute.</p>
     *
     * @return the createdDatetime
     */
    public Long getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * <p>Sets value of createdDatetime attribute.</p>
     *
     * @param createdDatetime the createdDatetime to set
     */
    public void setCreatedDatetime(Long createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * <p>Returns current value of createdBy attribute.</p>
     *
     * @return the createdBy
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * <p>Sets value of createdBy attribute.</p>
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * <p>Returns current value of lastUpdated attribute.</p>
     *
     * @return the lastUpdated
     */
    public Long getLastUpdated() {
        return lastUpdated;
    }

    /**
     * <p>Sets value of lastUpdated attribute.</p>
     *
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    /**
     * <p><p>Format LastUpdated to dd-MM-yyyy HH:mm aa.</p>
     *
     * @return
     *
     * @author minh.nguyen
     */
    @JsonIgnore
    public String getDateFormatLastUpdated() {
        if(lastUpdated != null){
            return TimeUtil.convertLongToDate(lastUpdated, IConstants.DateForMat_DDMMYYYY_HHMM); 
        }
        return null;
        
    }
    
    /**
     * <p>Set string dateTime to long.</p>
     *
     * @param dateFormatEndActiveTime
     *
     * @author minh.nguyen
     */
    @JsonIgnore
    public void setDateFormatLastUpdated(String dateFormatLastUpdated) {
        if (lastUpdated != null) {
            this.lastUpdated = TimeUtil.convertDateTimeToLong(dateFormatLastUpdated, IConstants.DateForMat_DDMMYYYY_HHMM);
        } else {
            this.lastUpdated = null;
        }
    }
    /**
     * <p>Returns current value of lastUpdatedBy attribute.</p>
     *
     * @return the lastUpdatedBy
     */
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * <p>Sets value of lastUpdatedBy attribute.</p>
     *
     * @param lastUpdatedBy the lastUpdatedBy to set
     */
    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * <p>Returns current value of isDeleted attribute.</p>
     *
     * @return the isDeleted
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * <p>Sets value of isDeleted attribute.</p>
     *
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("lastUpdated=").append(lastUpdated);
        sb.append(", lastUpdatedBy=").append(lastUpdatedBy);
        sb.append(", isDeleted=").append(isDeleted);
        return sb.toString();
    }
    /**
     * 
     * <p>Change first character of business string field to upper case.</p>
     *
     *
     * @author XuanBui
     */
    public void toUpercaseFirstChar() {
    	
    }
}
