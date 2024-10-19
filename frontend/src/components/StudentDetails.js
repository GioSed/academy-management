import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './globalStyles.css';

const StudentDetails = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [student, setStudent] = useState(null);
    const [payments, setPayments] = useState([]); 
    const [error, setError] = useState(null); 

    useEffect(() => {
        const fetchStudentDetails = async () => {
            try {
                const studentResponse = await axios.get(`http://localhost:8080/api/students/${id}`);
                setStudent(studentResponse.data);
                const paymentsResponse = await axios.get(`http://localhost:8080/api/payments/student/${id}`);
                setPayments(paymentsResponse.data);
            } catch (error) {
                console.error('Error fetching student details:', error);
                setError('Failed to load student details.'); 
            }
        };

        fetchStudentDetails();
    }, [id]);

    const handleDelete = async () => {
        try {
            await axios.delete(`http://localhost:8080/api/students/delete/${id}`); 
            navigate('/show-students'); 
        } catch (error) {
            console.error('Error deleting student:', error);
            alert('Failed to delete student.');
        }
    };

    const handleUpdate = () => {
        navigate(`/students/update/${id}`); 
    };

    const handleAddPayment = () => {
        navigate(`/student/${id}/add-payment`); 
    };

    if (error) {
        return <div className="error-message">{error}</div>; 
    }

    if (!student) {
        return <div className="loading-message">Loading...</div>;
    }

    return (
        <div className="student-details-container">
            <h1 className="title">Student Details</h1>
            <div className="student-info">
                <p><span>Name:</span> {student.firstName} {student.lastName}</p>
                <p><span>Email:</span> {student.email}</p>
                <p><span>Phone Number:</span> {student.phoneNumber}</p>
                <p><span>Dance Style:</span> {student.danceStyle}</p>
            </div>

            <div className="button-container">
                <button className="btn btn-delete" onClick={handleDelete}>Delete Student</button>
                <button className="btn" onClick={handleUpdate}>
                    Update Student
                </button>
                <button className="btn" onClick={handleAddPayment}>
                    Add Payment
                </button>
                <button className="btn btn-back" onClick={() => navigate('/show-students')}>
                    Back to Students
                </button>
            </div>

            {/* Displaying the list of payments */}
            <div className="payments-section">
                <h2 className="payments-title">Payments</h2>
                {payments.length > 0 ? (
                    <table className="payments-table">
                        <thead>
                            <tr>
                                <th>Amount</th>
                                <th>Month Paid</th>
                                <th>Payment Date</th>
                                <th>Notes</th>
                            </tr>
                        </thead>
                        <tbody>
                            {payments.map((payment) => (
                                <tr key={payment.id}>
                                    <td>{payment.amount}</td>
                                    <td>{payment.monthPaid}</td>
                                    <td>{payment.paymentDate}</td>
                                    <td>{payment.notes}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p className="no-payments-message">No payments found for this student.</p>
                )}
            </div>
        </div>
    );
};

export default StudentDetails;
