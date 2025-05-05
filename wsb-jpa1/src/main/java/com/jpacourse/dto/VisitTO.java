package com.jpacourse.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class VisitTO implements Serializable {
    private Long id;
    private String description;
    private LocalDateTime visitDate;
    private String doctorName;
    private String doctorSurname;
    private List<MedicalTreatmentTO> medicalTreatments;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public LocalDateTime getVisitDate() {return visitDate;}
    public void setVisitDate(LocalDateTime visitDate) {this.visitDate = visitDate;}
    public String getDoctorName() {return doctorName;}
    public void setDoctorName(String doctorName) {this.doctorName = doctorName;}
    public String getDoctorSurname() {return doctorSurname;}
    public void setDoctorSurname(String doctorSurname) {this.doctorSurname = doctorSurname;}
    public List<MedicalTreatmentTO> getMedicalTreatments() {return medicalTreatments;}
    public void setMedicalTreatments(List<MedicalTreatmentTO> medicalTreatments) {this.medicalTreatments = medicalTreatments;}

}
