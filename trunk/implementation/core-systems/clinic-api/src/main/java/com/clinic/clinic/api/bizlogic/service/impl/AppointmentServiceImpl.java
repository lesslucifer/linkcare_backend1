package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
import com.clinic.clinic.api.translator.impl.TraceTranslatorImpl;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.TraceDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingDto;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;
import com.clinic.clinic.common.utils.Utils;

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
	public List<AppointmentBookingDto> getAppointments(Integer requester, List<Integer> appointmentIds) {
		if (requester == null) {
			return Collections.emptyList();
		}
		
		List<AppointmentBookingEntity> entities = appBookingRepo.findAll(appointmentIds);
		
		Predicate<AppointmentBookingEntity> hasPermission = (ab) -> {
			return requester.equals(ab.getBooker().getId()) || requester.equals(ab.getMedicar().getId());
		};
		entities = entities.stream().filter(hasPermission).collect(Collectors.toList());
		
		return AppointmentBookingTranslator.INST.getDtoList(entities);
	}
	
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
		
		TimingsEntity timings = timingsRepo.getTimingsAtTime(dto.getMedicar(), dto.getTime());
		if (timings == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Timings not found!", dto.getMedicar(), dto.getTime());
		}

		if (((dto.getTime() - timings.getBeginTime()) % IConstants.SLOT_TIME) != 0) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_INVALID_TIME,
					"Appointment's time doesn't match with timings slot", dto.getMedicar(), dto.getTime());
		}
		
		int appointmentStartTime = dto.getTime();
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
		
		boolean hasActiveAppointment = appBookingRepo.hasActiveAppointment(medicar.getId(), dto.getDate(),
				appointmentStartTime, appointmentEndTime);
		if (hasActiveAppointment) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Conflict with another appointment!");
		}
		
		AddressEntity address = (dto.isAtHome()) ? (accRepo.findOne(booker).getAddress()) : (medicar.getAddress());
		
		AppointmentBookingEntity appBooking = appBookingRepo.addAppointment(booker, address, appointmentStartTime, duration, dto);
		appPatientRepo.addNewPatientAppointment(appBooking, dto.getPatient());

		return AppointmentBookingTranslator.INST.getDto(appBooking);
	}
	
	@Override
	public void approveAppointment(Integer medicar, Integer appointmentId) {
		AppointmentBookingEntity appBooking = appBookingRepo.findOne(appointmentId);
		if (appBooking == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Appointment not found!", appointmentId);
		}
		
		if (appBooking.getMedicar().getId() != medicar) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_MEDICAR_MISMATCH, "Medicar mismatch!",
					medicar);
		}
		
		if (appBooking.getStatus() != AppointmentBookingEntity.STATUS_WAITING) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_STATUS_MISMATCH, "Status mismatch!",
					appBooking.getStatus());
		}

		LocalDateTime now = LocalDateTime.now();
		if (now.compareTo(Utils.toDateTime(appBooking.getDate(), appBooking.getTime())) >= 0) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_INVALID_APPROVE_TIME, "Cannot approve over appointment",
					now);
		}

		if (appBookingRepo.hasApprovedBooking(medicar, appBooking.getDate(), appBooking.getTime(), appBooking.getEnd())) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Conflict with another appointment!");
		}
		
		appBooking.setStatus(AppointmentBookingEntity.STATUS_APPROVED);
		appBookingRepo.save(appBooking);
	}

	@Override
	public void rejectAppointment(Integer medicar, Integer appointmentId) {
		// TODO Auto-generated method stub
		AppointmentBookingEntity appBooking = appBookingRepo.findOne(appointmentId);
		if (appBooking == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Appointment not found!", appointmentId);
		}
		
		if (appBooking.getMedicar().getId() != medicar) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_MEDICAR_MISMATCH, "Medicar mismatch!",
					medicar);
		}
		
		if (appBooking.getStatus() != AppointmentBookingEntity.STATUS_WAITING) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_STATUS_MISMATCH, "Status mismatch!",
					appBooking.getStatus());
		}
		
		appBooking.setStatus(AppointmentBookingEntity.STATUS_REJECTED);
		appBookingRepo.save(appBooking);
	}

	@Override
	public void cancelAppointment(Integer canceller, Integer appointmentId) {
		AppointmentBookingEntity appBooking = appBookingRepo.findOne(appointmentId);
		if (appBooking == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Appointment not found!", appointmentId);
		}
		
		if (appBooking.getMedicar().getId() != canceller && appBooking.getBooker().getId() != canceller) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_MEDICAR_MISMATCH, "Medicar mismatch!",
					canceller);
		}
		
		if (appBooking.getStatus() != AppointmentBookingEntity.STATUS_APPROVED) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_STATUS_MISMATCH, "Status mismatch!",
					appBooking.getStatus());
		}
		
		appBooking.setStatus(AppointmentBookingEntity.STATUS_CANCELLED);
		appBookingRepo.save(appBooking);
	}

	@Override
	public void startAppointment(Integer medicar, Integer appointmentId) {
		AppointmentBookingEntity appBooking = appBookingRepo.findOne(appointmentId);
		if (appBooking == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND, "Appointment not found!", appointmentId);
		}
		
		if (appBooking.getMedicar().getId() != medicar) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_MEDICAR_MISMATCH, "Medicar mismatch!",
					medicar);
		}
		
		if (appBooking.getStatus() != AppointmentBookingEntity.STATUS_APPROVED) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_STATUS_MISMATCH, "Status mismatch!",
					appBooking.getStatus());
		}
		
		LocalDateTime now = LocalDateTime.now();
		if (now.toLocalDate().compareTo(appBooking.getDate()) != 0) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Invalid appointment time, wrong day",
					appBooking);
		}

		int timeInMinutes = now.getHour() * 60 + now.getMinute();
		if (timeInMinutes < appBooking.getTime() || timeInMinutes > appBooking.getEnd()) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_INVALID_TIME, "Invalid appointment time, out of time",
					appBooking.getTime(), appBooking.getEnd());
		}
		
		// make sure there's no unfinished appointment
		if (appBookingRepo.hasProcessingAppointment(medicar)) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.APPOINTMENT_HAVE_UNFINISHED_APPOINTMENT, "Have another unfinished appointment");
		}
		
		appBooking.setStatus(AppointmentBookingEntity.STATUS_PROCESSING);
		appBookingRepo.save(appBooking);
	}
	
	@Override
	public List<TraceDto> getMedicarAppointments(Integer medicar, LocalDate date) {
		List<AppointmentBookingEntity> entities = appBookingRepo.getActiveAppointments(medicar, date);
		return TraceTranslatorImpl.INSTANCE.getDtoList(entities);
	}
	
	@Override
	public List<TraceDto> getMedicarAppointmentsByType(Integer medicar, LocalDate date, int type) {
		if (type != 0 && type !=1) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_INVALID_TYPE, "Invalid appointment type", type);
		}
		
		List<AppointmentBookingEntity> entities = appBookingRepo.getActiveAppointmentsAtHome(medicar, date, type != 0);
		return TraceTranslatorImpl.INSTANCE.getDtoList(entities);
	}
	
	@Override
	public int countMedicarAppointmentsByType(Integer medicar, LocalDate date, int type) {
		if (type != 0 && type !=1) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.APPOINTMENT_INVALID_TYPE, "Invalid appointment type", type);
		}

		return appBookingRepo.countActiveAppointmentsAtHome(medicar, date, type != 0);
	}
}
