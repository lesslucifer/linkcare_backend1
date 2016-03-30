package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IAppointmentService;
import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AddressEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.TimingsEntity;
import com.clinic.clinic.api.persistence.repository.IAccountBlockTimeRepository;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAppointmentBookingRepository;
import com.clinic.clinic.api.persistence.repository.IAppointmentPatientRepository;
import com.clinic.clinic.api.persistence.repository.ITimingsRepository;
import com.clinic.clinic.api.translator.impl.AppointmentBookingTranslator;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.biz.AppointmentBookingDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;
import com.clinic.clinic.common.dto.biz.AppointmentConfirmDto;

@ApplicationService
public class AppointmentServiceImpl extends AbsService implements IAppointmentService {

	@Autowired
	private IAccountRepository accRepo;
	
	@Autowired
	private ITimingsRepository timingsRepo;
	
	@Autowired
	private IAccountBlockTimeRepository accBlockTimeRepo;
	
	@Autowired
	private IAppointmentBookingRepository appBookingRepo;
	
	@Autowired
	private IAppointmentPatientRepository appPatientRepo;
	
	@Override
	public AppointmentBookingDto bookAppointment(Integer booker, AppointmentBookingRequestDto dto) {
		AccountEntity medicar = accRepo.findOne(dto.getMedicar(), true);
		if (medicar == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Medicar not found!", dto.getMedicar());
		}
		
		if (medicar.getSubcategory().getId() != dto.getSubCategory()) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_MEDICAR_SUB_CATEGORY_MISMATCH, "Medicar sub category mismatch!",
					medicar.getSubcategory().getId(), dto.getSubCategory());
		}
		
		TimingsEntity timings = timingsRepo.getTimings(dto.getMedicar(), dto.getTimingsId());
		if (timings == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Timings not found!", dto.getMedicar(), dto.getTimingsId());
		}
		
		int appointmentStartTime = timings.getBeginTime() + dto.getTimingsOffset() * IConstants.SLOT_TIME;
		int duration = dto.isAtHome() ? medicar.getSubcategory().getPatientHomeDur() : medicar.getSubcategory().getClinicDur();
		int appointmentEndTime = appointmentStartTime + duration;
		
		if (appointmentEndTime > timings.getEndTime()) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Invalid time!", timings.getEndTime(), appointmentEndTime);
		}
		
		// try to check conflicts time
		LocalDateTime startDateTime = LocalDateTime.of(dto.getDate(), LocalTime.of(appointmentStartTime / 60, appointmentStartTime % 60));
		LocalDateTime endDateTime = startDateTime.plusMinutes(duration);
		List<AccountBlockTimeEntity> blockTimes = accBlockTimeRepo.getBlockTime(medicar.getId(), startDateTime, endDateTime);
		if (!blockTimes.isEmpty()) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Time is blocked!", blockTimes.toArray());
		}
		
		List<AppointmentBookingEntity> approvedAppointments = appBookingRepo.getApprovedBooking(medicar.getId(), dto.getDate(),
				appointmentStartTime, appointmentEndTime);
		if (!approvedAppointments.isEmpty()) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Conflict with another appointment!",
					approvedAppointments.stream().map((aa) -> aa.getId()).toArray());
		}
		
		AddressEntity address = (dto.isAtHome()) ? (accRepo.findOne(booker).getAddress()) : (medicar.getAddress());
		
		AppointmentBookingEntity appBooking = appBookingRepo.addAppointment(booker, address, appointmentStartTime, duration, dto);
		appPatientRepo.addNewPatientAppointment(appBooking, dto.getPatient());

		return AppointmentBookingTranslator.INST.getDto(appBooking);
	}
	
	@Override
	public void confirmAppointment(Integer booker, AppointmentConfirmDto dto) {
		AppointmentBookingEntity bookingEntity = appBookingRepo.findOne(dto.getAppointment_id());
		if (bookingEntity == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Invalid booking id", dto.getAppointment_id());
		}
		
		if (bookingEntity.getBooker().getId() != booker) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_BOOKER_MISMATCH, "Invalid booker", booker);
		}

		if (bookingEntity.getStatus() != 0) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_STATUS_MISMATCH, "Appointment is already confirmed!");
		}
		
		// try to check conflicts time
		LocalDateTime startDateTime = LocalDateTime.of(bookingEntity.getDate(), LocalTime.of(bookingEntity.getTime() / 60, bookingEntity.getTime() % 60));
		LocalDateTime endDateTime = LocalDateTime.of(bookingEntity.getDate(), LocalTime.of(bookingEntity.getEnd() / 60, bookingEntity.getEnd() % 60));
		List<AccountBlockTimeEntity> blockTimes = accBlockTimeRepo.getBlockTime(bookingEntity.getMedicar().getId(), startDateTime, endDateTime);
		if (!blockTimes.isEmpty()) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Time is blocked!", blockTimes.toArray());
		}
		
		List<AppointmentBookingEntity> approvedAppointments = appBookingRepo.getApprovedBooking(bookingEntity.getMedicar().getId(),
				bookingEntity.getDate(), bookingEntity.getTime(), bookingEntity.getEnd());
		if (!approvedAppointments.isEmpty()) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Conflict with another appointment!",
					approvedAppointments.stream().map((aa) -> aa.getId()).toArray());
		}
		
		bookingEntity.setStatus(1);
		appBookingRepo.save(bookingEntity);
	}
}
