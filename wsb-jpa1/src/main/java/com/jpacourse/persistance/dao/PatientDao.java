package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>{
    public List<PatientEntity> findByLastName(String lastName);
    public List<PatientEntity> findByVisitNumber(int vnr);
    public List<PatientEntity> findOlderThanAge(int age);
}
