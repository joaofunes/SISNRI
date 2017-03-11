/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("deprecation")
@Service("henryMailSenderService")
public class FreeMarkerMailServiceImpl implements FreeMarkerMailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    Configuration freemarkerConfiguration;

    private String subJect;
    private String setToMail;
    private String nameTemplate;

    @Override
    public void sendEmail(Object object) {

        try {
            
            MimeMessagePreparator preparator = getMessagePreparator(object);
            
            try {
                mailSender.send(preparator);
                System.out.println("Correo ha sido enviado exitosamente");
            } catch (MailException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (Exception ex) {
            Logger.getLogger(FreeMarkerMailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void sendEmail(Object object, String subJect, String setToMail, String nameTemplate) {
        try {           
            setSubJect(subJect);
            setSetToMail(setToMail);
            setNameTemplate(nameTemplate);
            MimeMessagePreparator preparator = getMessagePreparator(object);
            mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(FreeMarkerMailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void sendEmailMap(Map map) {
         try {           
            setSubJect((String) map.get("subJect"));
            setSetToMail((String) map.get("setToMail"));
            setNameTemplate((String) map.get("nameTemplate"));
            MimeMessagePreparator preparator = getMessagePreparator(map);
            mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(FreeMarkerMailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MimeMessagePreparator getMessagePreparator(final Object obj) throws Exception {

        try {

            MimeMessagePreparator preparator = new MimeMessagePreparator() {

                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                    helper.setSubject(getSubJect());
                    //helper.setFrom("tgraduacion01@gmail.com" );
                    helper.setTo(getSetToMail());
                    helper.setSentDate(new Date());

                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("obj", obj);

                    String text = geFreeMarkerTemplateContent(model);
                    System.out.println("Contenido de plantilla : " + text);

                    // use the true flag to indicate you need a multipart message
                    helper.setText(text, true);

                //Additionally, let's add a resource as an attachment as well.
                    //helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
                }
            };
            return preparator;
        } catch (Exception ex) {
            throw new Exception("Error class  FreeMarkerMailServiceImpl - getMessagePreparator()\n" + ex.getMessage(), ex.getCause());
        }

    }

    public String geFreeMarkerTemplateContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(getNameTemplate()), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }

    public String getSubJect() {
        return subJect;
    }

    public void setSubJect(String subJect) {
        this.subJect = subJect;
    }

    public String getSetToMail() {
        return setToMail;
    }

    public void setSetToMail(String setToMail) {
        this.setToMail = setToMail;
    }

    public String getNameTemplate() {
        return nameTemplate;
    }

    public void setNameTemplate(String nameTemplate) {
        this.nameTemplate = nameTemplate;
    }

}
