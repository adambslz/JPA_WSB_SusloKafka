package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface PatientDao extends Dao<PatientEntity, Long>{
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription);
}