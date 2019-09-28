package com.bazinga.base.configuration;

import lombok.Data;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.util.Properties;

@Data
@Configuration
public class SessionFactoryConfiguration {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;

    @Value("${hibernate.cache.use_second_level_cache}")
    private String hibernateSecondLevelCache;

    @Value("${scanPackages}")
    private String scanPackages;

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);

        localSessionFactoryBuilder.scanPackages(getScanPackages());
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", getHibernateDialect());
        hibernateProperties.put("hibernate.show_sql", getHibernateShowSql());
        localSessionFactoryBuilder.addProperties(hibernateProperties);
        return localSessionFactoryBuilder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(DataSource dataSource) {
        return new HibernateTransactionManager(sessionFactory(dataSource));
    }
}
