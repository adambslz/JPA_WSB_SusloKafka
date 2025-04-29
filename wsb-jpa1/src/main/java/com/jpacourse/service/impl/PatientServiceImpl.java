package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao pDao) {
        patientDao = pDao;
    }

    @Override
    public PatientTO findById(Long id){
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deleteById(Long id){
        patientDao.delete(id);
    }

    @Override
    public List<VisitTO> findVisitsByPatient(Long id){
        List<VisitTO> visitTOS = new ArrayList<VisitTO>();
        for(VisitEntity p : patientDao.findOne(id).getVisits()){
            visitTOS.add(VisitMapper.mapToTO(p));
        }
        return visitTOS;
    }

}
