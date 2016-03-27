package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DeleteableEntity extends IdEntity {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -309272012083487103L;
	
	/**
     * The isDeleted flag is used to mark the record is deleted in system.
     */
    @Column(name="is_deleted")
    private Boolean isDeleted = false;
    
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
    public void setIsDeleted(final Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
