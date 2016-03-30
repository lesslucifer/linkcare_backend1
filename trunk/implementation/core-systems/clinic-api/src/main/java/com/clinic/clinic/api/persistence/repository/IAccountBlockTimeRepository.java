package com.clinic.clinic.api.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;

public interface IAccountBlockTimeRepository extends IRepository<AccountBlockTimeEntity, Integer> {
	List<AccountBlockTimeEntity> getBlockTime(Integer accountId, LocalDateTime from, LocalDateTime to);
}
