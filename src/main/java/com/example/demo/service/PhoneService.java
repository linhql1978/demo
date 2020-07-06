package com.example.demo.service;

import com.example.demo.entity.Phone;
import com.example.demo.repository.PhoneRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Phone> getPhoneByGraph(Long id) {
        EntityGraph entityGraph = entityManager.createEntityGraph("graph.Phone");
        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistence.fetchgraph", entityGraph);
        Phone phone = entityManager.find(Phone.class, id, map);

        return Optional.ofNullable(phone);
    }
}
