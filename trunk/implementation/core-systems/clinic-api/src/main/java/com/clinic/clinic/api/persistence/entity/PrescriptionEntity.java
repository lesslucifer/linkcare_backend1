package com.clinic.clinic.api.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_prescription")
@NamedQuery(name = "PrescriptionEntity.findAll", query = "Select a From PrescriptionEntity a")
public class PrescriptionEntity extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2371342072534137743L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking_id")
	private AppointmentBookingEntity booking;
	
	@Column
	private LocalDate date;
	
	@Column
	private String instruction;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "prescription")
	private PrescriptionDoctorNoteEntity doctorNote;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prescription")
	private List<PrescriptionMedicineEntity> medicines = new ArrayList<PrescriptionMedicineEntity>();

	public AppointmentBookingEntity getBooking() {
		return booking;
	}

	public void setBooking(AppointmentBookingEntity booking) {
		this.booking = booking;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public PrescriptionDoctorNoteEntity getDoctorNote() {
		return doctorNote;
	}

	public void setDoctorNote(PrescriptionDoctorNoteEntity doctorNote) {
		this.doctorNote = doctorNote;
	}

	public List<PrescriptionMedicineEntity> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<PrescriptionMedicineEntity> medicines) {
		this.medicines = medicines;
	}
}
