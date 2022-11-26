package com.example.hotel.repository;

import com.example.hotel.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
}