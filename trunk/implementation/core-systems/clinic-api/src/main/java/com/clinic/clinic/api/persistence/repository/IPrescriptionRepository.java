package com.clinic.clinic.api.persistence.repository;

import java.util.List;

import com.clinic.clinic.api.persistence.entity.PrescriptionEntity;

public interface IPrescriptionRepository extends IRepository<PrescriptionEntity, Integer> {
	List<Integer> getPrescriptionsHistory(Integer patient);
}
