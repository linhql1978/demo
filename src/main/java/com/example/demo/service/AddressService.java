package com.example.demo.service;

import com.example.demo.repository.AddressRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
}
