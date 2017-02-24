/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.pojo.rpt.BecasGestionadasPojo;
import com.sisrni.pojo.rpt.RptDetalleBecasPojo;
import com.sisrni.service.BecaService;
import com.sisrni.service.ProyectoService;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
@Named("reporteBecas")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ReporteBecas {

    private int yearActual;
    private String anioDesde;
    private String anioHasta;

    private String reportName;
    @Autowired
    BecaService becaService;

    @Autowired
    ProyectoService proyectoService;

    @PostConstruct
    public void init() {
        yearActual = getYearOfDate(new Date());
    }

    public void print(String formato) {
        try {
            Boolean badyears = badYears();
            if (!badyears) {
                Integer desdeYear = Integer.parseInt(anioDesde.trim());
                Integer hastaYear = Integer.parseInt(anioHasta.trim());
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                Reporte reporte = new Reporte("beca", "rpt_becas_gestionadas_blank", request);
                List<BecasGestionadasPojo> dataBecasGestionadasReportes = becaService.getDataBecasGestionadasReportes(desdeYear, hastaYear);
                reporte.setDataSource(new JRBeanCollectionDataSource(dataBecasGestionadasReportes));
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
                proyectoService.getProyectosDesdeHasta(2010, 2017);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Parametro Desde no puede ser mayor al parametro Hasta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printPaisDestino(String formato) {
        try {
            Boolean badyears = badYears();
            if (!badyears) {
                Integer desdeYear = Integer.parseInt(anioDesde.trim());
                Integer hastaYear = Integer.parseInt(anioHasta.trim());
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                Reporte reporte = new Reporte("beca", "rpt_becas_gestionadas_paisDestino", request);
                List<BecasGestionadasPojo> dataBecasGestionadasReportes = becaService.getDataBecasGestionadasGroupPaisDestino(desdeYear, hastaYear);
                reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
                reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
                reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
                reporte.addParameter("desde", anioDesde.trim());
                reporte.addParameter("hasta", anioHasta.trim());
                if (!formato.equalsIgnoreCase("pdf")) {
                    reporte.setTipoMime(formato);
                }
                reporte.setReportInSession(request, response);
                reportName = reporte.getNombreLogico();
                RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Parametro Desde no puede ser mayor al parametro Hasta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printFacultad(String formato) {
        try {
            Boolean badyears = badYears();
            if (!badyears) {
                Integer desdeYear = Integer.parseInt(anioDesde.trim());
                Integer hastaYear = Integer.parseInt(anioHasta.trim());
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                Reporte reporte = new Reporte("beca", "rpt_becas_gestionadas_facultad", request);
                List<BecasGestionadasPojo> dataBecasGestionadasReportes = becaService.getDataBecasGestionadasGroupFacultad(desdeYear, hastaYear);
                reporte.setDataSource(new JRBeanCollectionDataSource(dataBecasGestionadasReportes));
                reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
                reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
                reporte.addParameter("desde", anioDesde.trim());
                reporte.addParameter("hasta", anioHasta.trim());
                if (!formato.equalsIgnoreCase("pdf")) {
                    reporte.setTipoMime(formato);
                }

                reporte.setReportInSession(request, response);
                reportName = reporte.getNombreLogico();
                RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Parametro Desde no puede ser mayor al parametro Hasta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printDetalleBecas(String formato) {
        try {
            Boolean badyears = badYears();
            if (!badyears) {
                Integer desdeYear = Integer.parseInt(anioDesde.trim());
                Integer hastaYear = Integer.parseInt(anioHasta.trim());
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                Reporte reporte = new Reporte("beca", "rpt_becas_gestionadas_detalle", request);
                List<RptDetalleBecasPojo> dataBecasGestionadasReportes = becaService.getDetalleBecas(desdeYear, hastaYear);
                reporte.setDataSource(new JRBeanCollectionDataSource(dataBecasGestionadasReportes));
                reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
                reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
                reporte.addParameter("desde", anioDesde.trim());
                reporte.addParameter("hasta", anioHasta.trim());
                if (!formato.equalsIgnoreCase("pdf")) {
                    reporte.setTipoMime(formato);
                }
                reporte.setReportInSession(request, response);
                reportName = reporte.getNombreLogico();
                RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Parametro Desde no puede ser mayor al parametro Hasta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printOrganismos(String formato) {
        try {
            Boolean badyears = badYears();
            if (!badyears) {
                Integer desdeYear = Integer.parseInt(anioDesde.trim());
                Integer hastaYear = Integer.parseInt(anioHasta.trim());
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                Reporte reporte = new Reporte("beca", "rpt_becas_gestionadas_organismos", request);
                List<BecasGestionadasPojo> dataBecasGestionadasReportes = becaService.getDataBecasGestionadasGroupOrganismos(desdeYear, hastaYear);
                reporte.setDataSource(new JRBeanCollectionDataSource(dataBecasGestionadasReportes));
                reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
                reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
                reporte.addParameter("desde", anioDesde.trim());
                reporte.addParameter("hasta", anioHasta.trim());
                if (!formato.equalsIgnoreCase("pdf")) {
                    reporte.setTipoMime(formato);
                }

                reporte.setReportInSession(request, response);
                reportName = reporte.getNombreLogico();
                RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Parametro Desde no puede ser mayor al parametro Hasta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean badYears() {
        if (Integer.parseInt(anioDesde.trim()) > Integer.parseInt(anioHasta.trim())) {
            return true;
        } else {
            return false;
        }
    }

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    public int getYearActual() {
        return yearActual;
    }

    public void setYearActual(int yearActual) {
        this.yearActual = yearActual;
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

}
