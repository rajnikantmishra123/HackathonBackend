package com.example.HackathonBackend.repository;

import com.example.HackathonBackend.entity.Payment;
import com.example.HackathonBackend.entity.type.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderId(Long orderId);

    Optional<Payment> findByTransactionId(String transactionId);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
}
