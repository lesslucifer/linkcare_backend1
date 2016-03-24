package com.clinic.clinic.api.bizlogic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.clinic.common.dto.biz.MajorDto;
import com.clinic.clinic.common.exception.BizlogicException;

public interface IMajorService {

	Page<MajorDto> getAllMajor(Pageable range) throws BizlogicException;

}
