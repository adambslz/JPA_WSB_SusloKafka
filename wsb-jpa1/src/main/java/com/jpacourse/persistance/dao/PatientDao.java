package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {

    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription);

    List<PatientEntity> findAllByLastName(String lastName);

    List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount);

    PatientEntity createPatient(PatientEntity patient);

    List<PatientEntity> findPatientsBornBefore(LocalDate date);
}