package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;

public interface PatientService {

    PatientTO findById(final Long id);

    void deletePatient(final Long id);

    List<VisitEntity> getVisitsByPatientId(Long id);
}
