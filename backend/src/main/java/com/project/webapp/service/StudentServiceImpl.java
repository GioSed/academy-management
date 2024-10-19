package com.project.webapp.service;

import com.project.webapp.dto.StudentDTO;
import com.project.webapp.model.Student;
import com.project.webapp.model.Teacher;
import com.project.webapp.repository.StudentRepository;
import com.project.webapp.repository.TeacherRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays; // <-- Import Arrays utility
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return convertToDTO(student);
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);

        List<String> danceStyles = Arrays.asList(studentDTO.getDanceStyle().split(",\\s*"));  

        List<Teacher> assignedTeachers = new ArrayList<>();

        for (String danceStyle : danceStyles) {
            List<Teacher> teachersForStyle = teacherRepository.findByDanceStyle(danceStyle);
            assignedTeachers.addAll(teachersForStyle);
        }

        student.setTeachers(assignedTeachers);

        for (Teacher teacher : assignedTeachers) {
            if (!teacher.getStudents().contains(student)) {
                teacher.getStudents().add(student);
            }
        }

        Student savedStudent = studentRepository.save(student);

        return convertToDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setPhoneNumber(studentDetails.getPhoneNumber());
        existingStudent.setDanceStyle(studentDetails.getDanceStyle());

        List<String> danceStyles = Arrays.asList(studentDetails.getDanceStyle().split(",\\s*"));
        List<Teacher> assignedTeachers = new ArrayList<>();

        for (String danceStyle : danceStyles) {
            List<Teacher> teachersForStyle = teacherRepository.findByDanceStyle(danceStyle);
            assignedTeachers.addAll(teachersForStyle);
        }

        existingStudent.setTeachers(assignedTeachers);

        for (Teacher teacher : assignedTeachers) {
            if (!teacher.getStudents().contains(existingStudent)) {
                teacher.getStudents().add(existingStudent);
            }
        }

        Student updatedStudent = studentRepository.save(existingStudent);
        return convertToDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO convertToDTO(Student student) {
        List<String> teacherNames = student.getTeachers().stream()
                .map(teacher -> teacher.getFirstName() + " " + teacher.getLastName())
                .collect(Collectors.toList());

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDanceStyle(),
                teacherNames,
                student.getPhoneNumber()
        );
    }

    public Student convertToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setDanceStyle(studentDTO.getDanceStyle());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        return student;
    }
}

