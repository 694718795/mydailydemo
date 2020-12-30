/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	StudentServiceImpl.java
 * 模块说明：	
 * 修改历史：
 * 2018年9月13日 - Administrator - 创建。
 */
package com.shu.jpa.core;


import com.shu.config.DataSourceConfig;
import com.shu.config.TargetDateSource;
import com.shu.jpa.api.Student;
import com.shu.jpa.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentDao studentDao;

  @Override
  @TargetDateSource(dataSource = DataSourceConfig.READ_DATASOURCE_KEY)
  public List<Student> findAll() {
    return studentDao.findAll();
  }

  @Override
  @TargetDateSource(dataSource = DataSourceConfig.READ_DATASOURCE_KEY)
  public Student findById(Integer id) {
    Optional<Student> students = studentDao.findById(id);
    if (students.isPresent() && students.get() != null) {
      return students.get();
    }
    return null;
  }

  @Override
  @Transactional
  @TargetDateSource(dataSource = DataSourceConfig.WRITE_DATASOURCE_KEY)
  public Integer save(Student entity) throws Exception {
    if (entity.getId() != null) {
      Student perz = studentDao.saveAndFlush(entity);
      return perz.getId();
    }
    Student perz = studentDao.save(entity);
    return perz.getId();
  }

  @Override
  @Transactional
  @TargetDateSource(dataSource = DataSourceConfig.READ_DATASOURCE_KEY)
  public Integer saveread(Student entity) throws Exception {
    if (entity.getId() != null) {
      Student perz = studentDao.saveAndFlush(entity);
      return perz.getId();
    }
    Student perz = studentDao.save(entity);
    return perz.getId();
  }

}
