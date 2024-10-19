package com.project.webapp.service;

import com.project.webapp.dto.PaymentDTO;
import com.project.webapp.model.Student;
import com.project.webapp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private StudentRepository studentRepository;

    private Student createTestStudent() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("johndoe@example.com");
        student.setPhoneNumber(1234567890L);
        student.setDanceStyle("Bachata");
        return studentRepository.save(student); 
    }

    @Test
    public void testAddPayment() {
        Student student = createTestStudent(); 
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(100);
        paymentDTO.setMonthPaid("October");
        paymentDTO.setPaymentDate(LocalDate.now());
        paymentDTO.setNotes("First payment");
        paymentDTO.setStudentId(student.getId()); 

        PaymentDTO savedPayment = paymentService.addPayment(paymentDTO);

        assertNotNull(savedPayment.getId(), "Payment ID should not be null");
        assertEquals(100, savedPayment.getAmount(), "Payment amount should be 100");
        assertEquals("October", savedPayment.getMonthPaid(), "Month paid should be October");
        assertEquals(student.getId(), savedPayment.getStudentId(), "Payment should be linked to the correct student");
    }

    @Test
    public void testGetPaymentsByStudentId() {
        Student student = createTestStudent(); 
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(100);
        paymentDTO.setMonthPaid("October");
        paymentDTO.setPaymentDate(LocalDate.now());
        paymentDTO.setNotes("First payment");
        paymentDTO.setStudentId(student.getId());

        paymentService.addPayment(paymentDTO);

        List<PaymentDTO> payments = paymentService.getPaymentsByStudentId(student.getId());

        assertNotNull(payments, "Payments list should not be null");
        assertTrue(payments.size() > 0, "There should be at least one payment for the student");
    }

    @Test
    public void testDeletePaymentFromStudent() {
        Student student = createTestStudent(); 
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(200);
        paymentDTO.setMonthPaid("November");
        paymentDTO.setPaymentDate(LocalDate.now());
        paymentDTO.setNotes("Second payment");
        paymentDTO.setStudentId(student.getId());

        PaymentDTO savedPayment = paymentService.addPayment(paymentDTO);

        paymentService.deletePaymentFromStudent(student.getId(), savedPayment.getId());
        List<PaymentDTO> paymentsAfterDeletion = paymentService.getPaymentsByStudentId(student.getId());
        assertFalse(paymentsAfterDeletion.stream().anyMatch(p -> p.getId().equals(savedPayment.getId())),
                "Payment should be deleted and not present in the student's payments list");
    }
}

