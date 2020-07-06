package com.example.demo.entity;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Scope(value = "request")
@Getter
@Setter
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @PrimaryKeyJoinColumn()
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @PrimaryKeyJoinColumn()
    private Account account;

    // occur circular reference relationship
//    @Override
//    public String toString() {
//        return "AccountRole{" +
//                "id=" + id +
//                ", role=" + role +
//                ", account=" + account +
//                '}';
//    }
    //if use //@EqualsAndHashCode(exclude = {"role", "account"}) of lombok
    //then occur 2 instance has same hashcode
}
