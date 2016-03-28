package com.clinic.clinic.api.persistence.repository.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountTimingsEntity;
import com.clinic.clinic.api.persistence.entity.TimingsEntity;
import com.clinic.clinic.api.persistence.repository.ITimingsRepository;
import com.clinic.clinic.common.dto.biz.TimingsDto;

@Repository
public class TimingsRepository extends AbsRepositoryImpl<TimingsEntity, Integer> implements ITimingsRepository {

	@Override
	public List<TimingsEntity> insertTimings(AccountTimingsEntity accTimings, List<TimingsDto> timings) {
		Stream<TimingsEntity> entities = timings.stream().map(t -> {
			TimingsEntity entity = new TimingsEntity();
			entity.setAccountTimings(accTimings);
			entity.setBeginTime(t.getBegin());
			entity.setLength(t.getLength());
			
			return entity;
		});
		
		
		return this.save(entities.collect(Collectors.toList()));
	}
	
}
