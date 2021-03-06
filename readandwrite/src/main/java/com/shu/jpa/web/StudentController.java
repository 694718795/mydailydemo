/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	StudentController.java
 * 模块说明：	
 * 修改历史：
 * 2018年9月13日 - Administrator - 创建。
 */
package com.shu.jpa.web;


import com.shu.jpa.api.Student;
import com.shu.jpa.core.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 *
 */
@Api(value = "学生管理接口")
@RestController
@RequestMapping("/student/*")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @ApiOperation(value = "获取所有的学生信息", httpMethod = "GET")
  @RequestMapping("findAll")
  public List<Student> findAll() {
    return studentService.findAll();
  }

  @ApiOperation(value = "获取学生信息", notes = "根据url的id获取学生信息", httpMethod = "GET")
  @ApiImplicitParam(name = "id", value = "学生唯一标识", required = true, dataType = "Integer",
      paramType = "path")
  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public Student findById(@PathVariable("id") Integer id) {
    return studentService.findById(id);
  }

  @ApiOperation(value = "新增/更新学生信息", notes = "id为空时表示新增，否则为更新", httpMethod = "POST")
  @ApiImplicitParam(name = "entity", value = "学生实体类", required = true, dataType = "Student")
  @RequestMapping(value = "save", method = RequestMethod.POST)
  public Integer save(@RequestBody Student entity) throws Exception {
    return studentService.save(entity);
  }

  @ApiOperation(value = "新增/更新学生信息", notes = "id为空时表示新增，否则为更新", httpMethod = "POST")
  @ApiImplicitParam(name = "entity", value = "学生实体类", required = true, dataType = "Student")
  @RequestMapping(value = "saveread", method = RequestMethod.POST)
  public Integer saveread(@RequestBody Student entity) throws Exception {
    return studentService.saveread(entity);
  }
}
