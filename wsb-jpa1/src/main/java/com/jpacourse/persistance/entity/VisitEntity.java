package com.jpacourse.persistance.entity;

import java.time.LocalDateTime;
import java.util.Collection;

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

	@ManyToOne(fetch = FetchType.LAZY)// default: EAGER
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private DoctorEntity doctor;

	@ManyToOne(fetch = FetchType.LAZY)// default: EAGER
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private PatientEntity patient;

	@OneToMany(mappedBy = "visit")
	private Collection<MedicalTreatmentEntity> treatment;
}
