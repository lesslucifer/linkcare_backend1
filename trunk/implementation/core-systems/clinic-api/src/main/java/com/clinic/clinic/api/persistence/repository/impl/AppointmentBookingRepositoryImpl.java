package com.clinic.clinic.api.persistence.repository.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.repository.IAppointmentBookingRepository;

@Repository
public class AppointmentBookingRepositoryImpl extends AbsRepositoryImpl<AppointmentBookingEntity, Integer> implements IAppointmentBookingRepository {

	@Override
	public List<AppointmentBookingEntity> getActiveAppointments(Integer medicarId, LocalDate date) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ab FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date = :date AND ");
		sb.append("ab.status IN :active_statuses");
		
		TypedQuery<AppointmentBookingEntity> q = getEntityManager().createQuery(sb.toString(), AppointmentBookingEntity.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("date", date);
		q.setParameter("active_statuses", AppointmentBookingEntity.ACTIVE_STATUSES);

		List<AppointmentBookingEntity> result = q.getResultList();
		if (result != null) {
			return result;
		}
		
		return null;
	}
	
	@Override
	public List<AppointmentBookingEntity> getActiveAppointmentsAtHome(Integer medicarId, LocalDate date, boolean atHome) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ab FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date = :date AND ");
		sb.append("ab.isAtHome = :home AND ");
		sb.append("ab.status IN :active_statuses");
		
		TypedQuery<AppointmentBookingEntity> q = getEntityManager().createQuery(sb.toString(), AppointmentBookingEntity.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("date", date);
		q.setParameter("home", atHome ? 1 : 0);
		q.setParameter("active_statuses", AppointmentBookingEntity.ACTIVE_STATUSES);

		List<AppointmentBookingEntity> result = q.getResultList();
		if (result != null) {
			return result;
		}
		
		return Collections.emptyList();
	}
	
	@Override
	public int countActiveAppointmentsAtHome(Integer medicarId, LocalDate date, boolean atHome) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date = :date AND ");
		sb.append("ab.isAtHome = :home AND ");
		sb.append("ab.status IN :active_statuses");
		
		TypedQuery<Integer> q = getEntityManager().createQuery(sb.toString(), Integer.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("date", date);
		q.setParameter("home", atHome ? 1 : 0);
		q.setParameter("active_statuses", AppointmentBookingEntity.ACTIVE_STATUSES);

		List<Integer> result = q.getResultList();
		if (result == null || result.isEmpty()) {
			return 0;
		}
		
		return result.get(0);
	}
	
	@Override
	public List<AppointmentBookingEntity> getActiveAppointments(Integer medicarId, LocalDate dateFrom, LocalDate dateTo) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ab FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date >= :dateFrom AND ");
		sb.append("ab.date <= :dateTo AND ");
		sb.append("ab.status IN :active_statuses");
		
		TypedQuery<AppointmentBookingEntity> q = getEntityManager().createQuery(sb.toString(), AppointmentBookingEntity.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("dateFrom", dateFrom);
		q.setParameter("dateTo", dateTo);
		q.setParameter("active_statuses", AppointmentBookingEntity.ACTIVE_STATUSES);

		return q.getResultList();
	}
	
	@Override
	public boolean hasActiveAppointment(Integer medicarId, LocalDate date, int timeFrom, int timeTo) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT 1 FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date = :date AND ");
		sb.append("NOT (");
		sb.append("ab.time + ab.duration <= :timeFrom OR ");
		sb.append("ab.time >= :timeTo");
		sb.append(") AND ");
		sb.append("ab.status IN :active_statuses");
		
		Query q = getEntityManager().createQuery(sb.toString());
		q.setParameter("medicarId", medicarId);
		q.setParameter("date", date);
		q.setParameter("timeFrom", timeFrom);
		q.setParameter("timeTo", timeTo);
		q.setParameter("active_statuses", AppointmentBookingEntity.ACTIVE_STATUSES);

		List<?> result = q.getResultList();
		return result != null && !result.isEmpty();
	}

	@Override
	public List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate date, int timeFrom,
			int timeTo) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ab FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date = :date AND ");
		sb.append("NOT (");
		sb.append("ab.time + ab.duration <= :timeFrom OR ");
		sb.append("ab.time >= :timeTo");
		sb.append(") AND ");
		sb.append("ab.status = :approved_status");
		
		TypedQuery<AppointmentBookingEntity> q = getEntityManager().createQuery(sb.toString(), AppointmentBookingEntity.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("date", date);
		q.setParameter("timeFrom", timeFrom);
		q.setParameter("timeTo", timeTo);
		q.setParameter("approved_status", AppointmentBookingEntity.STATUS_APPROVED);

		return q.getResultList();
	}
	
	@Override
	public boolean hasApprovedBooking(Integer medicarId, LocalDate date, int timeFrom,
			int timeTo) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT 1 FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date = :date AND ");
		sb.append("NOT (");
		sb.append("ab.time + ab.duration <= :timeFrom OR ");
		sb.append("ab.time >= :timeTo");
		sb.append(") AND ");
		sb.append("ab.status = :approved_status");
		
		Query q = getEntityManager().createQuery(sb.toString());
		q.setParameter("medicarId", medicarId);
		q.setParameter("date", date);
		q.setParameter("timeFrom", timeFrom);
		q.setParameter("timeTo", timeTo);
		q.setParameter("approved_status", AppointmentBookingEntity.STATUS_APPROVED);

		List<?> result = q.getResultList();
		return result != null && !result.isEmpty();
	}
	
	@Override
	public List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate dateFrom, LocalDate dateTo) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ab FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.date >= :dateFrom AND ");
		sb.append("ab.date <= :dateTo AND ");
		sb.append("ab.status = :approved_status");
		
		TypedQuery<AppointmentBookingEntity> q = getEntityManager().createQuery(sb.toString(), AppointmentBookingEntity.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("dateFrom", dateFrom);
		q.setParameter("dateTo", dateTo);
		q.setParameter("approved_status", AppointmentBookingEntity.STATUS_APPROVED);

		return q.getResultList();
	}
	
	@Override
	public boolean hasProcessingAppointment(Integer medicarId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT 1 FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar.id = :medicarId AND ");
		sb.append("ab.status = :processing_status");
		
		Query q = getEntityManager().createQuery(sb.toString());
		q.setParameter("medicarId", medicarId);
		q.setParameter("processing_status", AppointmentBookingEntity.STATUS_PROCESSING);

		List<?> result = q.getResultList();
		return result != null && !result.isEmpty();
	}
	
	@Override
	public boolean isPatientHaveRecentWaitingAppointment(Integer patientId, long now,
			int recentDuration) {
		long recentTime = now - recentDuration * 60 * 1000;
		
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT 1 FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.booker.id = :patientId AND ");
		sb.append("ab.status IN :active_statuses AND ");
		sb.append("ab.createdDatetime >= :recentTime ");
		
		Query q = getEntityManager().createQuery(sb.toString());
		q.setParameter("patientId", patientId);
		q.setParameter("active_statuses", AppointmentBookingEntity.ACTIVE_STATUSES);
		q.setParameter("recentTime", recentTime);

		List<?> result = q.getResultList();
		return result != null && !result.isEmpty();
	}
}
