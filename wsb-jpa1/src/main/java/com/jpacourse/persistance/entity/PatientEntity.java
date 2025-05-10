package com.jpacourse.persistance.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	//Dodatkowe pole w PatientEntity - numer PESEL
	private Long peselNumber;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@Column(nullable = false)
	private String patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	//Dodatkowe metody do obsługi dodanego pola PESEL
	private Long getPeselNumber() {return peselNumber;}
	public void setPeselNumber(Long peselNumber) {this.peselNumber = peselNumber;}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(List<VisitEntity> visits) {
		this.visits = visits;
	}

	//Relacje jednostronna (jeden-do-jednego) od strony rodzica
	@OneToOne(
			cascade =  CascadeType.ALL, // default: empty
			fetch = FetchType.LAZY, // default: EAGER
			optional = false // default: true
	)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private AddressEntity address;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<VisitEntity> visits;
}
