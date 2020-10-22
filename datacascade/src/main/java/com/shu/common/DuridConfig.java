package com.shu.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.shu.dao.*"}//repository所在位置
)
public class DuridConfig {
    @Autowired
    @Qualifier("duridDataSource")
    private DataSource dataSource;
    @Autowired
    private JpaProperties jpaProperties;




    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(entityManagerFactory(builder).getObject()).createEntityManager();
    }
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties())
                .packages("com.shu.po.*") //设置实体类所在位置
                .persistenceUnit("duridPersistenceUnit")
                .build();
    }
    private Map<String, Object> getVendorProperties() {
        Map<String, Object> ret = jpaProperties.getHibernateProperties(new HibernateSettings());
      //数据库方言  https://blog.csdn.net/jialinqiang/article/details/8679171
//        ret.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        ret.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return ret;
    }
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory(builder).getObject()));
    }


    @Bean
    @Primary
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter =new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(false);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        return adapter;
    }




    @Bean(name = "jdbcTemplate")
    public JdbcTemplate iflytekJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }
}
