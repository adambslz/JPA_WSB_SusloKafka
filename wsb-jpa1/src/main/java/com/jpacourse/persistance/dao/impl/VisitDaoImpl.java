package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {

    @Override
    public List<VisitEntity> findAllByPatientId(Long patientId) {
        TypedQuery<VisitEntity> query = entityManager.createQuery(
                "select v from VisitEntity v where v.patientId = :patientId", VisitEntity.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }
}
