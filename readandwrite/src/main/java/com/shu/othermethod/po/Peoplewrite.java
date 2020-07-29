package com.shu.othermethod.po;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-28 17:15
 */
@Entity
@Table(name = "peoplewrite")
public class Peoplewrite {
    private Integer id;
    private String name;
    private String cardNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
