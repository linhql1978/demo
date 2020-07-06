package com.example.demo.repository;

import com.example.demo.entity.PhoneCategorize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneCategorizeRepository extends JpaRepository<PhoneCategorize, Long> {

    @Query("select pc from PhoneCategorize pc where pc.name = :name")
    Optional<PhoneCategorize> findPhoneCategorizeByName(@Param("name") String name);
}
