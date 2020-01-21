package com.clinic.clinic.api.ws.biz;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.ws.AbsRestApi;

@RestController
@RequestMapping(value = "test")
public class TestRestApi extends AbsRestApi {
	
}