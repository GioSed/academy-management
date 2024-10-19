package com.project.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.webapp.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	boolean existsByFirstNameAndLastName(String firstName, String lastName);
	
	List<Teacher> findByDanceStyle(String danceStyle);
}
