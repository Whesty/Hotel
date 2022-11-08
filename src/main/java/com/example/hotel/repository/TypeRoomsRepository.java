package com.example.hotel.repository;

import com.example.hotel.model.TypeRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomsRepository extends JpaRepository<TypeRooms, Integer> {
}