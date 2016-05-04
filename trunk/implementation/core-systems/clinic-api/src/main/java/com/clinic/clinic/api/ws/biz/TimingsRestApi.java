package com.clinic.clinic.api.ws.biz;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.ITimingsService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.AccountTimingsDto;
import com.clinic.clinic.common.dto.biz.TimingsDayDto;
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

		return timingsServ.getAccountTimings(accountId);
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_TIMINGS, method = RequestMethod.POST, produces = {
    "application/json" })
    public Object setTimings(@RequestHeader("sess") String session,
    		@RequestBody AccountTimingsDto body,
    		HttpServletResponse response) {
		validate(body);
		Integer accountId = auth().authSession(session);
		auth().authRight(accountId, IDbConstants.RIGHT_UPDATE_TIMINGS);

		timingsServ.updateAccountTimings(accountId, body);
		return Utils.mkMap();
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_TIMINGS_SLOT, method = RequestMethod.GET, produces = {
    "application/json" })
    public List<TimingsDayDto> getTimingsSlot(@RequestHeader("sess") String session,
    		@PathVariable("targetId") Integer targetId,
    		@RequestParam("date") String date,
    		HttpServletResponse response) {
		auth().authSession(session);

		return timingsServ.getTimingDaySlots(targetId, date, 30, i -> true);
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_TIMINGS_SLOT_TYPE, method = RequestMethod.GET, produces = {
    "application/json" })
    public List<TimingsDayDto> getTimingsSlotByType(@RequestHeader("sess") String session,
    		@PathVariable("targetId") Integer targetId,
    		@PathVariable("type") Integer type,
    		@RequestParam("date") String date,
    		HttpServletResponse response) {
		auth().authSession(session);

		return timingsServ.getTimingDaySlots(targetId, date, 30, i -> i == type);
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_MEDICAR_TIMINGS_SLOT, method = RequestMethod.GET, produces = {
    "application/json" })
    public TimingsDayDto getMedicarTimingsSlot(@RequestHeader("sess") String session,
    		@RequestParam("date") String date,
    		HttpServletResponse response) {
		Integer medicar = auth().authSession(session);

		List<TimingsDayDto> daySlots = timingsServ.getTimingDaySlots(medicar, date, 1, i -> true);
		if (daySlots == null || daySlots.isEmpty()) {
			return new TimingsDayDto();
		}
		
		return daySlots.get(0);
    }

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_MEDICAR_TIMINGS_SLOT_COUNT, method = RequestMethod.GET, produces = {
    "application/json" })
    public Object getTimingsSlot(@RequestHeader("sess") String session,
    		@RequestParam("date") String date,
    		HttpServletResponse response) {
		Integer medicar = auth().authSession(session);

		return timingsServ.countTimingDaySlots(medicar, date);
    }
}
