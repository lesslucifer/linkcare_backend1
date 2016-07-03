package com.clinic.clinic.api.persistence.repository;

import java.util.List;

import com.clinic.clinic.api.persistence.entity.DeviceEntity;

public interface IDeviceRepository extends IRepository<DeviceEntity, String> {
	List<DeviceEntity> getDevicesOfUser(Integer userId, String app);
}
