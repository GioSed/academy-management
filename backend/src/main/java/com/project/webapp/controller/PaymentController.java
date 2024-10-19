package com.project.webapp.controller;

import com.project.webapp.dto.PaymentDTO;
import com.project.webapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/student/{id}/add")
    public ResponseEntity<PaymentDTO> addPaymentToStudent(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO addedPayment = paymentService.addPaymentToStudent(id, paymentDTO);
        return ResponseEntity.ok(addedPayment);
    }

    @DeleteMapping("/student/{studentId}/payment/{paymentId}")
    public ResponseEntity<Void> deletePaymentFromStudent(@PathVariable Long studentId, @PathVariable Long paymentId) {
        paymentService.deletePaymentFromStudent(studentId, paymentId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/student/{studentId}")
    public List<PaymentDTO> getPaymentsByStudentId(@PathVariable Long studentId) {
        return paymentService.getPaymentsByStudentId(studentId);
    }
}
