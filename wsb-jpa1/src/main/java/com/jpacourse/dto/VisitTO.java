package com.jpacourse.dto;

import com.jpacourse.persistence.enums.TreatmentType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class VisitTO implements Serializable {

    private Long id;

    private LocalDateTime time;

    private List<TreatmentType> treatments;

    private String doctorFirstName;

    private String doctorLastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<TreatmentType> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<TreatmentType> treatments) {
        this.treatments = treatments;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }
}
