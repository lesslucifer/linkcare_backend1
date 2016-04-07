package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import net.sf.oval.constraint.NotNull;

public class PrescriptionSubmitDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -432203706171428122L;
	
	@NotNull
	private Integer appointmentId;

	@NotNull
	private LocalDate date;
	
	@NotNull
	private DoctorNoteDto note;
	
	@NotNull
	private List<PrescriptionMedicineDto> medicines;
	
	@NotNull
	private String instruction;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public DoctorNoteDto getNote() {
		return note;
	}

	public void setNote(DoctorNoteDto note) {
		this.note = note;
	}

	public List<PrescriptionMedicineDto> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<PrescriptionMedicineDto> medicines) {
		this.medicines = medicines;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
