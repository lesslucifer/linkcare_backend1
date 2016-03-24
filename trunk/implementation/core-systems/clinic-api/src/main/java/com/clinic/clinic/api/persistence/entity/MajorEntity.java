package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "major")
@NamedQuery(name = "MajorEntity", query = "SELECT m FROM MajorEntity m")
public class MajorEntity extends NameCodeEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1880971513771961143L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "major")
	private List<CategoryEntity> categories = new ArrayList<CategoryEntity>();

	public List<CategoryEntity> getCatrgories() {
		return categories;
	}

	public void setCatrgories(List<CategoryEntity> catrgories) {
		this.categories = catrgories;
	}
}