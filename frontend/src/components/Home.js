import React from 'react';
import { Link } from 'react-router-dom';
import './globalStyles.css';


function Home() {
    return (
        <div className="student-details-container">  {/* Use the main container class */}
            <h1 className="title">Welcome to Dance Art Academy</h1> {/* Use title class */}

            <div className="button-container">  {/* Use the button container for centering */}
                <Link to="/menu">
                    <button className="btn btn-back">Let's Start</button>  {/* Styled button */}
                </Link>
            </div>
        </div>
    );
}

export default Home;
