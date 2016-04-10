package com.clinic.clinic.api.ws.biz;

import java.time.LocalDateTime;
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

import com.clinic.clinic.api.bizlogic.service.IBlockVacationService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.AccountBlockTimeDto;
import com.clinic.clinic.common.utils.JsonJava8TimeSerialization;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class BlockVacationRestApi extends AbsRestApi {
	
	@Autowired
	private IBlockVacationService blockVacationsServ;

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_BLOCK_VACATIONS, method = RequestMethod.GET, produces = {
    "application/json" })
    public Object getBlockVacations(@RequestHeader("sess") String session,
    		@RequestParam("from") String from,
    		@RequestParam("to") String to,
    		HttpServletResponse response) {
		Integer requester = auth().authSession(session);
		auth().authRight(requester, IDbConstants.RIGHT_BLOCK_VACATION);
		
		LocalDateTime fromDateTime = LocalDateTime.parse(from, JsonJava8TimeSerialization.DATETIME_FORMATTER);
		LocalDateTime toDateTime = LocalDateTime.parse(to, JsonJava8TimeSerialization.DATETIME_FORMATTER);
		
		return blockVacationsServ.getBlockVacations(requester, fromDateTime, toDateTime);
    }
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_BLOCK_VACATIONS, method = RequestMethod.POST, produces = {
    "application/json" })
    public Object addNewBlockVacations(@RequestHeader("sess") String session,
    		@RequestBody AccountBlockTimeDto dto,
    		HttpServletResponse response) {
		
		validate(dto);
		Integer accountId = auth().authSession(session);
		auth().authRight(accountId, IDbConstants.RIGHT_BLOCK_VACATION);
		
		return blockVacationsServ.addBlockVacation(accountId, dto);
    }
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_BLOCK_VACATIONS_SINGLE, method = RequestMethod.GET, produces = {
    "application/json" })
    public Object getBlockVacations(@RequestHeader("sess") String session,
    		@PathVariable("block_vacation_id") String idList,
    		HttpServletResponse response) {
		
		Integer accountId = auth().authSession(session);
		auth().authRight(accountId, IDbConstants.RIGHT_BLOCK_VACATION);
		
		List<Integer> ids = Arrays.stream(idList.split(","))
				.map(Integer::parseInt).collect(Collectors.toList());
		
		return blockVacationsServ.getBlockVacations(accountId, ids);
    }
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_BLOCK_VACATIONS_SINGLE, method = RequestMethod.DELETE, produces = {
    "application/json" })
    public Object deleteBlockVacation(@RequestHeader("sess") String session,
    		@PathVariable("block_vacation_id") Integer id,
    		HttpServletResponse response) {
		
		Integer accountId = auth().authSession(session);
		auth().authRight(accountId, IDbConstants.RIGHT_BLOCK_VACATION);
		
		return blockVacationsServ.deleteBlockVacation(accountId, id);
    }
}
