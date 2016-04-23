   package com.clinic.clinic.api.ws.biz;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.IAppointmentService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.AppointmentBookingRequestDto;
import com.clinic.clinic.common.utils.JsonJava8TimeSerialization;
import com.clinic.clinic.common.utils.Utils;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class AppointmentRestApi extends AbsRestApi {

	@Autowired
	private IAppointmentService appServ;

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENTS_SINGLE, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getAppointment(@RequestHeader("sess") String session,
			@PathVariable String appointment_id,
    		HttpServletResponse response) {
		validate(appointment_id);
		Integer requester = auth().authSession(session);
		
		List<Integer> appointmentIds = Arrays.stream(appointment_id.split(","))
				.map((sId) -> Integer.parseInt(sId)).collect(Collectors.toList());
		
		return appServ.getAppointments(requester, appointmentIds);
	}
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENTS, method = RequestMethod.POST, produces = {
    "application/json" })
    public Object bookAppointment(@RequestHeader("sess") String session,
    		@RequestBody AppointmentBookingRequestDto dto,
    		HttpServletResponse response) {
		validate(dto);
		Integer patientId = auth().authSession(session);
		auth().authRight(patientId, IDbConstants.RIGHT_BOOK_APPOINTMENT);
		
		return appServ.bookAppointment(patientId, dto);
    }
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENTS_APPROVE, method = RequestMethod.PUT, produces = {
    "application/json" })
	public Object approveAppointment(@RequestHeader("sess") String session,
    		@PathVariable Integer appointment_id,
    		HttpServletResponse response) {
		validate(appointment_id);
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_APPROVE_APPOINTMENT);

		appServ.approveAppointment(medicar, appointment_id);
		return Utils.mkMap();
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENTS_REJECT, method = RequestMethod.PUT, produces = {
    "application/json" })
	public Object rejectAppointment(@RequestHeader("sess") String session,
			@PathVariable Integer appointment_id,
    		HttpServletResponse response) {
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_REJECT_APPOINTMENT);
		
		appServ.rejectAppointment(medicar, appointment_id);
		return Utils.mkMap();
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENTS_CANCEL, method = RequestMethod.PUT, produces = {
    "application/json" })
	public Object cancelAppointment(@RequestHeader("sess") String session,
    		@PathVariable Integer appointment_id,
    		HttpServletResponse response) {
		validate(appointment_id);
		Integer canceller = auth().authSession(session);
		auth().authRight(canceller, IDbConstants.RIGHT_CANCEL_APPOINTMENT);
		
		appServ.cancelAppointment(canceller, appointment_id);
		return Utils.mkMap();
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENTS_START, method = RequestMethod.PUT, produces = {
    "application/json" })
	public Object startAppointment(@RequestHeader("sess") String session,
    		@PathVariable Integer appointment_id,
    		HttpServletResponse response) {
		validate(appointment_id);
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_START_APPOINTMENT);
		
		appServ.startAppointment(medicar, appointment_id);
		return Utils.mkMap();
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_MEDICAR_APPOINTMENTS_DATE, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getActiveAppointments(@RequestHeader("sess") String session,
    		@PathVariable String date,
    		HttpServletResponse response) {
		
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_GET_MEDICAR_APPOINTMENT);
		
		LocalDate localDate = LocalDate.parse(date, JsonJava8TimeSerialization.DATE_FORMATTER);
		
		return appServ.getMedicarAppointments(medicar, localDate);
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_MEDICAR_APPOINTMENTS_TODAY, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getTodayActiveAppointments(@RequestHeader("sess") String session,
    		HttpServletResponse response) {
		
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_GET_MEDICAR_APPOINTMENT);
		
		return appServ.getMedicarAppointments(medicar, LocalDate.now());
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_MEDICAR_APPOINTMENTS_BY_TYPE, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getActiveAppointmentsByType(@RequestHeader("sess") String session,
    		@PathVariable String date,
    		@PathVariable Integer type,
    		HttpServletResponse response) {
		
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_GET_MEDICAR_APPOINTMENT);

		LocalDate localDate = null;
		if ("today".equals(date)) {
			localDate = LocalDate.now();
		}
		else {
			localDate = LocalDate.parse(date, JsonJava8TimeSerialization.DATE_FORMATTER);
		}
		
		return appServ.getMedicarAppointmentsByType(medicar, localDate, type);
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_COUNT_MEDICAR_APPOINTMENTS_BY_TYPE, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getCountActiveAppointmentsByType(@RequestHeader("sess") String session,
    		@PathVariable String date,
    		@PathVariable Integer type,
    		HttpServletResponse response) {
		
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_GET_MEDICAR_APPOINTMENT);

		LocalDate localDate = null;
		if ("today".equals(date)) {
			localDate = LocalDate.now();
		}
		else {
			localDate = LocalDate.parse(date, JsonJava8TimeSerialization.DATE_FORMATTER);
		}
		
		return Utils.mkMap("count", appServ.countMedicarAppointmentsByType(medicar, localDate, type));
	}

	@RequestMapping(value = IRestApiUrlMaps.REST_API_APPOINTMENTS_BY_STATUS, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getAppointmentsByStatus(@RequestHeader("sess") String session,
    		@PathVariable("status") Integer status,
    		HttpServletResponse response) {
		
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_GET_MEDICAR_APPOINTMENT);

		return appServ.getAppointmentByStatus(medicar, status);
	}
}