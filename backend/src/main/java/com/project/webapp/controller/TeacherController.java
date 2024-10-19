package com.project.webapp.controller;

import com.project.webapp.dto.StudentDTO;
import com.project.webapp.dto.TeacherDTO;
import com.project.webapp.model.Teacher;
import com.project.webapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO savedTeacher = teacherService.saveTeacher(teacherDTO);
        return ResponseEntity.ok(savedTeacher);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacherDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDetails) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-style/{danceStyle}")
    public ResponseEntity<List<TeacherDTO>> getTeachersByDanceStyle(@PathVariable String danceStyle) {
        List<Teacher> teachers = teacherService.getTeachersByDanceStyle(danceStyle);
        List<TeacherDTO> teacherDTOs = teachers.stream().map(teacherService::convertToTeacherDTO).collect(Collectors.toList());
        return ResponseEntity.ok(teacherDTOs);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByTeacherId(@PathVariable Long id) {
        List<StudentDTO> students = teacherService.getStudentsByTeacherId(id);
        return ResponseEntity.ok(students);
    }
}
