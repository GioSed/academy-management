package com.project.webapp.service;

import com.project.webapp.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentDTO> getPaymentsByStudentId(Long studentId);
    PaymentDTO addPaymentToStudent(Long studentId, PaymentDTO paymentDTO);
    void deletePaymentFromStudent(Long studentId, Long paymentId);
    public PaymentDTO addPayment(PaymentDTO paymentDTO);
}