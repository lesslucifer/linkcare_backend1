package com.clinic.clinic.api.ws.biz;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.persistence.repository.ITimingsRepository;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.exception.BizlogicException;

@RestController
@RequestMapping(value = "test")
public class TestRestApi extends AbsRestApi {
	
	@Autowired
	private ITimingsRepository timingsRepo;

	@RequestMapping(value = "/timings", method = RequestMethod.GET, produces = {
    "application/json" })
    public Object getTimings(HttpServletResponse response, @RequestParam Integer medicar, @RequestParam Integer timingsId) {
		Object ret = timingsRepo.getTimings(medicar, timingsId);
		if (ret == null) {
			BizlogicException.throwEx(404, -1, "Timings not found");
		}
		
		return ret;
    }
}