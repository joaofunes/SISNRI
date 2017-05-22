package com.sisrni.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceHibernateConfig {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean alertsSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{"com.sisrni.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(alertsSessionFactory().getObject());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator exceptionTranslation() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public DataSource dataSource() throws Exception {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");

//        //servidor BD
//        ds.setUrl("jdbc:mysql://srnidb.cdl1mrxlsyzf.sa-east-1.rds.amazonaws.com:3306/sisrni");
//        ds.setUsername("srnidb");
//        ds.setPassword("tragra01");

//        // DB Local
        ds.setUrl("jdbc:mysql://localhost:3306/sisrni");
        ds.setUsername("root");
        ds.setPassword("");
//
        // DB Local -2
//        ds.setUrl("jdbc:mysql://localhost:3306/sisrni");
//        ds.setUsername("root");
//        ds.setPassword("root");
//        //amazon ec2
//        ds.setUrl("jdbc:mysql://52.67.109.233:3306/sisrni");
//        ds.setUsername("srnidb");
//        ds.setPassword("tragra01");
        return ds;
    }

    final Properties hibernateProperties() {
        return new Properties() {
            private static final long serialVersionUID = -2305452120L;

            {
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                setProperty("hibernate.cache.use_second_level_cache", "false");
                setProperty("hibernate.cache.use_query_cache", "false");
                setProperty("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");
                setProperty("hibernate.cglib.use_reflection_optimizer", "false");
                setProperty("hibernate.connection.release_mode", "after_transaction");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.connection.autocommit", "false");
            }
        };
    }
}
