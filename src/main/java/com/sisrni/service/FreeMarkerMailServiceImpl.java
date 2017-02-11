/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.model.Beca;
import com.sisrni.pojo.rpt.PojoBeca;
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

@SuppressWarnings("deprecation")
@Service("henryMailSenderService")
public class FreeMarkerMailServiceImpl implements FreeMarkerMailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    Configuration freemarkerConfiguration;

    @Override
    public void sendEmail(Object object) {

        PojoBeca order = (PojoBeca) object;

        MimeMessagePreparator preparator = getMessagePreparator(order);

        try {
            mailSender.send(preparator);
            System.out.println("Correo ha sido enviado exitosamente");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final PojoBeca obj) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject("Registro de Beca");
                helper.setFrom("tgraduacion01@gmail.com");
                helper.setTo(obj.getCorreoBecario());

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
    }

    public String geFreeMarkerTemplateContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("fm_mailTemplate.txt"), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }
}
