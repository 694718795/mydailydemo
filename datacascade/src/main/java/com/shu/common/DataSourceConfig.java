
package com.shu.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *  https://www.cnblogs.com/SimpleWu/p/10049825.html
 *
 * SpringBoot 三种方式配置 Druid https://www.cnblogs.com/yjq520/p/10779356.html
 *
 * JAVA-SpringBoot+JPA+Druid多数据源配置
 * https://blog.csdn.net/handong01027/article/details/97246560?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
 * 
 * @author Administrator
 *
 */
@Configuration
public class DataSourceConfig {

  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean(name="duridDataSource")
  public DruidDataSource druidDataSource(){
    return new DruidDataSource();
  }

  /**
   * 配置监控服务器
   * @return 返回监控注册的servlet对象
   * @author SimpleWu
   */
//  @Bean
//  public ServletRegistrationBean statViewServlet() {
//    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//    // 添加IP白名单
//    servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
//    // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
//    servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
//    // 添加控制台管理用户
//    servletRegistrationBean.addInitParameter("loginUsername", "SimpleWu");
//    servletRegistrationBean.addInitParameter("loginPassword", "123456");
//    // 是否能够重置数据
//    servletRegistrationBean.addInitParameter("resetEnable", "false");
//    return servletRegistrationBean;
//  }

  /**
   * 配置服务过滤器
   *
   * @return 返回过滤器配置对象
   */
//  @Bean
//  public FilterRegistrationBean statFilter() {
//    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//    // 添加过滤规则
//    filterRegistrationBean.addUrlPatterns("/*");
//    // 忽略过滤格式
//    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
//    return filterRegistrationBean;
//  }

}