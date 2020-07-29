/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	StudentService.java
 * 模块说明：	
 * 修改历史：
 * 2018年9月13日 - Administrator - 创建。
 */
package com.shu.jpa.core;



import com.shu.jpa.api.Student;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface StudentService {

  List<Student> findAll();

  Student findById(Integer id);

  Integer save(Student entity) throws Exception;
}
