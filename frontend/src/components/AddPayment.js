import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker'; 
import 'react-datepicker/dist/react-datepicker.css'; 
import { registerLocale, setDefaultLocale } from 'react-datepicker';
import enUS from 'date-fns/locale/en-US'; 
import './globalStyles.css';


registerLocale('en-US', enUS); 
setDefaultLocale('en-US'); 

const AddPayment = () => {
    const { id } = useParams(); 
    const navigate = useNavigate();
    const [amount, setAmount] = useState('');
    const [monthPaid, setMonthPaid] = useState('');
    const [paymentDate, setPaymentDate] = useState(new Date()); 
    const [notes, setNotes] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const paymentData = {
                amount,
                monthPaid,
                paymentDate: paymentDate.toISOString().split('T')[0],
                notes
            };

            await axios.post(`http://localhost:8080/api/payments/student/${id}/add`, paymentData);
            alert('Payment added successfully!');
            navigate(`/student/${id}`);
        } catch (error) {
            console.error('Error adding payment:', error);
            alert('Failed to add payment. Please try again.');
        }
    };

    return (
        <div className="student-details-container">
            <h2 className="title">Add Payment</h2>
            <form onSubmit={handleSubmit}>
                <div className="student-info">
                    <label>Amount:</label>
                    <input
                        type="number"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        required
                    />
                </div>
                <div className="student-info">
                    <label>Month Paid:</label>
                    <input
                        type="text"
                        value={monthPaid}
                        onChange={(e) => setMonthPaid(e.target.value)}
                        required
                    />
                </div>
                <div className="student-info">
                    <label>Payment Date:</label>
                    <DatePicker
                        selected={paymentDate}
                        onChange={(date) => setPaymentDate(date)}
                        dateFormat="yyyy-MM-dd"
                        locale="en-US"
                        required
                    />
                </div>
                <div className="student-info">
                    <label>Notes:</label>
                    <input
                        type="text"
                        value={notes}
                        onChange={(e) => setNotes(e.target.value)}
                    />
                </div>
                {/* Add Payment button */}
                <div className="button-container">
                    <button type="submit" className="btn btn-back">
                        Add Payment
                    </button>
                </div>
            </form>
        </div>
    );
};

export default AddPayment;
