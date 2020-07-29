/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	StudentDao.java
 * 模块说明：	
 * 修改历史：
 * 2018年9月13日 - Administrator - 创建。
 */
package com.shu.jpa.dao;


import com.shu.jpa.api.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 *
 */
public interface StudentDao extends JpaRepository<Student, Integer> {

}
