package com.clinic.clinic.common.dto.biz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.dto.IdDto;
import com.clinic.clinic.common.dto.TraceDto;

public class PrescriptionDto extends IdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3891370966665737670L;

	private TraceDto booking;
	
	private LocalDate date;
	
	private String instruction;

	private DoctorNoteDto doctorNote;
	
	private List<PrescriptionMedicineDto> medicines = new ArrayList<>();

	public TraceDto getBooking() {
		return booking;
	}

	public void setBooking(TraceDto booking) {
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

	public DoctorNoteDto getDoctorNote() {
		return doctorNote;
	}

	public void setDoctorNote(DoctorNoteDto doctorNote) {
		this.doctorNote = doctorNote;
	}

	public List<PrescriptionMedicineDto> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<PrescriptionMedicineDto> medicines) {
		this.medicines = medicines;
	}
}
