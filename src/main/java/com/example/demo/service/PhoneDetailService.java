package com.example.demo.service;

import com.example.demo.repository.PhoneDetailRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDetailService {

    @Autowired
    PhoneDetailRepository phoneDetailRepository;

}
