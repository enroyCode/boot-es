/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-es
 * 文件名：	UserController.java
 * 模块说明：
 * 修改历史：
 * 2018/10/25 - zhuchao - 创建。
 */
package com.enroy.cloud.es.controller;

import com.enroy.cloud.es.api.User;
import com.enroy.cloud.es.repository.UserRepository;
import com.enroy.cloud.es.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuchao
 */
@Api("测试")
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=utf-8")
public class UserController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserService userService;

  @ApiOperation("测试")
  @RequestMapping(value = "/get/{code}", method = RequestMethod.GET)
  public User getUser(@ApiParam("用户code") @PathVariable("code") String code) {
    return userService.queryUserByCode(code);
  }

  @ApiOperation("插入")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public User save(@ApiParam("用户") @RequestBody User user) {
    return userService.save(user);
  }

  @ApiOperation("查询")
  @RequestMapping(value = "/query", method = RequestMethod.POST)
  @ResponseBody
  public List<User> save(@ApiParam("用户") @RequestParam("key") String key) {
    return userService.queryUsers(key);
  }
}
