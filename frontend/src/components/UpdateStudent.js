import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './globalStyles.css'; 

const UpdateStudent = () => {
    const { id } = useParams(); 
    const navigate = useNavigate();
    const [student, setStudent] = useState({
        firstName: '',
        lastName: '',
        phoneNumber: '',
        email: '',
        danceStyle: ''
    });

    useEffect(() => {
        const fetchStudent = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/students/${id}`);
                setStudent(response.data);
            } catch (error) {
                console.error('Error fetching student:', error);
            }
        };

        fetchStudent();
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setStudent({ ...student, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.put(`http://localhost:8080/api/students/update/${id}`, student);
            alert('Student updated successfully!');
            navigate(`/student/${id}`); // Navigate back to student details page
        } catch (error) {
            console.error('Error updating student:', error);
            alert('Failed to update student.');
        }
    };

    return (
        <div className="student-details-container">
            <h2 className="title">Update Student</h2>
            <form onSubmit={handleSubmit}>
                <div className="student-info">
                    <label>First Name:</label>
                    <input
                        type="text"
                        name="firstName"
                        value={student.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="student-info">
                    <label>Last Name:</label>
                    <input
                        type="text"
                        name="lastName"
                        value={student.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="student-info">
                    <label>Phone Number:</label>
                    <input
                        type="text"
                        name="phoneNumber"
                        value={student.phoneNumber}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="student-info">
                    <label>Email:</label>
                    <input
                        type="email"
                        name="email"
                        value={student.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="student-info">
                    <label>Dance Style:</label>
                    <input
                        type="text"
                        name="danceStyle"
                        value={student.danceStyle}
                        onChange={handleChange}
                        required
                    />
                </div>

                {/* Update button */}
                <div className="button-container">
                    <button type="submit" className="btn btn-back">Update Student</button>
                </div>
            </form>
        </div>
    );
};

export default UpdateStudent;
