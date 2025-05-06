package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class PatientMapper {

    public static PatientTO toPatientTO(PatientEntity patientEntity){
        if(patientEntity == null){
            return null;
        }
        PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setBirthDate(patientEntity.getDateOfBirth());

        if(patientEntity.getVisits() != null){
            List<VisitTO> visits = patientEntity.getVisits()
                    .stream()
                    .map(visitEntity -> VisitMapper.toVisitTO(visitEntity))
                    .collect(Collectors.toList());
            patientTO.setVisits(visits);
        }
        return patientTO;
    }

    public static PatientEntity toPatientEntity(PatientTO patientTO){
        if(patientTO == null){
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getBirthDate());

        if(patientTO.getVisits() != null){
            List<VisitEntity> visitEntityList = patientTO.getVisits()
                    .stream()
                    .map(visitTO -> VisitMapper.toVisitEntity(visitTO))
                    .collect(Collectors.toList());
            patientEntity.setVisits(visitEntityList);
        }

        return patientEntity;
    }
}
