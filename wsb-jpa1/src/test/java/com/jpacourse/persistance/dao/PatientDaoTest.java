package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.Specialization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    public void testAddVisitToPatient() {

        //given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setPatientNumber("P12345");
        patient.setTelephoneNumber("111-222-333");
        patient.setDateOfBirth(LocalDate.of(1990,1,1));

        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("Test Str");
        patientAddress.setCity("Test City");
        patientAddress.setPostalCode("5L596X");
        patient.setAddress(patientAddress);

        patient = patientDao.save(patient);

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Alice");
        doctor.setLastName("Cooper");
        doctor.setDoctorNumber("TST001");
        doctor.setSpecialization(Specialization.GP);
        doctor.setEmail("alice@gmail.com");
        doctor.setTelephoneNumber("555-555-5555");

        AddressEntity doctorAddress = new AddressEntity();
        doctorAddress.setAddressLine1("Testowa");
        doctorAddress.setCity("Testowo");
        doctorAddress.setPostalCode("11-222");
        doctor.setAddress(doctorAddress);

        doctor = doctorDao.save(doctor);

        LocalDateTime visitTime = LocalDateTime.now();
        String description = "General medical check-up";

        //when
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), visitTime, description);

        //then
        PatientEntity updatedPatient = patientDao.findOne(patient.getId());
        List<VisitEntity> visits = updatedPatient.getVisits();

        assertNotNull(visits);
        assertEquals(1,visits.size());

        VisitEntity visit = visits.get(0);
        assertEquals(description, visit.getDescription());
        assertEquals(visitTime,visit.getTime());
        assertEquals(doctor.getId(), visit.getDoctor().getId());
        assertEquals(patient.getId(), visit.getPatient().getId());
    }
}
