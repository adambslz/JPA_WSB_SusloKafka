package com.jpacourse.persistance.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpacourse.dto.AddressTO;
import com.jpacourse.persistance.enums.Specialization;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "TELEPHONE_NUMBER", nullable = false)
	private String telephoneNumber;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DOCTOR_NUMBER", nullable = false)
	private String doctorNumber;

	@Column(name = "SPECIALIZATION", nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

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

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	// Getter for Address
	public AddressEntity getAddress() {
		return address;
	}

	// Setter for Address
	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(List<VisitEntity> visits) {
		this.visits = visits;
	}

	//Relacja jednostronna (jeden-do-jednego) od strony rodzica
	@OneToOne(
			cascade =  CascadeType.ALL, // default: empty
			fetch = FetchType.LAZY, // default: EAGER
			optional = false // default: true
	)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private AddressEntity address;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<VisitEntity> visits;


}
