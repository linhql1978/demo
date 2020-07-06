package com.example.demo.service;

import com.example.demo.entity.PhoneCategorize;
import com.example.demo.repository.PhoneCategorizeRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneCategorizeService {

    @Autowired
    PhoneCategorizeRepository phoneCategorizeRepository;

    @PersistenceContext
    EntityManager entityManager;

    public Optional<PhoneCategorize> getPhoneCategorizeByGraph(Long id) {
        EntityGraph entityGraph = entityManager.createEntityGraph("graph.PhoneCategorize");
        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistence.fetchgraph", entityGraph);
        PhoneCategorize phoneCategorize = entityManager.find(PhoneCategorize.class, id, map);

        return Optional.ofNullable(phoneCategorize);
    }

}
