import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './globalStyles.css';

function StudentRegistration() {
    const [student, setStudent] = useState({
        firstName: '',
        lastName: '',
        phoneNumber: '',
        email: '',
        danceStyle: ''
    });

    const [teachers, setTeachers] = useState([]);
    const [selectedTeacherIds, setSelectedTeacherIds] = useState([]);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setStudent({
            ...student,
            [name]: value
        });
    };

    useEffect(() => {
        if (student.danceStyle) {
            const danceStyles = student.danceStyle.split(',').map(style => style.trim());
            axios.get('http://localhost:8080/api/teachers/list')
                .then(response => {
                    const availableTeachers = response.data.filter(teacher =>
                        danceStyles.includes(teacher.danceStyle)
                    );
                    setTeachers(availableTeachers);
                    setSelectedTeacherIds(availableTeachers.map(teacher => teacher.id)); 
                })
                .catch(error => {
                    console.error('Error fetching teachers:', error);
                });
        }
    }, [student.danceStyle]);

    const handleSubmit = (e) => {
        e.preventDefault();

        axios.post('http://localhost:8080/api/students/register', student, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                console.log('Student registered:', response.data);
                alert('Student registered successfully!');
                navigate(`/student/${response.data.id}`);
            })
            .catch(error => {
                console.error('Error registering student:', error);
                alert('Failed to register student.');
            });
    };

    return (
        <div className="container">
            <h2 className="title">Student Registration</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>First Name:</label>
                    <input
                        type="text"
                        name="firstName"
                        value={student.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input
                        type="text"
                        name="lastName"
                        value={student.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label>Phone Number:</label>
                    <input
                        type="text"
                        name="phoneNumber"
                        value={student.phoneNumber}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        name="email"
                        value={student.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label>Dance Style(s):</label>
                    <input
                        type="text"
                        name="danceStyle"
                        value={student.danceStyle}
                        onChange={handleChange}
                        placeholder="e.g., Bachata, Tango"
                        required
                    />
                </div>

                {teachers.length > 0 && (
                    <div>
                        <h3>Assigned Teacher(s) for {student.danceStyle}:</h3>
                        <ul>
                            {teachers.map((teacher) => (
                                <li key={teacher.id}>
                                    {teacher.firstName} {teacher.lastName} ({teacher.danceStyle})
                                </li>
                            ))}
                        </ul>
                    </div>
                )}

                <button type="submit" className="btn">Register Student</button>
            </form>
        </div>
    );
}

export default StudentRegistration;
