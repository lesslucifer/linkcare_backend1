package com.clinic.clinic.api.bizlogic.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.INotificationService;
import com.clinic.clinic.api.bizlogic.service.IPrescriptionService;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.MedicarProfileEntity;
import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.entity.PrescriptionDoctorNoteEntity;
import com.clinic.clinic.api.persistence.entity.PrescriptionEntity;
import com.clinic.clinic.api.persistence.entity.PrescriptionMedicineEntity;
import com.clinic.clinic.api.persistence.repository.IAppointmentBookingRepository;
import com.clinic.clinic.api.persistence.repository.IMedicarProfileRepository;
import com.clinic.clinic.api.persistence.repository.IPrescriptionDoctorNoteRepository;
import com.clinic.clinic.api.persistence.repository.IPrescriptionMedicineRepository;
import com.clinic.clinic.api.persistence.repository.IPrescriptionRepository;
import com.clinic.clinic.api.persistence.repository.IRoleRepository;
import com.clinic.clinic.api.translator.impl.PrescriptionDoctorNoteTranslatorImpl;
import com.clinic.clinic.api.translator.impl.PrescriptionMedicineTranslatorImpl;
import com.clinic.clinic.api.translator.impl.PrescriptionTranslatorImpl;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.dto.biz.PrescriptionDto;
import com.clinic.clinic.common.dto.biz.PrescriptionSubmitDto;

@ApplicationService
public class PrescriptionServiceImpl extends AbsService implements IPrescriptionService {
	
	@Autowired
	IPrescriptionRepository prescriptionRepo;
	
	@Autowired
	IPrescriptionDoctorNoteRepository doctorNoteRepo;
	
	@Autowired
	IPrescriptionMedicineRepository medicineRepo;
	
	@Autowired
	IAppointmentBookingRepository appBookingRepo;
	
	@Autowired
	IMedicarProfileRepository medicarProfileRepo;
	
	@Autowired
	INotificationService notifServ;
	
	@Autowired
	private IRoleRepository roleRepo;
	
	public String medicarTitle(Integer medicarId) {
		if (roleRepo.isHasRole(medicarId, "NURSE_ROLE")) {
			return "Điều dưỡng";
		}
		
		return "Bác sĩ";
	}
	
	
	@Override
	public List<PrescriptionDto> getPrescriptions(Integer requester, List<Integer> ids) {
		if (requester == null) {
			return Collections.emptyList();
		}
		
		List<PrescriptionEntity> entities = prescriptionRepo.findAll(ids);
		
		Predicate<PrescriptionEntity> hasPermission = (pre) -> {
			return requester.equals(pre.getBooking().getBooker().getId()) ||
					requester.equals(pre.getBooking().getMedicar().getId());
		};
		entities = entities.stream().filter(hasPermission).collect(Collectors.toList());
		
		return PrescriptionTranslatorImpl.INST.getDtoList(entities);
	}

