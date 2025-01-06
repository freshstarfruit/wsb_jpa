package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @PersistenceContext
    protected EntityManager entityManager;

    DoctorDao doctorDao;

    @Autowired
    public PatientDaoImpl(DoctorDao pDoctorDao)
    {
        doctorDao = pDoctorDao;
    }

    @Override
    @Transactional
    public void addVisit(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = this.findOne(patientId);
        if (patient == null) {
            throw new EntityNotFoundException(patientId);
        }

        DoctorEntity doctor = doctorDao.findOne(doctorId);
        if (doctor == null) {
            throw new EntityNotFoundException(doctorId);
        }

        VisitEntity visit = new VisitEntity();
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.setTime(time);
        visit.setDescription(description);
        patient.getVisits().add(visit);
    }
}
