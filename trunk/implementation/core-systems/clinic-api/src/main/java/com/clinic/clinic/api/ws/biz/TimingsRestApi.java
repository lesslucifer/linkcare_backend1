package com.clinic.clinic.api.ws.biz;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.ITimingsService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;
import com.clinic.clinic.common.utils.Utils;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class TimingsRestApi extends AbsRestApi {
    /** Logging property. */
//    private static final Logger LOGGER = LoggerFactory.getLogger(TimingsRestApi.class);

    @Autowired
    private ITimingsService timingsServ;
	
	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_TIMINGS, method = RequestMethod.GET, produces = {
    "application/json" })
    public Object getTimings(@RequestHeader("sess") String session,
    		HttpServletResponse response) {
		Integer accountId = auth().authSession(session);
		auth().authRight(accountId, IDbConstants.RIGHT_UPDATE_TIMINGS);

		return Utils.mkMap("data", timingsServ.getAccountTimings(accountId));
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_TIMINGS, method = RequestMethod.POST, produces = {
    "application/json" })
    public void setTimings(@RequestHeader("sess") String session,
    		@RequestBody AccountTimingsDto body,
    		HttpServletResponse response) {
		Integer accountId = auth().authSession(session);
		auth().authRight(accountId, IDbConstants.RIGHT_UPDATE_TIMINGS);

		timingsServ.updateAccountTimings(accountId, body);
		return204(response);
    }
}
