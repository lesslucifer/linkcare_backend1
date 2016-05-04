package com.clinic.clinic.api.bizlogic.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IBlockVacationService;
import com.clinic.clinic.api.bizlogic.service.INotificationService;
import com.clinic.clinic.api.persistence.entity.AccountBlockTimeEntity;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.NotificationEntity;
import com.clinic.clinic.api.persistence.repository.IAccountBlockTimeRepository;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.IAppointmentBookingRepository;
import com.clinic.clinic.api.translator.impl.AccountBlockTimeTranslatorImpl;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.dto.biz.AccountBlockTimeDto;
import com.clinic.clinic.common.utils.DateTimeFormatters;
import com.clinic.clinic.common.utils.Utils;

@ApplicationService
public class BlockVacationServiceImpl extends AbsService implements IBlockVacationService {
	
	@Autowired
	IAccountBlockTimeRepository accBlockTimeRepo;
	
	@Autowired
	IAccountRepository accRepo;
	
	@Autowired
	IAppointmentBookingRepository appBookingRepo;
	
	@Autowired
	INotificationService notifServ;
	
	@Override
	public List<AccountBlockTimeDto> getBlockVacations(Integer accountId, LocalDateTime start, LocalDateTime end) {
		List<AccountBlockTimeEntity> entities = accBlockTimeRepo.getBlockTime(accountId, start, end);
		if (entities == null) {
			return Collections.emptyList();
		}

		return AccountBlockTimeTranslatorImpl.INST.getDtoList(entities);
	}
	
	@Override
	public List<AccountBlockTimeDto> getBlockVacations(Integer accountId, List<Integer> ids) {
		List<AccountBlockTimeEntity> entities = accBlockTimeRepo.getBlockTime(accountId, ids);
		if (entities == null) {
			return Collections.emptyList();
		}

		return AccountBlockTimeTranslatorImpl.INST.getDtoList(entities);
	}

	@Override
	public AccountBlockTimeDto addBlockVacation(Integer accountId, AccountBlockTimeDto dto) {
		final LocalDateTime begin = dto.getBegin();
		final LocalDateTime end = begin.plusMinutes(dto.getLength());
		
		LocalDateTime now = LocalDateTime.now();
		if (end.compareTo(now) < 0) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.BLOCK_VACATION_TIME_IN_PAST,
					"Cannot set vacation time in past");
		}
		
		if (accBlockTimeRepo.hasBlockTime(accountId, begin, end)) {
			throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.BLOCK_VACATION_CONFLICT_TIME,
					"CONFLICT TIME");
		}

		List<AppointmentBookingEntity> appointments = appBookingRepo
				.getActiveAppointments(accountId, begin.toLocalDate(), end.toLocalDate());

		if (!appointments.isEmpty()) {
			List<Runnable> sendNotifs = new ArrayList<>(appointments.size());
			
			final int beginInMin = begin.getHour() * 60 + begin.getMinute();
			final int endInMin = end.getHour() * 60 + end.getMinute();
			List<AppointmentBookingEntity> edittedAppointments = new ArrayList<>(appointments.size());
			for (AppointmentBookingEntity appointment : appointments) {
				if (appointment.getEnd() <= beginInMin || appointment.getTime() >= endInMin) {
					continue;
				}
				
				// appointment in vacation time, auto cancel - reject it
				if (appointment.getStatus() == AppointmentBookingEntity.STATUS_WAITING) {
					appointment.setStatus(AppointmentBookingEntity.STATUS_REJECTED);
					edittedAppointments.add(appointment);
					
					// send notification
					sendNotifs.add(() -> {
						AccountEntity bookerEnt = appointment.getBooker();
						AccountEntity medicarEnt = appointment.getMedicar();
						LocalDateTime time = Utils.toDateTime(appointment.getDate(), appointment.getTime());
						StringBuilder content = new StringBuilder();
						content.append("<b>Bác sĩ ");
						medicarEnt.getFullName(content);
						content.append("</b> đã từ chối cuộc hẹn bạn đặt <b>vào lúc ");
						content.append(time.format(DateTimeFormatters.HOUR_MINUTE_FORMATTER));
						content.append(" ngày ");
						content.append(time.format(DateTimeFormatters.DATE_FORMATTER));
						content.append("</b>");
						notifServ.sendMessage(null, bookerEnt.getId(), NotificationEntity.TYPE_APPOINTMENT_REJECTED,
								content.toString(), appointment.getId(), appointment.getMedicar().getId());
					});
				}
				else if (appointment.getStatus() == AppointmentBookingEntity.STATUS_APPROVED) {
					appointment.setStatus(AppointmentBookingEntity.STATUS_CANCELLED);
					edittedAppointments.add(appointment);
					
					// notify here
					sendNotifs.add(() -> {
						AccountEntity bookerEnt = appointment.getBooker();
						AccountEntity medicarEnt = appointment.getMedicar();
						LocalDateTime time = Utils.toDateTime(appointment.getDate(), appointment.getTime());
						String title = "Bác sĩ";
						AccountEntity cancellerEnt = medicarEnt;
						AccountEntity cancelleeEnt = bookerEnt;
						
						StringBuilder content = new StringBuilder();
						content.append("<b>").append(title).append(" ");
						cancellerEnt.getFullName(content);
						content.append("</b> đã hủy cuộc hẹn lúc <b>");
						content.append(time.format(DateTimeFormatters.HOUR_MINUTE_FORMATTER));
						content.append(" ngày ");
						content.append(time.format(DateTimeFormatters.DATE_FORMATTER));
						content.append("</b>. Lý do: Bác sĩ có việc bận.");
						notifServ.sendMessage(null, cancelleeEnt.getId(), NotificationEntity.TYPE_APPOINTMENT_CANCELLED,
								content.toString(), appointment.getId(), appointment.getMedicar().getId(), appointment.getBooker().getId());
					});
				}
				else if (appointment.getStatus() == AppointmentBookingEntity.STATUS_PROCESSING) {
					throwBizlogicException(HttpStatus.CONFLICT, IBizErrorCode.BLOCK_VACATION_HAS_PROCESSING_APPOINTMENT,
							"Has processing appointment", appointment.getId());
				}
			}
			
			// save all editted appointments
			if (!edittedAppointments.isEmpty()) {
				appBookingRepo.save(edittedAppointments);
			}
			
			// send notifications
			sendNotifs.forEach((r) -> r.run());
		}
		
		AccountBlockTimeEntity entity = AccountBlockTimeTranslatorImpl.INST.getEntity(dto);
		entity.setAccount(accRepo.getReference(AccountEntity.class, accountId));
		entity = accBlockTimeRepo.save(entity);
		
		return AccountBlockTimeTranslatorImpl.INST.getDto(entity);
	}

	@Override
	public AccountBlockTimeDto deleteBlockVacation(Integer accountId, Integer blockTimeId) {
		AccountBlockTimeEntity entity = accBlockTimeRepo.getOne(blockTimeId);
		if (entity == null) {
			throwBizlogicException(HttpStatus.NOT_FOUND, IBizErrorCode.OBJECT_NOT_FOUND,
					"Invalid block vacation id", blockTimeId);
		}
		
		if (accountId != entity.getAccount().getId()) {
			throwBizlogicException(HttpStatus.BAD_REQUEST, IBizErrorCode.BLOCK_VACATION_INVALID_OWNER,
					"Invalid vacation owner", accountId);
		}
		
		AccountBlockTimeDto retDto = AccountBlockTimeTranslatorImpl.INST.getDto(entity);
		accBlockTimeRepo.delete(entity);
		return retDto;
	}

}
