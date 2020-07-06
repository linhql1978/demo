package com.example.demo.entity;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Component
@Scope(value = "session")
@Getter
@Setter
@NamedEntityGraph(name = "graph.Account.roles",attributeNodes = {@NamedAttributeNode(value = "accountRoles",subgraph = "accountRoles"),
        @NamedAttributeNode(value = "address")},subgraphs = {@NamedSubgraph(name = "accountRoles",attributeNodes = @NamedAttributeNode("role"))})
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Long idBill;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<AccountRole> accountRoles;

}
