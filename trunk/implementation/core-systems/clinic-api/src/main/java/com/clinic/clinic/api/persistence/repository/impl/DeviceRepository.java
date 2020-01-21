package com.clinic.clinic.api.persistence.repository.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.DeviceEntity;
import com.clinic.clinic.api.persistence.repository.IDeviceRepository;

@Repository
public class DeviceRepository extends AbsRepositoryImpl<DeviceEntity, String> implements IDeviceRepository {

	@Override
	public List<DeviceEntity> getDevicesOfUser(Integer accountId, String app) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT d FROM DeviceEntity d ");
		sb.append("WHERE d.owner.id = :accountId AND d.app = :app");
		
		TypedQuery<DeviceEntity> q = getEntityManager().createQuery(sb.toString(), DeviceEntity.class);
		q.setParameter("accountId", accountId);
		q.setParameter("app", app);

		List<DeviceEntity> result = q.getResultList();
		if (result == null) {
			result = Collections.emptyList();
		}
		
		return result;
	}
	
}
