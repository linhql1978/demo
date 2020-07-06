package com.example.demo;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private ApplicationContext ctx;

    @Test
    void contextLoads() {
        System.out.println(ctx);
        Cart cart = ctx.getBean(Cart.class);
        System.out.println(cart);
//        Phone phone = new Phone("AAA", 3333.0);
//        phone = new Phone();
    }

}
