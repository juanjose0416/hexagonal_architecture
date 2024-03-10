package com.students.grades_hexagonal.domain.service;

import com.students.grades_hexagonal.domain.model.Student;

public interface StudentService {

    Student getStudentById(String studentId);

}
