package com.example.demo.service;

import com.example.demo.repository.CartRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;

}
