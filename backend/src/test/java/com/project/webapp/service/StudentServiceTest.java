package com.project.webapp.service;

import com.project.webapp.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testSaveStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setEmail("johndoe@example.com");
        studentDTO.setDanceStyle("Salsa");
        studentDTO.setPhoneNumber(1234567890L);

        StudentDTO savedStudent = studentService.saveStudent(studentDTO);

        assertNotNull(savedStudent.getId());
        assertEquals("John", savedStudent.getFirstName());
    }

    @Test
    public void testGetAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();

        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    public void testGetStudentById() {
        Long studentId = 1L;

        StudentDTO student = studentService.getStudentById(studentId);

        assertNotNull(student);
        assertEquals(studentId, student.getId());
    }

    @Test
    public void testDeleteStudent() {
        Long studentId = 2L;

        studentService.deleteStudent(studentId);
        assertThrows(RuntimeException.class, () -> studentService.getStudentById(studentId));
    }
}
