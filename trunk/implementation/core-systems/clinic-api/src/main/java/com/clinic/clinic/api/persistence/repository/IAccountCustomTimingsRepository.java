package com.clinic.clinic.api.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import com.clinic.clinic.api.persistence.entity.AccountCustomTimingsEntity;

public interface IAccountCustomTimingsRepository extends IRepository<AccountCustomTimingsEntity, Integer> {
	List<AccountCustomTimingsEntity> getCustomTimings(Integer accountId, LocalDate from, LocalDate to);
}
