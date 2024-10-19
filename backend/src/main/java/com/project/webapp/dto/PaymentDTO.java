package com.project.webapp.dto;

import java.time.LocalDate;

public class PaymentDTO {

    private Long id;
    private double amount;
    private String monthPaid;
    private LocalDate paymentDate;
    private Long studentId; 
    private String notes;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, double amount, String monthPaid, LocalDate paymentDate, Long studentId, String notes) {
        this.id = id;
        this.amount = amount;
        this.monthPaid = monthPaid;
        this.paymentDate = paymentDate;
        this.studentId = studentId;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMonthPaid() {
        return monthPaid;
    }

    public void setMonthPaid(String monthPaid) {
        this.monthPaid = monthPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
