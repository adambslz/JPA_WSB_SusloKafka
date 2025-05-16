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

    //Lab 3 - zadanie 1
    @Test
    public void testFindByLastName() {

        //given
        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("John");
        patient1.setLastName("Smith");
        patient1.setPatientNumber("TST001");
        patient1.setTelephoneNumber("111-222-333");
        patient1.setDateOfBirth(LocalDate.of(1990,1,1));

        AddressEntity address1 = new AddressEntity();
        address1.setAddressLine1("Test Str");
        address1.setCity("Test City");
        address1.setPostalCode("5L596X");
        patient1.setAddress(address1);

        patientDao.save(patient1);

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Alice");
        patient2.setLastName("Cooper");
        patient2.setPatientNumber("TST002");
        patient2.setTelephoneNumber("555-555-5555");
        patient2.setDateOfBirth(LocalDate.of(1992,3,15));

        AddressEntity address2 = new AddressEntity();
        address2.setAddressLine1("Testowa");
        address2.setCity("Testowo");
        address2.setPostalCode("11-222");
        patient2.setAddress(address2);

        patientDao.save(patient2);

        PatientEntity patient3 = new PatientEntity();
        patient3.setFirstName("Stefan");
        patient3.setLastName("Batory");
        patient3.setPatientNumber("TST003");
        patient3.setTelephoneNumber("777-777-6666");
        patient3.setDateOfBirth(LocalDate.of(1993,6,23));

        AddressEntity address3 = new AddressEntity();
        address3.setAddressLine1("Bukowa");
        address3.setCity("Kłodzko");
        address3.setPostalCode("57-300");
        patient3.setAddress(address3);

        patientDao.save(patient3);

        //when
        List<PatientEntity> patientsLastName = patientDao.findAllByLastName("Smith");

        //then
        assertNotNull(patientsLastName);
        assertEquals(1,patientsLastName.size());
        assertTrue(patientsLastName.stream().allMatch(s -> "Smith".equals(s.getLastName())));
    }

    //Lab 3 - zadanie 3
    @Test
    void testFindPatientsWithMoreThanXVisits() {
        //given
        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("John");
        patient1.setLastName("Smith");
        patient1.setPatientNumber("TST001");
        patient1.setTelephoneNumber("111-222-333");
        patient1.setDateOfBirth(LocalDate.of(1990,1,1));

        AddressEntity address1 = new AddressEntity();
        address1.setAddressLine1("Test Str");
        address1.setCity("Test City");
        address1.setPostalCode("5L596X");
        patient1.setAddress(address1);

        //Pacjent 1 ma dwie wizyty
        VisitEntity visit1 = new VisitEntity();
        visit1.setDescription("Pierwsza wizyta");
        visit1.setTime(LocalDateTime.now());
        visit1.setPatient(patient1);

        VisitEntity visit2 = new VisitEntity();
        visit2.setDescription("Druga wizyta");
        visit2.setTime(LocalDateTime.now().plusDays(2));
        visit2.setPatient(patient1);

        patient1.getVisits().add(visit1);
        patient1.getVisits().add(visit2);

        patientDao.createPatient(patient1);

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Jan");
        patient2.setLastName("Kowalski");
        patient2.setPatientNumber("TST002");
        patient2.setTelephoneNumber("777-777-6666");
        patient2.setDateOfBirth(LocalDate.of(1992,3,15));

        AddressEntity address2 = new AddressEntity();
        address2.setAddressLine1("Al. Jerozolimskie");
        address2.setCity("Warszawa");
        address2.setPostalCode("00-222");
        patient2.setAddress(address2);

        //Pacjent 2 ma tylko 1 wizytę
        VisitEntity visit3 = new VisitEntity();
        visit3.setDescription("Jedna wizyta");
        visit3.setTime(LocalDateTime.now());
        visit3.setPatient(patient2);

        patient2.getVisits().add(visit3);

        patientDao.createPatient(patient2);

        //when
        List<PatientEntity> result = patientDao.findPatientsWithMoreThanXVisits(1);

        //then
        assertEquals(1, result.size());
        assertEquals("Smith", result.get(0).getLastName());
    }

    //Lab 3 - zad 4.
    @Test
    public void testFindPatientsBornBeforeGivenDateFromPesel(){

        //given
        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("Adam");
        patient1.setLastName("Nowak");
        patient1.setPatientNumber("TST001");
        patient1.setTelephoneNumber("777-777-6666");
        patient1.setDateOfBirth(LocalDate.of(1979,11,15));
        patient1.setPeselNumber(79111505238L); //PESEL dla daty urodzenia

        AddressEntity address1 = new AddressEntity();
        address1.setAddressLine1("Zamenhoffa");
        address1.setCity("Kudowa-Zdrój");
        address1.setPostalCode("57-520");
        patient1.setAddress(address1);

        patientDao.createPatient(patient1);

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Julia");
        patient2.setLastName("Kowalska");
        patient2.setPatientNumber("TST002");
        patient2.setTelephoneNumber("222-222-4444");
        patient2.setDateOfBirth(LocalDate.of(1993,7,8));
        patient2.setPeselNumber(93070813228L);

        AddressEntity address2 = new AddressEntity();
        address2.setAddressLine1("Grunwaldzka");
        address2.setCity("Wrocław");
        address2.setPostalCode("58-210");
        patient2.setAddress(address2);

        patientDao.createPatient(patient2);

        //when: znajdź wszystkich pacjentów urodzonych przed 1990-01-01
        List<PatientEntity> result = patientDao.findPatientsBornBefore(LocalDate.of(1980,1,1));
        System.out.println(result);
        //then
        assertEquals(1, result.size());
        assertEquals("Adam", result.get(0).getFirstName());
    }
}
