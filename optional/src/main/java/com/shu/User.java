package com.shu;

import lombok.Data;

import java.util.Optional;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-08-05 11:57
 */
@Data
public class User {
    String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }


    public Optional<String> getPosition() {
        return Optional.ofNullable(name);
    }
}
