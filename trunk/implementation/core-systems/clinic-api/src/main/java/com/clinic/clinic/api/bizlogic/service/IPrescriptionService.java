package com.clinic.clinic.api.bizlogic.service;

import java.util.List;

import com.clinic.clinic.common.dto.biz.PrescriptionDto;
import com.clinic.clinic.common.dto.biz.PrescriptionSubmitDto;

public interface IPrescriptionService {
	List<PrescriptionDto> getPrescriptions(Integer requester, List<Integer> ids);
	PrescriptionDto submitPrescription(Integer medicar, PrescriptionSubmitDto dto);
	List<Integer> getPrescriptionHistory(Integer medicarId, Integer patientId, Integer appointmentId);
}
