package com.example.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.PaymentDTO;
import com.example.entity.Payment;
import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;
import com.example.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentDTO makePayment(PaymentDTO dto) {
        Payment pay = new Payment();
        pay.setUserId(dto.getUserId());
        pay.setPlanId(dto.getPlanId());
        pay.setAmount(BigDecimal.valueOf(dto.getAmount()));
        pay.setCurrency(dto.getCurrency());
        pay.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));
        pay.setPaymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus()));
        pay.setPaymentDate(LocalDateTime.ofInstant(dto.getPaymentDate().toInstant(), ZoneId.systemDefault()));
        pay.setTransactionId(dto.getTransactionId());

        Payment saved = paymentRepository.save(pay);

        dto.setId(saved.getId());
        dto.setPaymentDate(Date.from(saved.getPaymentDate().atZone(ZoneId.systemDefault()).toInstant()));
        dto.setPaymentStatus(saved.getPaymentStatus().toString());
        return dto;
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(p -> {
            PaymentDTO dto = new PaymentDTO();
            dto.setId(p.getId());
            dto.setUserId(p.getUserId());
            dto.setPlanId(p.getPlanId());
            dto.setAmount(p.getAmount().doubleValue());
            dto.setCurrency(p.getCurrency());
            dto.setPaymentType(p.getPaymentType().toString());
            dto.setPaymentStatus(p.getPaymentStatus().toString());
            dto.setPaymentDate(Date.from(p.getPaymentDate().atZone(ZoneId.systemDefault()).toInstant()));
            dto.setTransactionId(p.getTransactionId());
            return dto;
        }).collect(Collectors.toList());
    }
}
