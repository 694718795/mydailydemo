package com.shu.springbootes.controller;

import com.shu.springbootes.po.BookBean;
import com.shu.springbootes.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-09-22 10:51
 */
@RestController
public class ElasticController {
    @Autowired
    private BookService bookService;


    @GetMapping("/getlist}")
    @ResponseBody
    public  List<BookBean> getlist(){
        List<BookBean> all = bookService.findAll();

        return all;
    }

    @GetMapping("/book/{id}")
    @ResponseBody
    public BookBean getBookById(@PathVariable String id){
        Optional<BookBean> opt =bookService.findById(id);
        BookBean book=opt.get();
        System.out.println(book);
        return book;
    }

    @PostMapping("/save")
    @ResponseBody
    public void Save(@RequestBody BookBean book){

        System.out.println(book);
        bookService.save(book);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody String id){

        System.out.println(id);
        bookService.delete(id);
    }

}
