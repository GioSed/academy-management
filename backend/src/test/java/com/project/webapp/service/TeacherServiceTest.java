package com.project.webapp.service;

import com.project.webapp.dto.TeacherDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Ensures changes made during tests are rolled back after each test
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    private TeacherDTO createTestTeacherDTO() {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setFirstName("Alice");
        teacherDTO.setLastName("Johnson");
        teacherDTO.setDanceStyle("Salsa");
        teacherDTO.setBirthdate(java.time.LocalDate.of(1990, 1, 15));
        return teacherDTO;
    }

    @Test
    public void testSaveTeacher() {
       
        TeacherDTO teacherDTO = createTestTeacherDTO();

        TeacherDTO savedTeacher = teacherService.saveTeacher(teacherDTO);

        assertNotNull(savedTeacher.getId(), "Teacher ID should not be null");
        assertEquals("Alice", savedTeacher.getFirstName(), "Teacher first name should match");
        assertEquals("Johnson", savedTeacher.getLastName(), "Teacher last name should match");
        assertEquals("Salsa", savedTeacher.getDanceStyle(), "Dance style should match");
    }

    @Test
    public void testGetTeacherById() {
        TeacherDTO teacherDTO = createTestTeacherDTO();
        TeacherDTO savedTeacher = teacherService.saveTeacher(teacherDTO);

        TeacherDTO foundTeacher = teacherService.getTeacherById(savedTeacher.getId());

        assertNotNull(foundTeacher, "Teacher should be found by ID");
        assertEquals(savedTeacher.getId(), foundTeacher.getId(), "Teacher ID should match");
        assertEquals("Alice", foundTeacher.getFirstName(), "Teacher first name should match");
    }

    @Test
    public void testUpdateTeacher() {
        TeacherDTO teacherDTO = createTestTeacherDTO();
        TeacherDTO savedTeacher = teacherService.saveTeacher(teacherDTO);

        savedTeacher.setFirstName("Bob");
        TeacherDTO updatedTeacher = teacherService.updateTeacher(savedTeacher.getId(), savedTeacher);

        assertEquals("Bob", updatedTeacher.getFirstName(), "Teacher first name should be updated to Bob");
        assertEquals(savedTeacher.getLastName(), updatedTeacher.getLastName(), "Last name should remain unchanged");
    }

    @Test
    public void testDeleteTeacher() {
        TeacherDTO teacherDTO = createTestTeacherDTO();
        TeacherDTO savedTeacher = teacherService.saveTeacher(teacherDTO);

        boolean isDeleted = teacherService.deleteTeacher(savedTeacher.getId());

        assertTrue(isDeleted, "Teacher should be successfully deleted");
        assertThrows(RuntimeException.class, () -> teacherService.getTeacherById(savedTeacher.getId()),
                "Fetching deleted teacher should throw RuntimeException");
    }

    @Test
    public void testGetAllTeachers() {
        TeacherDTO teacherDTO1 = createTestTeacherDTO();
        TeacherDTO teacherDTO2 = new TeacherDTO();
        teacherDTO2.setFirstName("Jane");
        teacherDTO2.setLastName("Doe");
        teacherDTO2.setDanceStyle("Bachata");
        teacherDTO2.setBirthdate(java.time.LocalDate.of(1985, 5, 20));

        teacherService.saveTeacher(teacherDTO1);
        teacherService.saveTeacher(teacherDTO2);

        List<TeacherDTO> teachers = teacherService.getAllTeachers();

        assertTrue(teachers.size() >= 2, "There should be at least 2 teachers in the list");
    }
}
