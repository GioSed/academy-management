package com.project.webapp.dto;

import java.time.LocalDate;
import java.util.List;

public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String danceStyle;
    private LocalDate birthdate;
    private List<String> studentNames;

    public TeacherDTO(Long id, String firstName, String lastName, String danceStyle, LocalDate birthdate, List<String> studentNames) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.danceStyle = danceStyle;
        this.birthdate = birthdate;
        this.studentNames = studentNames;
    }

    public TeacherDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDanceStyle() {
        return danceStyle;
    }

    public void setDanceStyle(String danceStyle) {
        this.danceStyle = danceStyle;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }
}
