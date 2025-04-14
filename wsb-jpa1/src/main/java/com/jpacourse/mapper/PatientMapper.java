package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.PatientEntity;
import java.util.stream.Collectors;

public final class PatientMapper {
    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if(patientEntity == null) {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        if(patientEntity.getVisits()!=null{
            patientTO.setVisits(patientEntity.getVisits().stream()
                    .map(VisitMapper::mapToTO)
                    .collect(Collectors.toList()));
        }
        return patientTO;
    }
    public static PatientEntity mapToEntity(final PatientTO patientTO) {
        if(patientTO == null) {
            return null;
        }
        final PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        if (patientTO.getVisits() != null) {
            patientEntity.setVisits(patientTO.getVisits().stream()
                    .map(VisitMapper::mapToEntity)
                    .collect(Collectors.toList()));
        }
        return patientEntity;
    }
}
