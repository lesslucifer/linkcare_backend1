/**==============================================================================
 * clinic JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 clinic JSC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 *
 * This software is the confidential and proprietary information of 
 * clinic J.S.C ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license  
 * agreement, nondisclosure agreement or contract entered into with 
 * clinic providing access to this software.
 *
 * Project name  : clinic-api<br>
 * File name     : AbstractTranslatorImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Sep 15, 2015		Long Phan			Initial<br>
 * </p>
 *
 *=============================================================================*/
package com.clinic.clinic.api.translator.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.IdEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.IdDto;
import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.exception.BizlogicException;

/**
 * <p>
 * Abstract base class for all Translator impelement.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see @see {@link com.clinic.clinic.webapp.cliniccollect.tranlator.Translator} 
 */
public abstract class AbstractTranslatorImpl <DTO /*extends IdDto*/, ENT /*extends IdEntity*/> implements ITranslator<DTO, ENT> {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTranslatorImpl.class);
    
    private final Class<DTO> dtoClass;
    private final Class<ENT> entClass;

    protected AbstractTranslatorImpl(Class<DTO> dtoClass, Class<ENT> entClass) {
		super();
		this.dtoClass = dtoClass;
		this.entClass = entClass;
	}

	@Override
	public DTO getDto(ENT ent) {
        DTO dto = null;
		try {
			dto = dtoClass.newInstance();
		} catch (Exception e) {
			BizlogicException bizEx = new BizlogicException("Cannot init dto", e);
			bizEx.addParamValue(dtoClass.getName());
			throw bizEx;
		}
		
        this.entityToDto(ent, dto);
        return dto;
	}



	@Override
	public ENT getEntity(DTO dto) {
        ENT ent = null;
		try {
			ent = entClass.newInstance();
		} catch (Exception e) {
			BizlogicException bizEx = new BizlogicException("Cannot init ent", e);
			bizEx.addParamValue(entClass.getName());
			throw bizEx;
		}
		
        this.dtoToEntity(dto, ent);
        return ent;
	}



	/* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.tranlator.Translator#entityToDto(com.clinic.clinic.webapp.cliniccollect.persistence.entity.IdEntity, com.clinic.clinic.common.dto.IdDto)
     */
    @Override
    public void entityToDto(ENT ent, DTO dto) {
        if (ent == null) {
            String errMsg = "entityToDto() got ent null";
            LOGGER.error(errMsg);
            throw new BizlogicException(errMsg);
        } else if (dto == null) {
            String errMsg = "entityToDto() got dto null";
            LOGGER.error(errMsg);
            throw new BizlogicException(errMsg);
        }

        BeanUtils.copyProperties(ent, dto);
        
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.tranlator.Translator#dtoToEntity(com.clinic.clinic.common.dto.IdDto, com.clinic.clinic.webapp.cliniccollect.persistence.entity.IdEntity)
     */
    @Override
    public void dtoToEntity(DTO dto, ENT ent) {
        if (ent == null) {
            String errMsg = "entityToDto() got ent null";
            LOGGER.error(errMsg);
            throw new BizlogicException(errMsg);
        } else if (dto == null) {
            String errMsg = "entityToDto() got dto null";
            LOGGER.error(errMsg);
            throw new BizlogicException(errMsg);
        }


        BeanUtils.copyProperties(dto, ent);
        
        
    }

    /*
     * (non-Javadoc)
     * @see
     * com.clinic.clinic.webapp.cliniccollect.translator.ITranslator#getDtoList
     * (java.util.List)
     */
    @Override
    public List<DTO> getDtoList(List<ENT> entList) {
        List<DTO> resultList = new ArrayList<DTO>();

        if (entList == null) {
            return resultList;
        }

        for (ENT ent : entList) {
            resultList.add(getDto(ent));
        }

        return resultList;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.clinic.clinic.webapp.cliniccollect.translator.ITranslator#getEntityList
     * (java.util.List)
     */
    @Override
    public List<ENT> getEntityList(List<DTO> dtoList) {
        List<ENT> resultList = new ArrayList<ENT>();

        if (dtoList == null) {
            return resultList;
        }

        for (DTO dto : dtoList) {
            resultList.add(getEntity(dto));
        }

        return resultList;
    }
}
