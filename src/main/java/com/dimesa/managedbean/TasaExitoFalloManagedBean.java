/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean;

import com.dimesa.jasper.Reporte;
import com.dimesa.managedbean.form.CurrentUserSessionForm;

import com.dimesa.model.Evento;
import com.dimesa.pojo.rpt.RptTasaExitoFalloReparaciones;
import com.dimesa.service.EventoService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import net.sf.jasperreports.view.JasperViewer;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author RAUL
 */
@Named("tasaExitoFalloManagedBean")/*aqui la primera letra es en minuscula*/
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TasaExitoFalloManagedBean {

    @Autowired
    @Qualifier(value = "eventoService")
    private EventoService eventoService;

    private Evento evento;/*area hospitalaria*/

    private List<Evento> eventoList;/*area hospitalaria*/

    private String fechaInicio;
    private String fechaFinal;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private String fecha;
    private Date date1;/*fecaha inicial seleccionada*/
    private Date date2;/*fecaha final seleccionada*/
    private Date date3 = new Date();/*fecha solo sirve para pintarla en la principal*/


    private String area;
    private String reportName;

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;
    
    public TasaExitoFalloManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }
    
    @PostConstruct
    public void init() {

        eventoList = new ArrayList<Evento>();
        eventoList = eventoService.findAll();/*llenado area hospitalaria utilizo findALL del dao generico
         porque no necesito una consulta espefica.... si necesitara una espeficica la haria dentro de EventoDao */

    }

    public void click() {

        if (getArea() == null || getArea().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccionar Area Hospitalaria"));
        } else if (getDate1() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Inicial Vacia."));
        } else if (getDate2() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin Vacia."));
        } else if (getDate2().before(getDate1())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin es Menor que Fecha Inicio."));
        } else if (getDate2().equals(getDate1())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin es Menor que Fecha Inicio."));
        } else if (area.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccionado Area."));
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Procesado Reporte."));
            print();
        }

    }

    public void print() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        List<RptTasaExitoFalloReparaciones> list = new ArrayList<RptTasaExitoFalloReparaciones>();
        for (int i = 0; i < 100; i++) {
            RptTasaExitoFalloReparaciones prueba = new RptTasaExitoFalloReparaciones("10-10-1992", "12-12-2015", "CIPediatrico");
            list.add(prueba);
        }

        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("tasaexitofallorep", "rpt_tasaExito/fallo", request);

        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptTasaExitoFalloReparaciones>(list)));
        reporte.setReportInSession(request, response);
        reporte.addParameter("usuario", user.getSessionUser().getUsername());
        reportName = reporte.getNombreLogico();

        JasperViewer.viewReport(reporte.getJasperPrint());/*quitar si funciona*/

        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
    }

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public String getFecha() {
        fecha = formatter.format(date3);
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
