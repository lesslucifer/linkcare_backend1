package com.clinic.clinic.api.bizlogic.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IMajorService;
import com.clinic.clinic.api.persistence.entity.MajorEntity;
import com.clinic.clinic.api.persistence.repository.IMajorRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.MajorTranslatorImpl;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.biz.MajorDto;
import com.clinic.clinic.common.exception.BizlogicException;

@ApplicationService
public class MajorServiceImpl extends AbsService implements IMajorService{

	/** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MajorServiceImpl.class);
    
	@Autowired
    private IMajorRepository majorRepository;
    
	private ITranslator<MajorDto, MajorEntity> trans = new MajorTranslatorImpl();
	
	@Override
	public Page<MajorDto> getAllMajor(Pageable range) throws BizlogicException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(IConstants.BEGIN_METHOD);
		}
		Page<MajorDto> pageDto = null;
		try {
			List<MajorEntity> ents = majorRepository.findAll(range).getContent();
			pageDto = new PageImpl<MajorDto>(trans.getDtoList(ents));
		} catch (BizlogicException be) {
			LOGGER.error("Error", be);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		} finally {
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug(IConstants.END_METHOD);
			}
		}
		return pageDto;
	}
}
