/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 CLINIC JSC.
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
 * File name     : IdEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 20, 2015		Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * <p>
 * The abstract entity contains 
 *  is_deleted
 *  last_updated,
 *  created_by,
 *  created_datetime
 *  attributes.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link IdEntity}
 */
@MappedSuperclass
public abstract class TraceEntity extends DeleteableEntity {
    private static final long serialVersionUID = 3953977144749349775L;
    /**
     * The last updated time-stamp.
     */
    @Column(name="last_updated")
    private Long lastUpdated;
    /**
     * The identifier of user who has updated the record last time.
     */
    @Column(name="last_updated_by")
    private Integer lastUpdatedBy;
    /**
     * The created time-stamp.
     */
    @Column(name="created_datetime")
    private Long createdDatetime;
    /**
     * The identifier of user who has created the record.
     */
    @Column(name="created_by")
    private Integer createdBy;
    
    /**
     * <p>Default constructor.</p>
     */
    public TraceEntity() {
        this.createdDatetime = System.currentTimeMillis();
        this.lastUpdated = System.currentTimeMillis();
        this.lastUpdatedBy = 0;
        this.createdBy = 0;
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
    public void setLastUpdated(final Long lastUpdated) {
        this.lastUpdated = lastUpdated;
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
    public void setLastUpdatedBy(final Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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
}
