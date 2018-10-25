/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-es
 * 文件名：	UserRepository.java
 * 模块说明：
 * 修改历史：
 * 2018/10/25 - zhuchao - 创建。
 */
package com.enroy.cloud.es.repository;

import com.enroy.cloud.es.api.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 */
@Component
public interface UserRepository extends ElasticsearchRepository<User, String> {

  User queryUserByCode(String code);

}
