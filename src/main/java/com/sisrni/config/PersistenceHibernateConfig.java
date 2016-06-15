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
//        ds.setUrl("jdbc:mysql://5.56.58.10:3306/sisrni");
//        ds.setUsername("sisrni");
//        ds.setPassword("s1ac0fing3s_UES");
        //ds.setUrl("jdbc:mysql://localhost:3307/sisrni");
        ds.setUrl("jdbc:mysql://localhost:3307/dimesa");
        ds.setUsername("root");
       ds.setPassword("");
//        ds.setUsername("root");
 //       ds.setPassword("siacofinges");
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
