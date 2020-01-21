package com.clinic.clinic.api.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.MajorEntity;
import com.clinic.clinic.api.persistence.repository.IMajorRepository;

@Repository
public class MajorRepositoryImpl extends AbsRepositoryImpl<MajorEntity, Integer> implements IMajorRepository {

}
