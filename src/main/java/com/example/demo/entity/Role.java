package com.example.demo.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Component
@Data
@ToString(exclude = "accountRoles")
@EqualsAndHashCode(exclude = "accountRoles")
//@AllArgsConstructor
//@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private Set<AccountRole> accountRoles;

}
