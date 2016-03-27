package com.clinic.clinic.api.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clinic.clinic.common.exception.BizlogicException;

@ControllerAdvice
public class BizExceptionAdvice {
	
    @ExceptionHandler(value = BizlogicException.class)
	public @ResponseBody Object handleBizException(HttpServletResponse response, BizlogicException e) {
		response.setStatus(500);

		Map<String, Object> resp = new HashMap<>();
		resp.put("error", e.getLocalizedMessage());
		resp.put("params", e.getParamValues());
		
		return resp;
	}
}
