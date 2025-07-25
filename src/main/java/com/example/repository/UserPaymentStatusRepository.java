package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.UserPaymentStatus;

public interface UserPaymentStatusRepository extends JpaRepository<UserPaymentStatus, Long> {
    // custom queries if needed
}
