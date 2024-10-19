import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Menu from './components/Menu';
import StudentRegistration from './components/StudentRegistration';
import TeacherRegistration from './components/TeacherRegistration';
import ShowStudents from './components/ShowStudents';
import ShowTeachers from './components/ShowTeachers';
import StudentDetails from './components/StudentDetails';
import TeacherDetails from './components/TeacherDetails';
import UpdateTeacher from './components/UpdateTeacher';
import UpdateStudent from './components/UpdateStudent';
import AddPayment from './components/AddPayment';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/menu" element={<Menu />} />
                <Route path="/student-registration" element={<StudentRegistration />} />
                <Route path="/teacher-registration" element={<TeacherRegistration />} />
                <Route path="/show-students" element={<ShowStudents />} />
                <Route path="/show-teachers" element={<ShowTeachers />} />
                <Route path="/student/:id" element={<StudentDetails />} />
                <Route path="/teachers/:id" element={<TeacherDetails />} />
                <Route path="/teachers/update/:id" element={<UpdateTeacher />} />
                <Route path="/students/update/:id" element={<UpdateStudent />} />
                <Route path="/student/:id/add-payment" element={<AddPayment />} />
            </Routes>
        </Router>
    );
}

export default App;
