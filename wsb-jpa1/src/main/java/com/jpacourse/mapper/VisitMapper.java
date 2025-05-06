package com.jpacourse.mapper;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class VisitMapper {

    public static VisitTO toVisitTO(VisitEntity visitEntity) {

        if(visitEntity == null) {
            return null;
        };

        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setVisitDate(visitEntity.getTime());

        if(visitEntity.getDoctor() != null) {
            visitTO.setDoctorName(visitEntity.getDoctor().getFirstName());
            visitTO.setDoctorSurname(visitEntity.getDoctor().getLastName());
        }

        if(visitEntity.getMedicalTreatments() != null) {
            List<MedicalTreatmentTO> treatmentTOList = visitEntity.getMedicalTreatments()
                    .stream()
                    .map(MedicalTreatmentMapper::medicalTreatmentTO)
                    .collect(Collectors.toList());
            visitTO.setMedicalTreatments(treatmentTOList);
        }
        return visitTO;
    }

    public static VisitEntity toVisitEntity(VisitTO visitTO) {
        if(visitTO == null) {
            return null;
        }

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getVisitDate());

        if(visitEntity.getDoctor().getFirstName() != null || visitEntity.getDoctor().getLastName() != null) {
            DoctorEntity doctor = new DoctorEntity();
            doctor.setFirstName(visitTO.getDoctorName());
            doctor.setLastName(visitTO.getDoctorSurname());
            visitEntity.setDoctor(doctor);
        }

        if(visitTO.getMedicalTreatments() != null) {
            List<MedicalTreatmentEntity> treatmentList = visitTO.getMedicalTreatments()
                    .stream()
                    .map(MedicalTreatmentMapper::toMedicalTreatmentEntity)
                    .collect(Collectors.toList());
            visitEntity.setMedicalTreatments(treatmentList);
        }

        return visitEntity;
    }
}
