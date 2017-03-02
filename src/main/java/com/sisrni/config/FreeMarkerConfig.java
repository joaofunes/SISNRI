/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.config;

import com.sisrni.model.Parametros;
import com.sisrni.service.ParametrosService;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 *
 * @author Cortez
 */
@Configuration
public class FreeMarkerConfig {
    
    @Autowired
    @Qualifier(value = "parametrosService")
    private ParametrosService parametrosService;

    //configuracion de datos del smtp
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Parametros parametrosMail = parametrosService.getParametrosMail();
        
        
        //para gmail.
        mailSender.setHost(parametrosMail.getSmtp());
        mailSender.setPort(parametrosMail.getPuerto());
        mailSender.setUsername(parametrosMail.getCuentaCorreo());
        mailSender.setPassword("tragra01");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    //configura el path a la carpeta de las plantillas
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/WEB-INF/freemarker/");
        return bean;
    }

}
