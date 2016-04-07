package com.clinic.clinic.api.ws.biz;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.IPrescriptionService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.PrescriptionSubmitDto;
import com.clinic.clinic.common.utils.Utils;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class PrescriptionRestApi extends AbsRestApi {
	
	@Autowired
	private IPrescriptionService prescriptionServ;

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_PRESCRIPTIONS_SINGLE, method = RequestMethod.GET, produces = {
    "application/json" })
    public Object getPrescriptions(@RequestHeader("sess") String session,
    		@PathVariable("prescription_id") String prescriptionIds,
    		HttpServletResponse response) {
		validate(prescriptionIds);
		Integer requesters = auth().authSession(session);
		
		List<Integer> ids = Arrays.stream(prescriptionIds.split(","))
				.map(Integer::parseInt).collect(Collectors.toList());
		
		return Utils.mkMap("data", prescriptionServ.getPrescriptions(requesters, ids));
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_PRESCRIPTIONS, method = RequestMethod.POST, produces = {
    "application/json" })
    public Object submitPrescription(@RequestHeader("sess") String session,
    		@RequestBody PrescriptionSubmitDto dto,
    		HttpServletResponse response) {
		validate(dto);
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_SUBMIT_PRESCRIPTION);
		
		return Utils.mkMap("data", prescriptionServ.submitPrescription(medicar, dto));
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_PATIENT_PRESCRIPTIONS, method = RequestMethod.GET, produces = {
    "application/json" })
    public Object getPrescriptionHistory(@RequestHeader("sess") String session,
    		@PathVariable("patient_id") Integer patient,
    		@RequestParam("appointment_id") Integer appointment,
    		HttpServletResponse response) {
		validate(patient);
		validate(appointment);
		
		Integer medicar = auth().authSession(session);
		auth().authRight(medicar, IDbConstants.RIGHT_GET_PRESCRIPTION_HISTORY);
		
		return Utils.mkMap("data", prescriptionServ.getPrescriptionHistory(medicar, patient, appointment));
    }
}
