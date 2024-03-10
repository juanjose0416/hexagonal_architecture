package com.students.grades_hexagonal.infraestructure.out.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.students.grades_hexagonal.infraestructure.out.jpa.entity.GradesEntity;

@Repository
public interface GradesRepository extends JpaRepository<GradesEntity, Long> {

    Optional<List<GradesEntity>> findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    Optional<GradesEntity> findByStudentIdAndSubjectIdAndGradingPeriod(Long studentId,
                                                                       Long subjectId,
                                                                       String gradingPeriod);

    List<GradesEntity> findBySubjectId(Long studentId);

}
