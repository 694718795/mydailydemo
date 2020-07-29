package com.shu.aopimplement;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-17 16:39
 */

public class Po implements Serializable {

    @JsonProperty(value = "id")
    private Long id;

    @PrivateData
    private String name;

    @PrivateData
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Po{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
