package com.higgsup.intern.ebshop.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan("com.higgsup.intern.ebshop.jpa")
@EnableTransactionManagement
@EnableJpaRepositories("com.higgsup.intern.ebshop.jpa.repo")
public class DataSourceConfig {

    private final Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
        config.setJdbcUrl(environment.getProperty("datasource.url"));
        config.setUsername(environment.getProperty("datasource.username"));
        config.setPassword(environment.getProperty("datasource.password"));

        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPersistenceUnitName("ebshop-persistence-unit");
        entityManagerFactoryBean.setPackagesToScan("com.higgsup.intern.ebshop.jpa.entity");
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());

        return entityManagerFactoryBean;
    }

    private Properties getJpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.implicit_naming_strategy", environment.getProperty("hibernate.implicit_naming_strategy"));
        jpaProperties.put("hibernate.physical_naming_strategy", environment.getProperty("hibernate.physical_naming_strategy"));
        jpaProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        return jpaProperties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}