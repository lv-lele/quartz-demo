package com.yonyou.cloud.quartz.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/** @author daniell */
@Log4j2
@Configuration
@ComponentScan(basePackages = "com.yonyou.cloud.quartz")
public class SchedledConfiguration {

  @Autowired private DataSource dataSource;

  /**
   * config Scheduler
   *
   * @return
   */
  @Bean
  public SchedulerFactoryBean getSchedulerFactoryBean() {
    SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
    factoryBean.setQuartzProperties(quartzProperties());
    factoryBean.setDataSource(dataSource);
    return factoryBean;
  }

  public Properties quartzProperties() {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    try {
      propertiesFactoryBean.afterPropertiesSet();
      Properties properties = propertiesFactoryBean.getObject();
      log.info("加载 Properties:{}", properties.getProperty("org.quartz.threadPool.class"));
      return properties;
    } catch (IOException e) {
      log.error("文件解析异常",e);
    }
    return null;
  }
}
