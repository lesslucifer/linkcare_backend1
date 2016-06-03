package com.clinic.clinic.api.ws.biz;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.IMedicarProfileService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.MedicarProfileDto;
import com.clinic.clinic.common.dto.biz.MedicarRegisterDto;
import com.clinic.clinic.common.utils.Utils;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class MedicarProfileRestApi extends AbsRestApi {

	@Autowired
	IMedicarProfileService medicarProfileServ;
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_MEDICAR_REGISTER, method = RequestMethod.POST, produces = {
    "application/json" })
	public Object registerMedicar(@RequestHeader("sess") String session,
			@RequestBody MedicarRegisterDto body) {
		Integer requester = auth().authSession("session");
		auth().authRight(requester, IDbConstants.RIGHT_UPDATE_MEDICAR_PROFILE);
		
		return Utils.mkMap();
	}
	
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_MEDICAR_PROFILE_SINGLE, method = RequestMethod.GET, produces = {
    "application/json" })
	public Object getMedicarProfile(@RequestHeader("sess") String session,
			@PathVariable("account_id") String _accountId,
    		HttpServletResponse response) {
		Integer requester = auth().authSession(session);
		
		Integer accountId = "me".equals(_accountId) ? requester : Integer.parseInt(_accountId);
		auth().authRight(accountId, IDbConstants.RIGHT_HAS_MEDICAR_PROFILE);
		
		return medicarProfileServ.getMedicarProfile(accountId);
	}
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_MEDICAR_PROFILE_SINGLE, method = RequestMethod.PUT, produces = {
    "application/json" })
	public Object updateMedicarProfile(@RequestHeader("sess") String session,
			@PathVariable("account_id") Integer accountId,
			@RequestBody MedicarProfileDto dto,
    		HttpServletResponse response) {
		validate(accountId);
		validate(dto);
		
		Integer requester = auth().authSession(session);
		auth().authRight(requester, IDbConstants.RIGHT_UPDATE_MEDICAR_PROFILE);
		auth().authRight(accountId, IDbConstants.RIGHT_HAS_MEDICAR_PROFILE);
		
		medicarProfileServ.updateMedicarProfile(accountId, dto);
		return Utils.mkMap();
	}
}
