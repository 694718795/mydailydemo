package com.shu.secondPackege;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-10-22 15:40
 */
@Entity
@Table(name = "teacher")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
