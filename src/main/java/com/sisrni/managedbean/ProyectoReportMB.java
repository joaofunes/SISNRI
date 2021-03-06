/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.Proyecto;
import com.sisrni.pojo.rpt.RptProyectoPojo;
import com.sisrni.pojo.rpt.RptProyectosFinanciadosPojo;
import com.sisrni.pojo.rpt.RptProyectosPorPaisPojo;
import com.sisrni.service.PersonaService;
import com.sisrni.service.ProyectoService;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * @author Lillian
 */
@Named("proyectoReportMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProyectoReportMB {

    private String reportName;
    @Autowired
    ProyectoService proyectoService;
    @Autowired
    PersonaService personaService;

    private static final String tipoPersona = "REFERENTE EXTERNO";
    private String anioDesde;
    private String anioHasta;
    private int yearActual;

    @PostConstruct
    public void init() {
        yearActual = getYearOfDate(new Date());
    }

    public void llenarReporte(String formato) {
        List<RptProyectoPojo> list = new ArrayList<RptProyectoPojo>();
        Integer desdeYear = Integer.parseInt(anioDesde.trim());
        Integer hastaYear = Integer.parseInt(anioHasta.trim());
        List<Proyecto> comparativoReparacionesDos = proyectoService.getProyectosDesdeHasta(desdeYear, hastaYear);
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
            prueba.setEstado(item.getIdEstadoProyecto().getNombreEstado());
            list.add(prueba);
        }

        print(list, formato);
    }

    public String obtenerOrganismos(List<Organismo> listOrganismos) {
        List<String> nombreOrganismo = new ArrayList<String>();
        for (Organismo organismo : listOrganismos) {
            nombreOrganismo.add(organismo.getNombreOrganismo());
        }
        return String.join(",", nombreOrganismo);
    }

    public String obtenerPersonaExterna(List<PersonaProyecto> listPersonas, String tipoPersona) {
        String nombrecompleto = "";
        for (PersonaProyecto personaExterna : listPersonas) {
            if (personaExterna.getIdTipoPersona().getNombreTipoPersona().equalsIgnoreCase(tipoPersona)) {
                nombrecompleto = personaExterna.getPersona().getNombrePersona() + " " + personaExterna.getPersona().getApellidoPersona();
            }
        }
        return nombrecompleto;
    }

    public String obtenerFacultades(List<Facultad> listFacultades) {
        List<String> nombreFacultad = new ArrayList<String>();
        for (Facultad facultad : listFacultades) {
            nombreFacultad.add(facultad.getNombreFacultad());
        }
        return String.join(",", nombreFacultad);
    }

    public void print(List<RptProyectoPojo> list, String formato) {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Reporte reporte = new Reporte("proyectos", "rpt_proyectos_gestionados", request);
            reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptProyectoPojo>(list)));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("Desde", anioDesde.trim());
            reporte.addParameter("Hasta", anioHasta.trim());
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printReportesFinanciados(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            Integer hastaYear = Integer.parseInt(anioHasta.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Reporte reporte = new Reporte("proyectos", "rpt_proyectos_gestionados_por_anio", request);
            List<RptProyectosFinanciadosPojo> dataProyectosFinanciadosReportes = proyectoService.getDataProyectosFinanciadosReportes(desdeYear, hastaYear);
            reporte.setDataSource(new JRBeanCollectionDataSource(dataProyectosFinanciadosReportes));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            reporte.addParameter("hasta", anioHasta.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printReportesProyectosPais(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            Integer hastaYear = Integer.parseInt(anioHasta.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Reporte reporte = new Reporte("proyectos", "rpt_proyectos_gestionados_por_pais", request);
            List<RptProyectosPorPaisPojo> dataProyectosPorPais = proyectoService.getDataProyectosPorPais(desdeYear, hastaYear);
            reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptProyectosPorPaisPojo>(dataProyectosPorPais)));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            reporte.addParameter("hasta", anioHasta.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printReportesProyectosTotales(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            Integer hastaYear = Integer.parseInt(anioHasta.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Reporte reporte = new Reporte("proyectos", "rpt_proyectos_totales", request);
            List<RptProyectosFinanciadosPojo> dataProyectosTotales = proyectoService.getDataProyectosTotales(desdeYear, hastaYear);
            reporte.setDataSource(new JRBeanCollectionDataSource(dataProyectosTotales));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            reporte.addParameter("hasta", anioHasta.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getBaseDir(String imagen) {
        String baseDir = "/img/" + imagen;
        try {
            return FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResource(baseDir)
                    .getPath();
        } catch (MalformedURLException mue) {

            return null;
        }
    }

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getAnioDesde() {
        return anioDesde;
    }

    public void setAnioDesde(String anioDesde) {
        this.anioDesde = anioDesde;
    }

    public String getAnioHasta() {
        return anioHasta;
    }

    public void setAnioHasta(String anioHasta) {
        this.anioHasta = anioHasta;
    }

    public int getYearActual() {
        return yearActual;
    }

    public void setYearActual(int yearActual) {
        this.yearActual = yearActual;
    }

}
