package com.project.webapp.service;

import com.project.webapp.dto.PaymentDTO;
import com.project.webapp.model.Payment;
import com.project.webapp.model.Student;
import com.project.webapp.repository.PaymentRepository;
import com.project.webapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<PaymentDTO> getPaymentsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getPayments().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        Student student = studentRepository.findById(paymentDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Payment payment = convertToEntity(paymentDTO, student);
        Payment savedPayment = paymentRepository.save(payment);

        return convertToDTO(savedPayment);
    }

    @Override
    public PaymentDTO addPaymentToStudent(Long studentId, PaymentDTO paymentDTO) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        
        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setMonthPaid(paymentDTO.getMonthPaid());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setStudent(student);
        payment.setNotes(paymentDTO.getNotes());

        Payment savedPayment = paymentRepository.save(payment);

        return convertToDTO(savedPayment);
    }

    @Override
    public void deletePaymentFromStudent(Long studentId, Long paymentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        student.getPayments().remove(payment);
        paymentRepository.delete(payment);
    }

    public PaymentDTO convertToDTO(Payment payment) {
        return new PaymentDTO(
            payment.getId(),
            payment.getAmount(),
            payment.getMonthPaid(),
            payment.getPaymentDate(),
            payment.getStudent().getId(),
            payment.getNotes()
        );
    }

    public Payment convertToEntity(PaymentDTO paymentDTO, Student student) {
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setMonthPaid(paymentDTO.getMonthPaid());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setStudent(student); 
        payment.setNotes(paymentDTO.getNotes());
        return payment;
    }
}
