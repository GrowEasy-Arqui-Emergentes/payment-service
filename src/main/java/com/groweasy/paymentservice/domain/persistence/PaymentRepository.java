package com.groweasy.paymentservice.domain.persistence;

import com.groweasy.paymentservice.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByDate(Date date);
    Optional<Payment> findByUuid(String uuid);

}