	@Override
	public PrescriptionDto submitPrescription(Integer medicar, PrescriptionSubmitDto dto) {
		AppointmentBookingEntity booking = appBookingRepo.findOne(dto.getAppointmentId());
		if (booking == null) {
			throwBizlogicException(HttpStatus.BAD_REQUEST,
					IBizErrorCode.OBJECT_NOT_FOUND,
					"Appointment booking not found!", dto.getAppointmentId());
		}
		
		if (booking.getStatus() != AppointmentBookingEntity.STATUS_PROCESSING) {
			throwBizlogicException(HttpStatus.BAD_REQUEST,
					IBizErrorCode.PRESCRIPTION_APPOINTMENT_STATUS_MISMATCH,
					"Appointment status mismatch (must be processing)!", booking.getStatus());
		}

		if (booking.getMedicar().getId() != medicar) {
			throwBizlogicException(HttpStatus.BAD_REQUEST,
					IBizErrorCode.PRESCRIPTION_MEDICAR_MISMATCH,
					"Cannot submit prescription for other's appointment!", medicar, booking.getMedicar().getId());
		}

		// update booking status before save prescription
		booking.setStatus(AppointmentBookingEntity.STATUS_FINISHED);
		appBookingRepo.save(booking);

		// ok! save prescription
		PrescriptionEntity entity = new PrescriptionEntity();
		entity.setBooking(booking);
		entity.setDate(dto.getDate());
		entity.setInstruction(dto.getInstruction());
		entity = prescriptionRepo.save(entity);
		
		PrescriptionDoctorNoteEntity doctorNote = PrescriptionDoctorNoteTranslatorImpl.INST.getEntity(dto.getNote());
		doctorNote.setId(entity.getId());
		doctorNote.setPrescription(entity);
		doctorNote = doctorNoteRepo.save(doctorNote);
		entity.setDoctorNote(doctorNote);
		
		List<PrescriptionMedicineEntity> medicines = PrescriptionMedicineTranslatorImpl.INST.getEntityList(dto.getMedicines());
		for (PrescriptionMedicineEntity medicine : medicines) {
			medicine.setPrescription(entity);
		}
		medicines = medicineRepo.save(medicines);
		entity.setMedicines(medicines);
		
		String title = this.medicarTitle(medicar);
		
		// send notification to patient
		final StringBuilder sb = new StringBuilder();
		sb.append("Bạn vừa khám xong với ").append(title.toLowerCase()).append(" <b>");
		booking.getMedicar().getFullName(sb);
		sb.append("</b>. Xin vui lòng nhấn vào đây để xem toa thuốc và đánh giá ").append(title.toLowerCase()).append(".");
		notifServ.sendMessage(INotificationService.USER_APP, null, booking.getBooker().getId(), NotificationEntity.TYPE_APPOINTMENT_FINISHED,
				sb.toString(), booking.getId(), entity.getId(), medicar);
		
		final MedicarProfileEntity medicarProfile = medicarProfileRepo.getByAccountId(booking.getMedicar().getId());
		final long now = System.currentTimeMillis();
		if (medicarProfile.getExpiredTime() == null || medicarProfile.getExpiredTime() <= now) {
			final int nOverloaded = medicarProfile.getFreeAppointments() == null ? 0 : medicarProfile.getFreeAppointments();
			medicarProfile.setFreeAppointments(nOverloaded + 1);
			medicarProfileRepo.save(medicarProfile);
		}

		// all done, save entity
		return PrescriptionTranslatorImpl.INST.getDto(entity);
	}

	@Override
	public List<Integer> getPrescriptionHistory(Integer medicarId, Integer patientId, Integer appointmentId) {
		AppointmentBookingEntity booking = appBookingRepo.findOne(appointmentId);
		
		if (booking == null) {
			throwBizlogicException(HttpStatus.BAD_REQUEST,
					IBizErrorCode.OBJECT_NOT_FOUND,
					"Appointment booking not found!", appointmentId);
		}
		
		if (booking.getStatus() != AppointmentBookingEntity.STATUS_PROCESSING) {
			throwBizlogicException(HttpStatus.BAD_REQUEST,
					IBizErrorCode.PRESCRIPTION_APPOINTMENT_STATUS_MISMATCH,
					"Appointment status mismatch (must be processing)!", booking.getStatus());
		}
		
		if (booking.getMedicar().getId() != medicarId) {
			throwBizlogicException(HttpStatus.BAD_REQUEST,
					IBizErrorCode.PRESCRIPTION_MEDICAR_MISMATCH,
					"The appointment is not belong to the medicar!", medicarId, appointmentId);
		}
		
		if (booking.getBooker().getId() != patientId) {
			throwBizlogicException(HttpStatus.BAD_REQUEST,
					IBizErrorCode.PRESCRIPTION_PATIENT_MISMATCH,
					"The appointment is not belong to the patient!", patientId, appointmentId);
		}
		
		// ok, the medicar can get patient's prescription history
		return prescriptionRepo.getPrescriptionsHistory(patientId);
	}
}
