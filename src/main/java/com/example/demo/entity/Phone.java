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
@NamedEntityGraph(name = "graph.Phone",
        attributeNodes = {@NamedAttributeNode(value = "phoneDetail"),
                @NamedAttributeNode(value = "phoneComments"),
                @NamedAttributeNode(value = "phoneCategorize", subgraph = "phoneCategorize")},
        subgraphs = @NamedSubgraph(name = "phoneCategorize", attributeNodes = @NamedAttributeNode("phones")))
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phoneId")
    private Long id;

    private String name;

    private Double price;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
    private Set<PhoneComment> phoneComments;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private PhoneCategorize phoneCategorize;

    @OneToOne(mappedBy = "phone", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PhoneDetail phoneDetail;

}
