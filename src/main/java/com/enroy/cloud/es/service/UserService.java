/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-es
 * 文件名：	UserService.java
 * 模块说明：
 * 修改历史：
 * 2018/10/25 - zhuchao - 创建。
 */
package com.enroy.cloud.es.service;

import com.alibaba.fastjson.JSONObject;
import com.enroy.cloud.es.api.User;
import com.enroy.cloud.es.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuchao
 */
@Service
@Slf4j
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  public User queryUserByCode(String code) {
    return userRepository.queryUserByCode(code);
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  /** 使用elasticsearchTemplate定制查询 */
  public List<User> queryUsers(String keyword) {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(matchAllQuery(keyword))
            .withIndices("cat")
            .withTypes("user")
            .withPageable(new PageRequest(0, 1))
            .build();
    Page<User> users = elasticsearchTemplate.queryForPage(searchQuery, User.class);
    return users.getContent();
  }

  private QueryBuilder matchAllQuery(String keyword) {
    return QueryBuilders
            .boolQuery()
            .should(QueryBuilders.termQuery("name", keyword));
  }
}
