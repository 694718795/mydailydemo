package com.shu.primaryPackage;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-10-22 15:39
 */
@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
