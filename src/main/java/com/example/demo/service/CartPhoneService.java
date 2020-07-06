package com.example.demo.service;

import com.example.demo.repository.CartPhoneRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartPhoneService {

    @Autowired
    private CartPhoneRepository cartPhoneRepository;

}
