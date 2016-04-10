package com.clinic.clinic.common.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.clinic.clinic.common.utils.Utils;

@ControllerAdvice
@Component
public class BizLogicResponseAdvice implements ResponseBodyAdvice<Object>{

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter retType, MediaType contentType,
			Class<? extends HttpMessageConverter<?>> converter, ServerHttpRequest req, ServerHttpResponse resp) {
		if (body == null) {
			body = Collections.emptyMap();
		}
		else if (body instanceof Map) {
			Map<?, ?> dataBody = (Map<?, ?>) body;
			if (dataBody.containsKey("error")) { // there's error, response as error format
				return Utils.mkMap(
						"status", false,
						"error", dataBody.get("error")
						);
			}
		}
		
		// success, response as success format
		return Utils.mkMap(
				"status", true,
				"data", body);
	}

	@Override
	public boolean supports(MethodParameter retType, Class<? extends HttpMessageConverter<?>> converter) {
		return true;
	}

}
