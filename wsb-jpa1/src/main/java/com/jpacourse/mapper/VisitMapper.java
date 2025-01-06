package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public final class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setTime(visitEntity.getTime());
        DoctorEntity doctor = visitEntity.getDoctor();
        visitTO.setDoctorFirstName(doctor.getFirstName());
        visitTO.setDoctorLastName(doctor.getLastName());
        visitTO.setTreatments(
                visitEntity.getTreatments()
                        .stream()
                        .map(MedicalTreatmentEntity::getType)
                        .collect(Collectors.toList())
        );
        return visitTO;
    }
}
