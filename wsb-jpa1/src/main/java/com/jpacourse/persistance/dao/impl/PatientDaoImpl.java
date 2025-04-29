package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager.createQuery("from PatientEntity where lastName =:lastName").setParameter("lastName", lastName).getResultList();
    }

    @Override
    public List<PatientEntity> findByVisitNumber(int vnr) {
        return entityManager.createNativeQuery("SELECT p.* FROM patient p join visits v on p.patient_id = v.patient_id group by p.patient_id having count(*) > :x").setParameter("x", vnr).getResultList();
    }

    @Override
    public List<PatientEntity> findOlderThanAge(int age) {
        return entityManager.createQuery("from PatientEntity where age > :age").setParameter("age", age).getResultList();
    }
}
