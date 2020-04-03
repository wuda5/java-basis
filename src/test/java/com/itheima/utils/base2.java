package com.itheima.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component("base2")

@Data
@AllArgsConstructor
public class base2 {

    private Long id;
    private String username;
    private String password;
    private String name;

    public base2(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("a");
    }

    public base2() {
        System.out.println("no----2222222222222--------------");
    }
}
