package com.clinic.clinic.api.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescription_doctor_note")
@NamedQuery(name = "PrescriptionDoctorNoteEntity.findAll", query = "Select a From PrescriptionDoctorNoteEntity a")
public class PrescriptionDoctorNoteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1992121616829716460L;
	
	@Id
	@Column
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private PrescriptionEntity prescription;
	
	@Column
	private String text;
	
	@Column
	private String image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PrescriptionEntity getPrescription() {
		return prescription;
	}

	public void setPrescription(PrescriptionEntity prescription) {
		this.prescription = prescription;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
