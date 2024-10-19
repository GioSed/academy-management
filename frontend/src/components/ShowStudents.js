import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './globalStyles.css';  

const ShowStudents = () => {
    const [students, setStudents] = useState([]);
    const navigate = useNavigate(); 

    useEffect(() => {
        const fetchStudents = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/students/list');
                if (Array.isArray(response.data)) {
                    setStudents(response.data);
                } else {
                    console.error("Expected an array but got", typeof response.data);
                    setStudents([]);
                }
            } catch (error) {
                console.error("Error fetching students:", error);
            }
        };

        fetchStudents();
    }, []);

    if (!Array.isArray(students) || students.length === 0) {
        return (
            <div>
                <div>No students found</div>
                <button onClick={() => navigate('/menu')} className="btn" style={{ marginTop: '20px' }}>
                    Back to Menu
                </button>
            </div>
        );
    }

    return (
        <div className="container">
            <h1 className="title">Students List</h1>
            <ul>
                {students.map(student => (
                    <li key={student.id}>
                        <Link to={`/student/${student.id}`}>
                            {student.firstName} {student.lastName}
                        </Link>
                    </li>
                ))}
            </ul>

            {/* Back to Menu button */}
            <button onClick={() => navigate('/menu')} className="btn" style={{ marginTop: '20px' }}>
                Back to Menu
            </button>
        </div>
    );
};

export default ShowStudents;
