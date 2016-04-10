package com.clinic.clinic.api.persistence.repository.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AddressEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.repository.IAppointmentBookingRepository;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;

@Repository
public class AppointmentBookingRepositoryImpl extends AbsRepositoryImpl<AppointmentBookingEntity, Integer> implements IAppointmentBookingRepository {

	@Override
	public AppointmentBookingEntity addAppointment(Integer booker, AddressEntity address, int time, int dur, AppointmentBookingRequestDto dto) {
		AppointmentBookingEntity entity = new AppointmentBookingEntity();
		entity.setBooker(getEntityManager().getReference(AccountEntity.class, booker));
		entity.setMedicar(getEntityManager().getReference(AccountEntity.class, dto.getMedicar()));
		entity.setAtHome(dto.isAtHome());
		entity.setAddress(address);
		entity.setDate(dto.getDate());
		entity.setTime(time);
		entity.setDuration(dur);
		// set cost as default
		entity.setCost(1000);
		entity.setStatus(0);

		return getJpaRepo().save(entity);
	}
	
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
		sb.append("(ab.time < :timeFrom AND ");
		sb.append("ab.time + ab.duration < :timeFrom) OR ");
		sb.append("(ab.time > :timeTo)");
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
		sb.append("(ab.time < :timeFrom AND ");
		sb.append("ab.time + ab.duration < :timeFrom) OR ");
		sb.append("(ab.time > :timeTo)");
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
		sb.append("(ab.time < :timeFrom AND ");
		sb.append("ab.time + ab.duration < :timeFrom) OR ");
		sb.append("(ab.time > :timeTo)");
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
		// TODO Auto-generated method stub
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
}
