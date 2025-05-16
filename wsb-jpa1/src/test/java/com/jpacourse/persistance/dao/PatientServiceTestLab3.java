package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
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
public class PatientServiceTestLab3 {

    @Autowired
    private PatientDao patientDao;
    @Autowired
    private PatientService patientService;

    //Lab 3 - zadanie 2
    @Test
    void testListByPatientId() {
        //give: pacjent z dwiema wizytami
        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("Test Str");
        patientAddress.setCity("Test City");
        patientAddress.setPostalCode("11-222");

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Smith");
        patient.setPatientNumber("P1234");
        patient.setPeselNumber(910125023310L);
        patient.setTelephoneNumber("222-333-4444");
        patient.setEmail("john.smith@gmail.com");
        patient.setDateOfBirth(LocalDate.of(1991,01,25));
        patient.setAddress(patientAddress);

        VisitEntity visit1 = new VisitEntity();
        visit1.setTime(LocalDateTime.now().minusDays(7));
        visit1.setDescription("Badanie wstępne");
        visit1.setPatient(patient);

        VisitEntity visit2 = new VisitEntity();
        visit2.setTime(LocalDateTime.now());
        visit2.setDescription("Badanie kontrolne");
        visit2.setPatient(patient);

        patient.setVisits(List.of(visit1,visit2));

        patientDao.save(patient);

        //when
        PatientTO patientTO = patientService.findById(patient.getId());

        //then
        assertNotNull(patientTO);
        assertEquals(2,patientTO.getVisits().size());
    }
}