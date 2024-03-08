package com.students.grades_hexagonal.domain.spi;

import com.students.grades_hexagonal.domain.model.Student;

public interface StudentPersistencePort {

    Student saveStudent(Student student);
}
