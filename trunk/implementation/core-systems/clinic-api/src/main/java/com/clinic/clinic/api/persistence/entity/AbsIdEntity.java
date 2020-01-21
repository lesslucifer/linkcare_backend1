package com.clinic.clinic.api.persistence.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

// This class is used to support override id field's annotations on subclasses
// of IdEntity
// Currently not used
@MappedSuperclass
public abstract class AbsIdEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7592482179561046614L;

	public abstract Integer getId();
}
