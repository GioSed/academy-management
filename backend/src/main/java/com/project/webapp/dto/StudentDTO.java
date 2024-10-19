package com.project.webapp.dto;

import java.util.List;

public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String danceStyle; // Dance style is a comma-separated string
    private List<String> teacherNames;
    private long phoneNumber;

    public StudentDTO(Long id, String firstName, String lastName, String email, String danceStyle, List<String> teacherNames, long phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.danceStyle = danceStyle;
        this.teacherNames = teacherNames;
        this.phoneNumber = phoneNumber;
    }
    
    public StudentDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDanceStyle() {
        return danceStyle;
    }

    public void setDanceStyle(String danceStyle) {
        this.danceStyle = danceStyle;
    }

    public List<String> getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(List<String> teacherNames) {
        this.teacherNames = teacherNames;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
