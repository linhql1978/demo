package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@NamedEntityGraph(name = "graph.PhoneCategorize",
        attributeNodes = {@NamedAttributeNode(value = "phones",subgraph = "phones")},
        subgraphs = @NamedSubgraph(name = "phones",
                attributeNodes = {@NamedAttributeNode(value = "phoneDetail"),@NamedAttributeNode(value = "phoneComments")}))
public class PhoneCategorize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "phoneCategorize", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Phone> phones;
}
