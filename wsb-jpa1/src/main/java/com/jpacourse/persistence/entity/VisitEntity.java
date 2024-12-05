package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.TreatmentType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	// VisitEntity jest rodzicem MedicalTreatmentEntity, relacja dwustronna
	@OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicalTreatmentEntity> treatments = new ArrayList<>();

	// VisitEntity jest dzieckiem DoctorEntity, relacja dwustronna
	@ManyToOne
	private DoctorEntity doctor;

	// VisitEntity jest dzieckiem PatientEntity, relacja dwustronna
	@ManyToOne
	private PatientEntity patient;

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

}
