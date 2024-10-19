import React from 'react';
import { Link } from 'react-router-dom';
import './globalStyles.css';  

function Menu() {
    return (
        <div className="component-container">
            <h2 className="title">Menu</h2>
            <ul className="list">
                <li>
                    <Link to="/student-registration">Student Registration</Link>
                </li>
                <li>
                    <Link to="/teacher-registration">Teacher Registration</Link>
                </li>
                <li>
                    <Link to="/show-students">Show Students</Link>
                </li>
                <li>
                    <Link to="/show-teachers">Show Teachers</Link>
                </li>
            </ul>
        </div>
    );
}

export default Menu;
