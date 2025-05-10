package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDateTime;
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
            List<VisitTO> completedVisits = patientEntity.getVisits()
                    .stream()
                    //visit.getVisitDate().isBefore(LocalDateTime.now()) sprawdza, czy data wizyty jest w przeszłości, dzięki temu pobieramy tylko wizyty, które odbyły się w przeszłości
                    .filter(visit -> visit.getTime().isBefore(LocalDateTime.now()))
                    .map(VisitMapper::toVisitTO)
                    .collect(Collectors.toList());
            patientTO.setVisits(completedVisits);
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
            List<VisitEntity> completedVisitEntityList = patientTO.getVisits()
                    .stream()
                    .filter(visit -> visit.getVisitDate().isBefore(LocalDateTime.now()))
                    .map(VisitMapper::toVisitEntity)
                    .collect(Collectors.toList());
            patientEntity.setVisits(completedVisitEntityList);
        }

        return patientEntity;
    }
}
