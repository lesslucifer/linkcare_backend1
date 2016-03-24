/**==============================================================================
 * Clinic JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 Clinic JSC.
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
 * Project name  : cliniccollect<br>
 * File name     : ITranslator.java<br>
 * <p>
 * Changes History <br>
 *      Date                Person              Reason<br>
 *      Sep 15, 2015                Vuong Do                Initial<br>
 * </p>
 *
 *=============================================================================*/
package com.clinic.clinic.api.translator;

import java.util.List;

import com.clinic.clinic.api.persistence.entity.IdEntity;
import com.clinic.clinic.common.dto.IdDto;

/**
 * <p>
 * Translator to translate Dto from/to Entity.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
public interface ITranslator<DTO extends IdDto, ENT extends IdEntity> {
    
    /**
     * 
     * <p>Translate Entity to Dto. Input and output parameters should be not null.</p>
     *
     * @param ent
     * @param dto
     *
     * @author Vuong Do
     */
    void entityToDto(ENT ent, DTO dto);
    
    /**
     * 
     * <p>Translate Dto to Entity. Input and output parameters should be not null.</p>
     *
     * @param dto
     * @param ent
     *
     * @author Vuong Do
     */
    void dtoToEntity(DTO dto, ENT ent);
    
    /**
     * 
     * <p>Get new Dto that is translated from given Entity. Input parameter should be not null.</p>
     *
     * @param ent
     *
     * @author Vuong Do
     */
    DTO getDto(ENT ent);
    
    /**
     * 
     * <p>Get new Entity that is translated from given Dto. Input parameter should be not null.</p>
     *
     * @param dto
     *
     * @author Vuong Do
     */
    ENT getEntity(DTO dto);
    
    /**
     * 
     * <p>Get new list of Dto that is translated from given list of Entity.</p>
     *
     * @param entList Input List. If this list is null, method return empty output list.
     *
     * @author Vuong Do
     */
    List<DTO> getDtoList(List<ENT> entList);
    
    /**
     * 
     * <p>Get new list of Entity that is translated from given list of Dto.</p>
     *
     * @param dtoList  Input List. If this list is null, method return empty output list.
     *
     * @author Vuong Do
     */
    List<ENT> getEntityList(List<DTO> dtoList);
}