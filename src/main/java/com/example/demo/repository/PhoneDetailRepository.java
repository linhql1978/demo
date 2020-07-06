package com.example.demo.repository;

import com.example.demo.entity.PhoneDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDetailRepository extends JpaRepository<PhoneDetail,Long> {
}
