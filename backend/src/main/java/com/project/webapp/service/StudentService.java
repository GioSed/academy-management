package com.project.webapp.service;

import com.project.webapp.dto.StudentDTO;
import com.project.webapp.model.Student;
import java.util.List;

public interface StudentService {

    StudentDTO getStudentById(Long id);
    
    StudentDTO saveStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(Long id, StudentDTO studentDetails);

    void deleteStudent(Long id);

    List<StudentDTO> getAllStudents();
    
    StudentDTO convertToDTO(Student student);

    Student convertToEntity(StudentDTO studentDTO); 

}
