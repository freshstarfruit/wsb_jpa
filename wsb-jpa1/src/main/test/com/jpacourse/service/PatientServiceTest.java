package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest
{
    @Autowired
    private PatientService patientService;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // given

        // when
        PatientTO patientTO = patientService.findById(1L);

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getFirstName()).isEqualTo("John");
    }

    @Transactional
    @Test
    public void testPatientDeleteShouldProperlyCascade() {
        // given

        // when
        PatientTO patientTO = patientService.findById(2L);
        patientService.deleteById(2L);

        // then
        assertThat(patientService.findById(2L)).isNull();
        assertThat(entityManager.find(VisitEntity.class, 2L)).isNull();
        assertThat(entityManager.find(VisitEntity.class, 3L)).isNull();
        assertThat(entityManager.find(MedicalTreatmentEntity.class, 2L)).isNull();
        assertThat(entityManager.find(MedicalTreatmentEntity.class, 3L)).isNull();
        assertThat(entityManager.find(MedicalTreatmentEntity.class, 4L)).isNull();
        assertThat(entityManager.find(DoctorEntity.class, 1L)).isNotNull();
        assertThat(entityManager.find(DoctorEntity.class, 2L)).isNotNull();
    }

    @Transactional
    @Test
    public void testPatientTOShouldHaveProperStructure() {
        // given

        // when
        PatientTO patientTO = patientService.findById(2L);
        List<VisitTO> visits = patientTO.getVisits();

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getIsInsured()).isEqualTo(true);

        assertThat(visits).hasOnlyElementsOfType(VisitTO.class);
        assertThat(visits.stream().map(VisitTO::getId)).containsExactlyInAnyOrder(2L, 3L);
        assertThat(visits.get(0).getTreatments()).hasOnlyElementsOfType(TreatmentType.class);

        VisitTO visitSelected = visits.stream()
                .filter(visit -> visit.getId() == 2L)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Visit with requested ID not found"));
        assertThat(visitSelected.getTime()).isEqualTo("2024-12-04T14:00:00");
        assertThat(visitSelected.getDoctorFirstName()).isEqualTo("Alice");
        assertThat(visitSelected.getDoctorLastName()).isEqualTo("Brown");
        assertThat(visitSelected.getTreatments()).containsExactlyInAnyOrder(TreatmentType.EKG, TreatmentType.RTG);
    }
}
