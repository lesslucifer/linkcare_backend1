package com.clinic.clinic.api.persistence.repository.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.PrescriptionEntity;
import com.clinic.clinic.api.persistence.repository.IPrescriptionRepository;

@Repository
public class PrescriptionRepositoryImpl extends AbsRepositoryImpl<PrescriptionEntity, Integer>
	implements IPrescriptionRepository {
	
	@Override
	public List<PrescriptionEntity> findAll(Iterable<Integer> ids) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT pe FROM PrescriptionEntity pe ");
		sb.append("WHERE pe.id IN :ids");
		
		TypedQuery<PrescriptionEntity> q = getEntityManager().createQuery(sb.toString(), PrescriptionEntity.class);
		q.setParameter("ids", StreamSupport.stream(ids.spliterator(), false).collect(Collectors.toList()));
//		q.setHint("eclipselink.batch", "pe.booking");
//		q.setHint("eclipselink.batch", "pe.doctorNote");
//		q.setHint("eclipselink.batch", "pe.medicines");

		List<PrescriptionEntity> result = q.getResultList();
		if (result == null) {
			result = Collections.emptyList();
		}

		return result;
	}
	
	@Override
	public List<Integer> getPrescriptionsHistory(Integer patient) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT pe.id FROM PrescriptionEntity pe ");
		sb.append("WHERE pe.booking.booker.id = :patient");
		
		TypedQuery<Integer> q = getEntityManager().createQuery(sb.toString(), Integer.class);
		q.setParameter("patient", patient);

		List<Integer> result = q.getResultList();
		if (result == null) {
			result = Collections.emptyList();
		}

		return result;
	}
}
