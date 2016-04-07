package com.clinic.clinic.api.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.PrescriptionDoctorNoteEntity;
import com.clinic.clinic.api.persistence.repository.IPrescriptionDoctorNoteRepository;

@Repository
public class PrescriptionDoctorNoteRepositoryImpl extends AbsRepositoryImpl<PrescriptionDoctorNoteEntity, Integer>
	implements IPrescriptionDoctorNoteRepository {

}
