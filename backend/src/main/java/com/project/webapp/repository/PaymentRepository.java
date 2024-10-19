package com.project.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.webapp.model.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findByStudentId(Long studentId);
}
