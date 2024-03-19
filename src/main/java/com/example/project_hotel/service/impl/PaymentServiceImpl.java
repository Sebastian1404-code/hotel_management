package com.example.project_hotel.service.impl;

import com.example.project_hotel.models.Payment;
import com.example.project_hotel.repos.PaymentRepository;
import com.example.project_hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
