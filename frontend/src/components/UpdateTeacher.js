import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker'; 
import 'react-datepicker/dist/react-datepicker.css'; 
import { registerLocale, setDefaultLocale } from 'react-datepicker';
import enUS from 'date-fns/locale/en-US'; 
import './globalStyles.css'; 

registerLocale('en-US', enUS);
setDefaultLocale('en-US');

const UpdateTeacher = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [teacher, setTeacher] = useState({
        firstName: '',
        lastName: '',
        danceStyle: '',
        birthdate: new Date() 
    });
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchTeacherDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/teachers/${id}`);
                const teacherData = response.data;
                setTeacher({
                    ...teacherData,
                    birthdate: new Date(teacherData.birthdate) 
                });
            } catch (error) {
                console.error('Error fetching teacher details:', error);
                setError('Failed to load teacher details.');
            }
        };

        fetchTeacherDetails();
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTeacher(prevTeacher => ({ ...prevTeacher, [name]: value }));
    };

    const handleDateChange = (date) => {
        setTeacher({
            ...teacher,
            birthdate: date
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const updatedTeacher = {
                ...teacher,
                birthdate: teacher.birthdate.toISOString().split('T')[0] // Convert date to 'YYYY-MM-DD'
            };
            const response = await axios.put(`http://localhost:8080/api/teachers/${id}`, updatedTeacher);
            console.log('Teacher updated successfully:', response.data);
            navigate('/show-teachers'); // Redirect after successful update
        } catch (error) {
            console.error('Error updating teacher:', error);
            alert('Failed to update teacher. Check console for details.');
        }
    };

    if (error) {
        return <div className="error-message">{error}</div>;
    }

    return (
        <div className="student-details-container">
            <h2 className="title">Update Teacher</h2>
            <form onSubmit={handleSubmit}>
                <div className="student-info">
                    <label>First Name:</label>
                    <input
                        type="text"
                        name="firstName"
                        value={teacher.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="student-info">
                    <label>Last Name:</label>
                    <input
                        type="text"
                        name="lastName"
                        value={teacher.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="student-info">
                    <label>Dance Style:</label>
                    <input
                        type="text"
                        name="danceStyle"
                        value={teacher.danceStyle}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="student-info">
                    <label>Birthdate:</label>
                    <DatePicker
                        selected={teacher.birthdate}
                        onChange={handleDateChange}
                        dateFormat="yyyy-MM-dd" 
                        locale="en-US"
                        required
                    />
                </div>

                <div className="button-container">
                    <button type="submit" className="btn btn-back">Update Teacher</button>
                </div>
            </form>
        </div>
    );
};

export default UpdateTeacher;
