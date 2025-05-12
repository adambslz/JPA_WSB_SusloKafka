package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.Specialization;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PatientServiceTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testDeletePatientCascadeDeleteVisitsLeaveDoctors(){
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setPatientNumber("P12345");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setTelephoneNumber("123456789");
        patient.setPeselNumber(90010112345L);
        patient.setEmail("john.doe@example.com");

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("John");
        doctor.setLastName("Smith");
        doctor.setDoctorNumber("TST001");
        doctor.setEmail("john.smith@example.com");
        doctor.setTelephoneNumber("111222333");
        doctor.setSpecialization(Specialization.GP);

        AddressEntity doctorAddress = new AddressEntity();
        doctorAddress.setAddressLine1("Testowa");
        doctorAddress.setCity("Testowo");
        doctorAddress.setPostalCode("11-222");
        doctor.setAddress(doctorAddress);

        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("Test Str");
        patientAddress.setCity("Test City");
        patientAddress.setPostalCode("5L596X");
        patient.setAddress(patientAddress);

        VisitEntity visit1 = new VisitEntity();
        visit1.setTime(LocalDateTime.now().minusDays(1));
        visit1.setDoctor(doctor);
        visit1.setPatient(patient);

        patient.setVisits(List.of(visit1));

        entityManager.persist(doctor);
        patientDao.save(patient);

        entityManager.flush();
        entityManager.clear();

        Long patientId = patient.getId();
        Long doctorId = doctor.getId();

        // when
        patientDao.delete(patientId);
        entityManager.flush();
        entityManager.clear();

        // then
        Assertions.assertNull(entityManager.find(PatientEntity.class, patientId));
        Assertions.assertTrue(entityManager.createQuery("SELECT v FROM VisitEntity v WHERE v.patient.id = :id", VisitEntity.class)
                .setParameter("id", patientId).getResultList().isEmpty());
        Assertions.assertNotNull(entityManager.find(DoctorEntity.class, doctorId));
    }

    @Test
    void testPatientDownloadData(){
        //given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Smith");
        patient.setPatientNumber("P12345");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setTelephoneNumber("123456789");
        patient.setPeselNumber(90010112345L);
        patient.setEmail("john.smith@example.com");

        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("Test Str");
        patientAddress.setCity("Test City");
        patientAddress.setPostalCode("5L596X");
        patient.setAddress(patientAddress);

        patientDao.save(patient);
        Long id = patient.getId();

        // when
        PatientEntity fetchedEntity = patientDao.findOne(id);
        PatientTO patientTO = PatientMapper.toPatientTO(fetchedEntity);

        // then
        System.out.println("Saved PESEL: " + patient.getPeselNumber());

        entityManager.flush();
        entityManager.clear();

        PatientEntity fromDb = patientDao.findOne(patient.getId());

        System.out.println("Fetched PESEL: " + fromDb.getPeselNumber());

        Assertions.assertEquals(90010112345L, fromDb.getPeselNumber());
        Assertions.assertEquals("John", patientTO.getFirstName());
        Assertions.assertEquals("Smith", patientTO.getLastName());
    }
}
