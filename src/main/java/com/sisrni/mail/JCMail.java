package com.sisrni.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 
 * @author Arturo Machuca
 */
public class JCMail {

    private String from = "";//tu_correo@gmail.com
    private String password = "";//tu password: 123456 :)
    // destinatario1@hotmail.com,destinatario2@hotmail.com, destinatario_n@hotmail.com
    private InternetAddress[] addressTo;
    private String Subject = "";//titulo del mensaje
    private String MessageMail = "";//contenido del mensaje

//    private  SAXBuilder sax;
//    Document dconfig1=null;
    String SmtpHost="";
    String SmtpHostPort="";
    
    public JCMail(){
    }

    public void SEND() throws  IOException, AddressException, MessagingException
    {
        try {
            
//            sax = new SAXBuilder(false);
//            dconfig1 =sax.build("C:/mailservices/MailServicesConfig.xml");
//            Element smtphost=dconfig1.getRootElement().getChild("smtphost");
//            Element smtphostport=dconfig1.getRootElement().getChild("smtphostport");
            
            
            SmtpHost = "smtp.gmail.com";//smtphost.getValue();
            SmtpHostPort = "587";//smtphostport.getValue();
                    
            System.out.println("smtp: "+SmtpHost);
            System.out.println("smtpport: "+SmtpHostPort);
            
            Properties props = new Properties();
            props.put("mail.smtp.host", SmtpHost);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.user", "mmmmachuca@gmail.com");
            //props.put("mail.smtp.port", 587);
            props.put("mail.smtp.port", SmtpHostPort);
            //
            SMTPAuthenticator auth = new SMTPAuthenticator( getFrom(), getPassword() );
            Session session = Session.getDefaultInstance(props, auth);
            session.setDebug(false);
            //Se crea destino y origen del mensaje
            MimeMessage mimemessage = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress( getFrom() );
            mimemessage.setFrom(addressFrom);
            mimemessage.setRecipients(Message.RecipientType.TO, addressTo);
            mimemessage.setSubject( getSubject() );
            // Se crea el contenido del mensaje
            MimeBodyPart mimebodypart = new MimeBodyPart();
            mimebodypart.setText( getMessage() );
            mimebodypart.setContent( getMessage() , "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimebodypart);            
            mimemessage.setContent(multipart);            
            mimemessage.setSentDate(new Date());
            Transport.send(mimemessage);
            //JOptionPane.showMessageDialog(null, "Correo enviado");
            System.out.println("Correo enviado");
        } catch (MessagingException ex) {
            System.out.println(ex);
        }

    }
    //remitente
    public void setFrom(String mail){ this.from = mail; }
    public String getFrom(){ return this.from; }
    //Contraseña
    public void setPassword(String value){
        this.password = new String(value);
    }
    public String getPassword(){ return this.password; }
    //destinatarios
    public void setTo(String mails){
        String[] tmp =mails.split(",");
        addressTo = new InternetAddress[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                addressTo[i] = new InternetAddress(tmp[i]);
            } catch (AddressException ex) {
                System.out.println(ex);
            }
        }
    }
    public InternetAddress[] getTo(){ return this.addressTo; }
    //titulo correo
    public void setSubject(String value){ this.Subject = value; }
    public String getSubject(){ return this.Subject; }
    //contenido del mensaje
    public void setMessage(String value){ this.MessageMail = value; }
    public String getMessage(){ return this.MessageMail; }

}
