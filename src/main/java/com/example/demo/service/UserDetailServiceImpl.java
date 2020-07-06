package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountService.getAccountRepository().findAccountByUsername(s);
        System.out.println("User Id= "+account.getId());
//        System.out.println("Roles ="+account.getAccountRoles().);
        account = accountService.getAccountByGraph(account.getId());
        if (account == null) {
            System.out.println("Account not found! " + s);
            throw new UsernameNotFoundException("User " + s + " was not found in the database!");
        }
        System.out.println("Found User: " + account);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (AccountRole accountRole : account.getAccountRoles()) {
            //test
            System.out.println("Role= "+accountRole.getRole().getName());
            grantedAuthorityList.add(new SimpleGrantedAuthority(accountRole.getRole().getName()));
        }
        UserDetails userDetail = new User(account.getUsername(), account.getPassword(), grantedAuthorityList);

        return userDetail;
    }
}
