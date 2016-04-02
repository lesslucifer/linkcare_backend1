package com.clinic.clinic.api.persistence.repository.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Query;
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
			entity.setBeginTime(t.getBeginTime());
			entity.setLength(t.getLength());
			
			return entity;
		});
		
		
		return this.save(entities.collect(Collectors.toList()));
	}

	@Override
	public TimingsEntity getTimings(Integer medicar, Integer timingsId) {
		final StringBuilder sb = new StringBuilder();

		sb.append("SELECT * FROM `timings` AS t ");
		sb.append("JOIN `account_timings` AS at ON ");
		sb.append("t.account_timings_id = at.id AND ");
		sb.append("at.id = (");
		{
			sb.append("SELECT at2.id FROM `account_timings` AS at2 ");
			sb.append("WHERE at2.account_id = :medicar ");
			sb.append("ORDER BY at2.begin_date DESC ");
			sb.append("LIMIT 1");
		}
		sb.append(") ");
		sb.append("WHERE t.id = :timingsId");

		Query q = getEntityManager().createNativeQuery(sb.toString(), TimingsEntity.class);
		q.setParameter("timingsId", timingsId);
		q.setParameter("medicar", medicar);
		
		List<?> timings = q.getResultList();
		
		if (timings == null || timings.isEmpty()) {
			return null;
		}
		
		return (TimingsEntity) timings.get(0);
	}
}
