/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.Proyecto;
import com.sisrni.pojo.rpt.RptProyectoPojo;
import com.sisrni.service.PersonaService;
import com.sisrni.service.ProyectoService;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("proyectoReportMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProyectoReportMB {

    private String reportName;
    @Autowired
    ProyectoService proyectoService;
    @Autowired
    PersonaService personaService;
    
    private static final String tipoPersona="REFERENTE EXTERNO";

    @PostConstruct
    public void init() {

    }
    public void llenarReporte() {
        List<RptProyectoPojo> list = new ArrayList<RptProyectoPojo>();

        List<Proyecto> comparativoReparacionesDos = proyectoService.findAll();
        RptProyectoPojo prueba = new RptProyectoPojo();

        for (Proyecto item : comparativoReparacionesDos) {
            prueba = new RptProyectoPojo();
            prueba.setNombre(item.getNombreProyecto());
            prueba.setObjetivo(item.getObjetivo());
            prueba.setAnioGestion(item.getAnioGestion());
            prueba.setOrgamismo(obtenerOrganismos(item.getOrganismoList()));
            prueba.setPaisCooperante(item.getIdPaisCooperante().getNombrePais());
            prueba.setContraparteExterna(obtenerPersonaExterna(item.getPersonaProyectoList(), tipoPersona));
            prueba.setBeneficiadoUES(obtenerFacultades(item.getFacultadList()));
            prueba.setMonto(item.getMontoProyecto());
            list.add(prueba);
        }

        print(list);
    }
    public String obtenerOrganismos(List<Organismo> listOrganismos){
        List<String> nombreOrganismo=new ArrayList<String>();
        for(Organismo organismo:listOrganismos){
            nombreOrganismo.add(organismo.getNombreOrganismo());
        }
        return String.join(",", nombreOrganismo);
    }
    public String obtenerPersonaExterna(List<PersonaProyecto> listPersonas, String tipoPersona){
        String nombrecompleto="";
        for(PersonaProyecto personaExterna: listPersonas){
            if(personaExterna.getIdTipoPersona().getNombreTipoPersona().equalsIgnoreCase(tipoPersona)){
               nombrecompleto= personaExterna.getPersona().getNombrePersona() +" "+ personaExterna.getPersona().getApellidoPersona();
            }
        }
        return nombrecompleto;
    }
    public String obtenerFacultades(List<Facultad> listFacultades){
        List<String> nombreFacultad=new ArrayList<String>();
        for(Facultad facultad:listFacultades){
            nombreFacultad.add(facultad.getNombreFacultad());
        }
        return String.join(",", nombreFacultad);
    }

    public void print(List<RptProyectoPojo> list) {
        try {
              ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("proyectos", "rpt_proyectos_gestionados", request);
        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptProyectoPojo>(list)));
//        reporte.addParameter("usuario", "ADM");
//        reporte.addParameter("equipox", "Equipo 1");
//        reporte.addParameter("equipoy", "Equipo 2");
        reporte.setReportInSession(request, response);
        reportName = reporte.getNombreLogico();
        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public String getBaseDir() {
        String baseDir = "/img/ues.png";
        try {
            return FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResource(baseDir)
                    .getPath();
        } catch (MalformedURLException mue) {

            return null;
        }
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

}
