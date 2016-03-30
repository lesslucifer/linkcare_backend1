package com.clinic.clinic.api.persistence.repository.impl;

import java.time.LocalDate;
import java.util.List;

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
	public List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate date, int timeFrom,
			int timeTo) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ab FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar = :medicarId AND ");
		sb.append("ab.date = :date AND ");
		sb.append("NOT (");
		sb.append("(ab.time < :timeFrom AND ");
		sb.append("ab.end < :timeFrom) OR ");
		sb.append("(ab.time > :timeTo)");
		sb.append(") AND ");
		sb.append("ab.status = 2");
		
		TypedQuery<AppointmentBookingEntity> q = getEntityManager().createQuery(sb.toString(), AppointmentBookingEntity.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("date", date);
		q.setParameter("timeFrom", timeFrom);
		q.setParameter("timeTo", timeTo);

		return q.getResultList();
	}
	
	@Override
	public List<AppointmentBookingEntity> getApprovedBooking(Integer medicarId, LocalDate dateFrom, LocalDate dateTo) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ab FROM AppointmentBookingEntity ab ");
		sb.append("WHERE ab.medicar = :medicarId AND ");
		sb.append("ab.date >= :dateFrom AND ");
		sb.append("ab.date <= :dateTo AND ");
		sb.append("ab.status = 2");
		
		TypedQuery<AppointmentBookingEntity> q = getEntityManager().createQuery(sb.toString(), AppointmentBookingEntity.class);
		q.setParameter("medicarId", medicarId);
		q.setParameter("dateFrom", dateFrom);
		q.setParameter("dateTo", dateTo);

		return q.getResultList();
	}
}
