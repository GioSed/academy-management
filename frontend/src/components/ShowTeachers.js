import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './globalStyles.css';

function ShowTeachers() {
    const [teachers, setTeachers] = useState([]);
    const navigate = useNavigate(); 
 
    useEffect(() => {
        axios.get('http://localhost:8080/api/teachers/list')
            .then(response => {
                setTeachers(response.data);
            })
            .catch(error => {
                console.error('Error fetching teachers:', error);
            });
    }, []);
    const handleBackToMenu = () => {
        navigate('/menu'); 
    };

    return (
        <div className="teachers-list-container">
            <h1 className="title">Teachers List</h1>
            <ul className="teachers-list">
                {teachers.map(teacher => (
                    <li key={teacher.id} className="teacher-item">
                        <Link to={`/teachers/${teacher.id}`} className="teacher-link">
                            {teacher.firstName} {teacher.lastName}
                        </Link>
                    </li>
                ))}
            </ul>

            {/* Button to go back to menu */}
            <div className="button-container">
                <button className="btn btn-back" onClick={handleBackToMenu}>
                    Back to Menu
                </button>
            </div>
        </div>
    );
}

export default ShowTeachers;
