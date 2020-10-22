package com.shu.springbootes.service;

import com.google.common.collect.Lists;
import com.shu.springbootes.dao.BookRepository;
import com.shu.springbootes.po.BookBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-09-22 10:47
 */
@Service("blogService")
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("bookRepository")
    private BookRepository bookRepository;


    @Override
    public Optional<BookBean> findById(String id) {
        //CrudRepository中的方法
        return bookRepository.findById(id);
    }

    @Override
    public BookBean save(BookBean blog) {
        return bookRepository.save(blog);
    }

    public void saveAll(List<BookBean> bookBeans) {
        bookRepository.saveAll(bookBeans);

    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void delete(BookBean blog) {

    }

    @Override
    public Optional<BookBean> findOne(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookBean> findAll() {
        Iterable<BookBean> all = bookRepository.findAll();

        ArrayList<BookBean> bookBeans1 = Lists.newArrayList(all);

        return bookBeans1;

    }

    @Override
    public Page<BookBean> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author,pageRequest);
    }

    @Override
    public Page<BookBean> findByTitle(String title, PageRequest pageRequest) {
        return bookRepository.findByTitle(title,pageRequest);
    }
}
