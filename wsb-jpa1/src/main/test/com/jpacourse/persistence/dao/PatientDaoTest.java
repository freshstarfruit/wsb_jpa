package com.jpacourse.persistence.dao;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest
{
    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // given

        // when
        PatientEntity patientEntity = patientDao.findOne(1L);

        // then
        assertThat(patientEntity).isNotNull();
        assertThat(patientEntity.getFirstName()).isEqualTo("John");
    }

    @Transactional
    @Test
    public void testShouldAddVisitToPatient() {
        // given
        Long patientId = 2L;
        Long doctorId = 2L;
        LocalDateTime time = LocalDateTime.parse("2025-01-06T14:00:00");
        String description = "Test visit";

        // when
        PatientEntity patientEntity = patientDao.findOne(patientId);
        int visitsSize = patientEntity.getVisits().size();

        patientDao.addVisit(patientId, doctorId, time, description);
        patientEntity = patientDao.findOne(patientId);

        // then
        assertThat(patientEntity).isNotNull();
        List<VisitEntity> visits = patientEntity.getVisits();
        assertThat(visits.size()).isEqualTo(visitsSize + 1);
        assertThat(visits.stream()
                    .filter(visit -> Objects.equals(visit.getDoctor().getId(), doctorId)
                            && Objects.equals(visit.getPatient().getId(), patientId)
                            && Objects.equals(visit.getDescription(), "Test visit"))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Visit with requested properties not found")))
                .isNotNull();
    }
}
