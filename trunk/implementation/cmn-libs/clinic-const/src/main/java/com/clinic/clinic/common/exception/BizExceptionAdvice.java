package com.clinic.clinic.common.exception;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.utils.Utils;

@ControllerAdvice
public class BizExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
	public @ResponseBody Object handleDefaultException(HttpServletRequest request, HttpServletResponse response, Exception e) {
    	response.setStatus(this.resolveAnnotatedResponseStatus(e));

		return Utils.mkMap("error", Utils.mkMap(
					"code", IBizErrorCode.UNKNOWN_ERROR,
					"msg", e.getLocalizedMessage(),
					"params", Collections.emptyList()
				));
	}
	
    @ExceptionHandler(value = BizlogicException.class)
	public @ResponseBody Object handleBizException(HttpServletResponse response, BizlogicException e) {
    	if (e.getHttpCode() != 0) {
    		response.setStatus(e.getHttpCode());
    	}
    	else {
    		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    	}
		return Utils.mkMap("error", Utils.mkMap(
					"code", e.getBizCode(),
					"msg", e.getLocalizedMessage(),
					"params", e.getParamValues()
				));
	}
    
    private int resolveAnnotatedResponseStatus(Exception ex) {
    	ResponseStatus responseStatus = AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
		if (responseStatus != null) {
			return responseStatus.code().value();
		}
		else if (ex.getCause() instanceof Exception) {
			ex = (Exception) ex.getCause();
			return resolveAnnotatedResponseStatus(ex);
		}
		return this.defaultResolveStatus(ex);
    }

    private int defaultResolveStatus(Exception ex) {
		if (ex instanceof NoSuchRequestHandlingMethodException) {
			return HttpServletResponse.SC_NOT_FOUND;
		}
		else if (ex instanceof HttpRequestMethodNotSupportedException) {
			return HttpServletResponse.SC_METHOD_NOT_ALLOWED;
		}
		else if (ex instanceof HttpMediaTypeNotSupportedException) {
			return HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
		}
		else if (ex instanceof HttpMediaTypeNotAcceptableException) {
			return HttpServletResponse.SC_NOT_ACCEPTABLE;
		}
		else if (ex instanceof MissingPathVariableException) {
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		else if (ex instanceof MissingServletRequestParameterException) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		else if (ex instanceof ServletRequestBindingException) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		else if (ex instanceof ConversionNotSupportedException) {
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		else if (ex instanceof TypeMismatchException) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		else if (ex instanceof HttpMessageNotReadableException) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		else if (ex instanceof HttpMessageNotWritableException) {
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		else if (ex instanceof MethodArgumentNotValidException) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		else if (ex instanceof MissingServletRequestPartException) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		else if (ex instanceof BindException) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		else if (ex instanceof NoHandlerFoundException) {
			return HttpServletResponse.SC_NOT_FOUND;
		}
		
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }
}
