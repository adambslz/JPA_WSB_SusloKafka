package com.jpacourse.service.impl;


import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao) {patientDao = pPatientDao;}

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.toPatientTO(entity);
    }

    @Override
    public void deletePatient(final Long id) {
        PatientEntity patientEntity = patientDao.findOne(id);
        if(patientEntity != null) {
            patientDao.delete(patientEntity); // Usunięcie pacjenta powinno automatycznie usunąć powiązane wizyty (kaskada)
        }
    }

    @Override
    public List<VisitEntity> getVisitsByPatientId(Long id){
        PatientEntity patientEntity = patientDao.findOne(id);
        return (patientEntity != null) ? patientEntity.getVisits() : List.of();
    }
}
