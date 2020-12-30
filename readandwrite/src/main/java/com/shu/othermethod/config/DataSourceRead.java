package com.shu.othermethod.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-28 17:20
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryRead",
        transactionManagerRef = "transactionManagerRead",
        basePackages = {"com.shu.othermethod.readdao"}//repository所在位置

)
public class DataSourceRead {

    @Autowired
    @Qualifier("readDruidDataSource")
    private DataSource dataSource;
    @Autowired
    private JpaProperties jpaProperties;





    @Bean(name = "entityManagerRead")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryIflytek(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryRead")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryIflytek(EntityManagerFactoryBuilder builder) {

        return builder

                .dataSource(dataSource)

                .properties(getVendorProperties())

                .packages("com.shu.othermethod.readpo") //设置实体类所在位置

                .persistenceUnit("iflytekPersistenceUnit")

                .build();

    }

    private Map<String, Object> getVendorProperties() {

        Map<String, Object> ret = jpaProperties.getHibernateProperties(new HibernateSettings());

        ret.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");

        return ret;

    }

//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
//
//        return new JpaTransactionManager(entityManagerFactoryIflytek(builder).getObject());
//
//    }

    @Bean(name = "myJdbcTemplateRead")

    public JdbcTemplate JdbcTemplateRead(){
        return new JdbcTemplate(dataSource);
    }
}
