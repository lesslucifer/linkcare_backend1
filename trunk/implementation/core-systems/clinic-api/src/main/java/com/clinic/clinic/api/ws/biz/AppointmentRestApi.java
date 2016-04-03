package com.clinic.clinic.api.ws.biz;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.clinic.clinic.common.dto.biz.AppointmentConfirmDto;
import com.clinic.clinic.common.utils.Utils;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class AppointmentRestApi extends AbsRestApi {

	@Autowired
	private IAppointmentService appServ;

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENT_BOOK, method = RequestMethod.POST, produces = {
    "application/json" })
    public Object bookAppointment(@RequestHeader("sess") String session,
    		@RequestBody AppointmentBookingRequestDto dto,
    		HttpServletResponse response) {
		validate(dto);
		Integer patientId = auth().authSession(session);
		auth().authRight(patientId, IDbConstants.RIGHT_BOOK_APPOINTMENT);
		
		return Utils.mkMap("data", appServ.bookAppointment(patientId, dto));
    }
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_APPOINTMENT_APPROVE, method = RequestMethod.POST, produces = {
    "application/json" })
	public Object approveAppointment(@RequestHeader("sess") String session,
    		@RequestBody AppointmentBookingRequestDto dto,
    		HttpServletResponse response) {
		return null;
	}
}