package com.enroy.cloud.es;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
public class EsApplication {

  public static void main(String[] args) {
    SpringApplication.run(EsApplication.class, args);
  }

  @Bean
  public Docket swaggerDocket() {
    return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).build();
  }

  @Bean
  public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    //设置日期格式
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//反序列化时，解析不存在参数不报错
    mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
    //设置中文编码格式
    List<MediaType> list = new ArrayList<MediaType>();
    list.add(MediaType.APPLICATION_JSON_UTF8);
    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
    return mappingJackson2HttpMessageConverter;
  }
}
