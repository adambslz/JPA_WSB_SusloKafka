package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = findOne(patientId); // from AbstractDao
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Patient or Doctor not found.");
        }

        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setTime(time);
        visit.setDescription(description);

        patient.getVisits().add(visit);
        update(patient); // triggers cascade via AbstractDao's update()
    }

    @Override
    public List<PatientEntity> findAllByLastName(String lastName) {
        return entityManager.createQuery(
                "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName",PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount) {
        return entityManager.createQuery("""
            SELECT p FROM PatientEntity p
            WHERE SIZE(p.visits) > :visitCount
        """, PatientEntity.class)
                .setParameter("visitCount", visitCount)
                .getResultList();
    }

    @Override
    public  PatientEntity createPatient(PatientEntity patient) {
        entityManager.persist(patient);
        return patient;
    }

    @Override
    public List<PatientEntity> findPatientsBornBefore(LocalDate date) {

        List<PatientEntity> allPatients = entityManager.createQuery("SELECT p FROM PatientEntity p", PatientEntity.class).getResultList();

        return allPatients.stream()
                .filter(p -> {
                    Long pesel = p.getPeselNumber();
                    if (pesel == null) return false;

                    LocalDate birthDate = extractBirthDateFromPesel(pesel);
                    return birthDate != null && birthDate.isBefore(date);
                })
                .toList();
    }

    private LocalDate extractBirthDateFromPesel(Long peselNumber) {
        if(peselNumber == null) return null;

        String peselStr = String.valueOf(peselNumber);
        if(peselStr.length() < 6) return null;

        int year = Integer.parseInt(peselStr.substring(0, 2));
        int month = Integer.parseInt(peselStr.substring(2, 4));
        int day = Integer.parseInt(peselStr.substring(4, 6));

        //Rozróżnienie stulecia wg miesiąca
        if(month >= 1 && month <=12){
            year += 1900;
        } else if(month >= 21 && month <= 32){
            year += 2000;
            month -= 20;
        } else {
            return null;
        }

        try{
            return LocalDate.of(year, month, day);
        } catch(Exception e){
            return null; //przechwycenie błędu związane ze złą datą
        }
    }
}