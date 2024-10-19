package com.project.webapp.service;

import com.project.webapp.dto.StudentDTO;
import com.project.webapp.dto.TeacherDTO;
import com.project.webapp.model.Student;
import com.project.webapp.model.Teacher;
import com.project.webapp.repository.StudentRepository;
import com.project.webapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentService studentService;
    
    @Autowired
	private StudentRepository studentRepository;

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(this::convertToTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToTeacherDTO(savedTeacher);
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        return convertToTeacherDTO(teacher);
    }

    @Override
    public boolean deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        
        for (Student student : teacher.getStudents()) {
            student.getTeachers().remove(teacher);  
            studentRepository.save(student);  
        }
        
        teacher.getStudents().clear();
        teacherRepository.delete(teacher);
        
        return true;
    }


    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDetails) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        existingTeacher.setFirstName(teacherDetails.getFirstName());
        existingTeacher.setLastName(teacherDetails.getLastName());
        existingTeacher.setBirthdate(teacherDetails.getBirthdate());
        existingTeacher.setDanceStyle(teacherDetails.getDanceStyle());
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return convertToTeacherDTO(updatedTeacher);
    }

    @Override
    public List<Teacher> getTeachersByDanceStyle(String danceStyle) {
        return teacherRepository.findByDanceStyle(danceStyle);
    }

    @Override
    public List<Teacher> getTeachersByIds(List<Long> teacherIds) {
        return teacherRepository.findAllById(teacherIds);
    }

    @Override
    public List<StudentDTO> getStudentsByTeacherId(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacher.getStudents().stream()
                .map(studentService::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO convertToTeacherDTO(Teacher teacher) {
        List<String> studentNames = teacher.getStudents().stream()
                .map(student -> student.getFirstName() + " " + student.getLastName())
                .collect(Collectors.toList());

        return new TeacherDTO(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getDanceStyle(),
                teacher.getBirthdate(),
                studentNames
        );
    }

    @Override
    public Teacher convertToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getId());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setBirthdate(teacherDTO.getBirthdate());
        teacher.setDanceStyle(teacherDTO.getDanceStyle());
        return teacher;
    }
}
