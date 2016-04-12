package com.clinic.clinic.api.persistence.repository;

import com.clinic.clinic.api.persistence.entity.MedicarProfileEntity;

public interface IMedicarProfileRepository extends IRepository<MedicarProfileEntity, Integer> {
	MedicarProfileEntity getByAccountId(Integer accountId);
}
