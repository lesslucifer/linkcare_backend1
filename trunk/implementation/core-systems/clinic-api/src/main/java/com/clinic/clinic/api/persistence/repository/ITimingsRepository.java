package com.clinic.clinic.api.persistence.repository;

import java.util.List;

import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.entity.TimingsEntity;
import com.clinic.clinic.common.dto.biz.TimingsDto;

public interface ITimingsRepository extends IRepository<TimingsEntity, Integer> {
	List<TimingsEntity> insertTimings(AccountTimingsEntity accTimings, List<TimingsDto> timings);
	TimingsEntity getTimingsAtTime(Integer medicar, Integer time);
}