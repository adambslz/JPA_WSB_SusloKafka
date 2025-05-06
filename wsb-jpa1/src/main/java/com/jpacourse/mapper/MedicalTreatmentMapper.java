package com.jpacourse.mapper;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;

public final class MedicalTreatmentMapper {

    public static MedicalTreatmentTO medicalTreatmentTO(MedicalTreatmentEntity entity){
        if(entity == null) {
            return null;
        }

        MedicalTreatmentTO treatmentTO = new MedicalTreatmentTO();
        treatmentTO.setId(entity.getId());
        treatmentTO.setDescription(entity.getDescription());
        treatmentTO.setTreatmentType(entity.getType());
        return treatmentTO;
    }

    public static MedicalTreatmentEntity toMedicalTreatmentEntity(MedicalTreatmentTO treatmentTO){
        if(treatmentTO == null) {
            return null;
        }

        MedicalTreatmentEntity entity = new MedicalTreatmentEntity();
        entity.setId(treatmentTO.getId());
        entity.setDescription(treatmentTO.getDescription());
        entity.setType(treatmentTO.getTreatmentType());
        return entity;
    }
}
