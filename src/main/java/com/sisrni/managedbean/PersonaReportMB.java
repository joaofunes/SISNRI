/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.PersonaLazyModal;
import com.sisrni.model.Persona;
import com.sisrni.pojo.rpt.RptPersona;
import com.sisrni.service.PersonaService;
import com.sisrni.service.generic.GenericService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("personaReportMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PersonaReportMB {
   
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
    private String reportName;
    @PostConstruct
    public void init() {
        
    }

    
    public void llenarReporte() {
        List<RptPersona> list = new ArrayList<RptPersona>();

        List<Persona> comparativoReparacionesDos = personaService.findAll();
        RptPersona prueba = new RptPersona();

        for (Persona item : comparativoReparacionesDos) {
            prueba = new RptPersona();
            prueba.setApellido(item.getApellidoPersona());
            prueba.setNombre(item.getNombrePersona());
            prueba.setEmail(item.getEmailPersona());
            list.add(prueba);
        }

        print(list);
    }

    public void print(List<RptPersona> list) {
        try {
              ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("compgastosrep", "rpt_comparativo_gasto_reparaciones", request);
        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptPersona>(list)));
        reporte.addParameter("usuario", "ADM");
        reporte.addParameter("equipox", "Equipo 1");
        reporte.addParameter("equipoy", "Equipo 2");
        reporte.setReportInSession(request, response);
        reportName = reporte.getNombreLogico();
        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

     //extends GenericManagedBean<Persona, Integer> 
//    @Override
//    public GenericService<Persona, Integer> getService() {
//       return personaService;
//    }
//
//    @Override
//    public LazyDataModel<Persona> getNewLazyModel() {
//        return new PersonaLazyModal(personaService);
//            
//    }

}
