/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-es
 * 文件名：	User.java
 * 模块说明：
 * 修改历史：
 * 2018/10/25 - zhuchao - 创建。
 */
package com.enroy.cloud.es.api;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author zhuchao
 */
@Data
@Document(indexName = User.INDEX, type = User.TYPE)
public class User {
  public static final String INDEX = "cat";
  public static final String TYPE = "user";
  @Id
  private String uuid;
  private String code;
  private String name;
}
