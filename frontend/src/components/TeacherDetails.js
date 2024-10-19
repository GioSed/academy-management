import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './globalStyles.css';

const TeacherDetails = () => {
    const { id } = useParams(); 
    const navigate = useNavigate();
    const [teacher, setTeacher] = useState(null);
    const [error, setError] = useState(null); 

    useEffect(() => {
        const fetchTeacherDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/teachers/${id}`);
                setTeacher(response.data);
            } catch (error) {
                console.error('Error fetching teacher details:', error);
                setError('Failed to load teacher details.');
            }
        };

        fetchTeacherDetails();
    }, [id]);

    const handleDelete = async () => {
        try {
            await axios.delete(`http://localhost:8080/api/teachers/${id}`);
            navigate('/show-teachers');
        } catch (error) {
            console.error('Error deleting teacher:', error);
            alert('Failed to delete teacher.');
        }
    };

    if (error) {
        return <div className="error-message">{error}</div>; 
    }

    if (!teacher) {
        return <div className="loading-message">Loading...</div>;
    }

    return (
        <div className="teacher-details-container">
            <h1 className="title">Teacher Details</h1>
            <div className="teacher-info">
                <p><span>Name:</span> {teacher.firstName} {teacher.lastName}</p>
                <p><span>Birthdate:</span> {teacher.birthdate}</p>
                <p><span>Dance Style:</span> {teacher.danceStyle}</p>
            </div>

            <div className="button-container">
                <button className="btn btn-delete" onClick={handleDelete}>Delete Teacher</button>
                <button className="btn" onClick={() => navigate(`/teachers/update/${id}`)}>
                    Update Teacher
                </button>
                <button className="btn btn-back" onClick={() => navigate('/show-teachers')}>
                    Back to Teachers
                </button>
            </div>

            <div className="students-section">
                <h2 className="students-title">Students Assigned to {teacher.firstName} {teacher.lastName}</h2>
                {teacher.studentNames && teacher.studentNames.length > 0 ? (
                    <ul className="students-list">
                        {teacher.studentNames.map((student, index) => (
                            <li key={index}>{student}</li>  // This is where student names are displayed
                        ))}
                    </ul>
                ) : (
                    <p className="no-students-message">No students are assigned to this teacher.</p>
                )}
            </div>
        </div>
    );
};

export default TeacherDetails;
