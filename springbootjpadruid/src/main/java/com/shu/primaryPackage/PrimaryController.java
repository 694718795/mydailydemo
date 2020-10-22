package com.shu.primaryPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-10-22 16:38
 */
@RestController
public class PrimaryController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("findAll")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
