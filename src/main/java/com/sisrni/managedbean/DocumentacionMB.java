/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisrni.managedbean;

import com.sisrni.pojo.rpt.DocumentosPojo;
import com.sisrni.security.AppUserDetails;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("documentacionMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class DocumentacionMB implements Serializable{
    
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;
  
    
    /**prueba/*/
    private DocumentosPojo documentosPojo;
    private List<DocumentosPojo> listadoDocumentosPojo;
    private String console;
    private Calendar unaFecha;
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final java.util.Random rand = new java.util.Random();
    final Set<String> identifiers = new HashSet<String>();
     /**prueba/*/
    
    
    @PostConstruct
    public void init() {
        try {
            user = new CurrentUserSessionBean();
            documentosPojo = new DocumentosPojo();
        } catch (Exception e) {
        }
    } 

    
    public void iniciar(){
        int i=0;
        int f=getCantidad();
        listadoDocumentosPojo=null;
        for( i=0 ; i <= f ;i++){
            documentosPojo = new DocumentosPojo();
            documentosPojo.setDocumento(getRandomBoolean());
            documentosPojo.setFechaRecibo(getFecha());
            documentosPojo.setNombreDocumento("xxxxxx xxxxxxxxxxx xxxxxxx");
            documentosPojo.setPersonaRecibe("yyyyyy yyyyyyy yyyyyyy");
            documentosPojo.setTipoDocumento("zzzz zzzzzz");
            listadoDocumentosPojo.add(documentosPojo);
        }
    }
    
    
     public Date getFecha() {
        int numero = 0;
        Random aleatorio;
        aleatorio = new Random();

        unaFecha = Calendar.getInstance();
        unaFecha.set (aleatorio.nextInt(10)+2014, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
        return unaFecha.getTime();
    }
     
     
    public String randomIdentifier() {
    StringBuilder builder = new StringBuilder();
    while(builder.toString().length() == 0) {
        int length = rand.nextInt(5)+5;
        for(int i = 0; i < length; i++)
            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        if(identifiers.contains(builder.toString())) 
            builder = new StringBuilder();
    }
    return builder.toString();
} 
     
     public static boolean getRandomBoolean() {
       return Math.random() < 0.5;
       //I tried another approaches here, still the same result
   } 
     
     public static int getCantidad(){
       return 2 + (int)(Math.random() * ((10 - 2) + 1));
     }
    
    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public Calendar getUnaFecha() {
        return unaFecha;
    }

    public void setUnaFecha(Calendar unaFecha) {
        this.unaFecha = unaFecha;
    }

    public List<DocumentosPojo> getListadoDocumentosPojo() {
        return listadoDocumentosPojo;
    }

    public void setListadoDocumentosPojo(List<DocumentosPojo> listadoDocumentosPojo) {
        this.listadoDocumentosPojo = listadoDocumentosPojo;
    }
    
}
