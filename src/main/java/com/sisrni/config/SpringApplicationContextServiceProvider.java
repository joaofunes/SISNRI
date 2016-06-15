package com.sisrni.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class SpringApplicationContextServiceProvider {

    private final static Log log = LogFactory.getLog(SpringApplicationContextServiceProvider.class);
    public final static String DATASOURCE_BEAN_NAME = "dataSource";
    public final static String SESSIONFACTORY_BEAN_NAME = "sessionFactory";

    public static Object getBean(String beanname) {
        ApplicationContext context = SpringApplicationContextProvider.getApplicationContext();
        return context.getBean(beanname);
    }

    public static Session getSessionFactoryCurrentSession() {
        SessionFactory sessionFactory = null;
        try {
            ApplicationContext context = SpringApplicationContextProvider.getApplicationContext();
            sessionFactory = (SessionFactory) context.getBean(SESSIONFACTORY_BEAN_NAME);
        } catch (BeansException ex) {
            log.error("ConnectionFactory > NO FUE POSIBLE OBTENER LA SESION ACTUAL DE BASE DE DATOS.", ex);
        }
        return (sessionFactory != null ? sessionFactory.getCurrentSession() : null);
    }

    public static void doInTransactionWithoutResult(TransactionCallbackWithoutResult transactionCallbackWithoutResult) {
        doInTransactionWithoutResult(SpringApplicationContextProvider.getApplicationContext(), transactionCallbackWithoutResult);
    }

    public static void doInTransactionWithoutResult(ApplicationContext context, TransactionCallbackWithoutResult transactionCallbackWithoutResult) {
        PlatformTransactionManager ptm = context.getBean(PlatformTransactionManager.class);
        TransactionTemplate tx = new TransactionTemplate(ptm);
        tx.execute(transactionCallbackWithoutResult);
    }
}
