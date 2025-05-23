package com.jpacourse.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class PatientTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;

    //Dodatkowe pole PESEL
    private Long peselNumber;

    private String telephoneNumber;
    private String email;
    private String patientNumber;
    private LocalDate birthDate;
    private List<VisitTO> visits;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}

    //Dodatkowe metody do obsługi PESEL-a
    public Long getPeselNumber() {return peselNumber;}
    public void setPeselNumber(Long peselNumber) {this.peselNumber = peselNumber;}

    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getTelephoneNumber() {return telephoneNumber;}
    public void setTelephoneNumber(String telephoneNumber) {this.telephoneNumber = telephoneNumber;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPatientNumber() {return patientNumber;}
    public void setPatientNumber(String patientNumber) {this.patientNumber = patientNumber;}
    public LocalDate getBirthDate() {return birthDate;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}
    public List<VisitTO> getVisits() {return visits;}
    public void setVisits(List<VisitTO> visits) {this.visits = visits;}

}
