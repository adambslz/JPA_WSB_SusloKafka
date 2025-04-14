package com.jpacourse.persistance.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public List<MedicalTreatmentEntity> getMedicalTreatments() {
		return treatment;
	}

	public void setMedicalTreatments(List<MedicalTreatmentEntity> medicalTreatments) {
		this.treatment = medicalTreatments;
	}

	//Relacja jednostronna (wiele-do-jednego) od strony dziecka
	@ManyToOne(fetch = FetchType.LAZY)// default: EAGER
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private DoctorEntity doctor;

	//Relacja jednostronna (wiele-do-jednego) od strony dziecka
	@ManyToOne(fetch = FetchType.LAZY)// default: EAGER
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private PatientEntity patient;

	//Relacja dwustronna (jeden-do-wielu) od strony dziecka
	@OneToMany(mappedBy = "visit")
	private List<MedicalTreatmentEntity> treatment;
}
