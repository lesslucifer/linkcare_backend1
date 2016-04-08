package com.clinic.clinic.api.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.PrescriptionMedicineEntity;
import com.clinic.clinic.api.persistence.repository.IPrescriptionMedicineRepository;

@Repository
public class PrescriptionMedicineRepositoryImpl extends AbsRepositoryImpl<PrescriptionMedicineEntity, Integer>
	implements IPrescriptionMedicineRepository {

}
