import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker'; 
import 'react-datepicker/dist/react-datepicker.css'; 
import { registerLocale, setDefaultLocale } from 'react-datepicker';
import enUS from 'date-fns/locale/en-US'; 
import './globalStyles.css'; 

registerLocale('en-US', enUS); 
setDefaultLocale('en-US'); 

function TeacherRegistration() {
    const [teacher, setTeacher] = useState({
        firstName: '',
        lastName: '',
        danceStyle: '',
        birthdate: new Date() 
    });

    const navigate = useNavigate(); 

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTeacher({
            ...teacher,
            [name]: value
        });
    };

    const handleDateChange = (date) => {
        setTeacher({
            ...teacher,
            birthdate: date
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault(); 

        const teacherData = {
            ...teacher,
            birthdate: teacher.birthdate.toISOString().split('T')[0] 
        };

        axios.post('http://localhost:8080/api/teachers/register', teacherData, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                console.log('Teacher registered:', response.data);
                alert('Teacher registered successfully!');
                navigate(`/teachers/${response.data.id}`);
            })
            .catch(error => {
                if (error.response) {
                    console.error('Error data:', error.response.data);
                    alert('Failed to register teacher. Error: ' + (error.response.data.message || error.response.data));
                } else if (error.request) {
                    console.error('Error request:', error.request);
                    alert('No response received from the server.');
                } else {
                    console.error('Error message:', error.message);
                    alert('An unexpected error occurred.');
                }
            });
    };

    return (
        <div className="container">
            <h2 className="title">Teacher Registration</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>First Name:</label>
                    <input
                        type="text"
                        name="firstName"
                        value={teacher.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input
                        type="text"
                        name="lastName"
                        value={teacher.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label>Dance Style:</label>
                    <input
                        type="text"
                        name="danceStyle"
                        value={teacher.danceStyle}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label>Birthdate:</label>
                    <DatePicker
                        selected={teacher.birthdate}
                        onChange={handleDateChange}
                        dateFormat="yyyy-MM-dd" 
                        locale="en-US" 
                        required
                    />
                </div>

                <button type="submit" className="btn">Register Teacher</button>
            </form>
        </div>
    );
}

export default TeacherRegistration;
