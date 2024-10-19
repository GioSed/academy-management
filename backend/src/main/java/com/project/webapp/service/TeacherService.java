package com.project.webapp.service;

import com.project.webapp.dto.StudentDTO;
import com.project.webapp.dto.TeacherDTO;
import com.project.webapp.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> getAllTeachers();

    TeacherDTO saveTeacher(TeacherDTO teacherDTO);

    TeacherDTO getTeacherById(Long id);

    boolean deleteTeacher(Long id);

    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDetails);

    List<Teacher> getTeachersByDanceStyle(String danceStyle);

    List<Teacher> getTeachersByIds(List<Long> teacherIds);

    List<StudentDTO> getStudentsByTeacherId(Long teacherId);

    TeacherDTO convertToTeacherDTO(Teacher teacher);

    Teacher convertToEntity(TeacherDTO teacherDTO);
}
