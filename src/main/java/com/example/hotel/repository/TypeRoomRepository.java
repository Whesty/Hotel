package com.example.hotel.repository;

import com.example.hotel.model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom, Integer> {

}