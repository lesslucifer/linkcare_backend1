package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@NamedQuery(name = "CategoryEntity.findAll", query = "Select c From CategoryEntity c ")
public class CategoryEntity extends NameCodeDescEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1886449672830753471L;
	
	@Column(name="i18n_lang_key_prefix", nullable=true)
    private String i18nLangKeyPrefix;
    @Column(name="view_radius_limit", nullable=true)
    private Long viewRadiusLimit;
    
    
	public String getI18nLangKeyPrefix() {
		return i18nLangKeyPrefix;
	}
	public void setI18nLangKeyPrefix(String i18nLangKeyPrefix) {
		this.i18nLangKeyPrefix = i18nLangKeyPrefix;
	}
	public Long getViewRadiusLimit() {
		return viewRadiusLimit;
	}
	public void setViewRadiusLimit(Long viewRadiusLimit) {
		this.viewRadiusLimit = viewRadiusLimit;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "major_id", nullable = false)
	private MajorEntity major;

	public MajorEntity getMajor() {
		return major;
	}
	public void setMajor(MajorEntity major) {
		this.major = major;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<SubcategoryEntity> subcategories = new ArrayList<SubcategoryEntity>();


    public List<SubcategoryEntity> getSubcategories() {
        return subcategories;
    }
    public void setSubcategories(List<SubcategoryEntity> subcategories) {
        this.subcategories = subcategories;
    }
	
}
