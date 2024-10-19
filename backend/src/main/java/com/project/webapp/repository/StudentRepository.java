package com.project.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.webapp.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
