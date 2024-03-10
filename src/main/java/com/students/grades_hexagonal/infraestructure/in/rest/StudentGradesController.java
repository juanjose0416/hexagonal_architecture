package com.students.grades_hexagonal.infraestructure.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.GradeStudentResponse;
import com.students.grades_hexagonal.app.usecase.GradeHandlerUseCase;

@RestController
@RequestMapping("/grades")
public class StudentGradesController {

    private final GradeHandlerUseCase gradeHandlerUseCase;

    public StudentGradesController(GradeHandlerUseCase gradeHandlerUseCase) {
        this.gradeHandlerUseCase = gradeHandlerUseCase;
    }

    @PostMapping(path = "/{studentId}/{subjectId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> upgrade(@PathVariable("studentId") String studentId,
                                        @PathVariable("subjectId") Long subjectId,
                                        @RequestBody CreateGradeRequest request) {
        gradeHandlerUseCase.create(request, studentId, subjectId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeStudentResponse> getGrades(@PathVariable("studentId") String studentId)
            throws JsonProcessingException {
        GradeStudentResponse gradeStudentResponse = gradeHandlerUseCase.getAllGrades(studentId);
        return ResponseEntity.ok(gradeStudentResponse);
    }

    @PutMapping(path = "/{studentId}/{subjectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeStudentResponse> updateGrade(@PathVariable("studentId") String studentId,
                                                            @PathVariable("subjectId") Long subjectId,
                                                            @RequestBody CreateGradeRequest request) {
        gradeHandlerUseCase.updateGrade(studentId, subjectId, request);
        return ResponseEntity.ok().build();
    }

}
