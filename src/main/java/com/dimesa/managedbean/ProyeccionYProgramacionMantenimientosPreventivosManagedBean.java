/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean;

import com.dimesa.jasper.Reporte;
import com.dimesa.managedbean.form.CurrentUserSessionForm;
import com.dimesa.managedbean.generic.GenericManagedBean;
import com.dimesa.managedbean.lazymodel.ProyeccionEfectividadReparacionTiempoEquipoLazyModel;
import com.dimesa.model.Equipo;
import com.dimesa.pojo.rpt.RptProyeccionYProgramacionMantenimientosPreventivos;
import com.dimesa.service.EquipoService;
import com.dimesa.service.generic.GenericService;
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
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author HDEZ
 */
@Named("proyeccionYProgramacionMantenimientosPreventivosManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProyeccionYProgramacionMantenimientosPreventivosManagedBean extends GenericManagedBean<Equipo, Integer> {

    @Autowired
    @Qualifier(value = "equipoService")
    private EquipoService equipoService;
    private List<Equipo> equipoList;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private String fecha;
    private Date date3 = new Date();
    private String reportName;
    private String equipox;

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    public ProyeccionYProgramacionMantenimientosPreventivosManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }

    @PostConstruct
    public void init() {
        equipoList = new ArrayList<Equipo>();
        equipoList = equipoService.findAll();
    }

    @Override
    public GenericService<Equipo, Integer> getService() {
        return equipoService;
    }

    @Override
    public LazyDataModel<Equipo> getNewLazyModel() {
        return new ProyeccionEfectividadReparacionTiempoEquipoLazyModel(equipoService);
    }

    public void click() {

        if (getEquipox().equals(null) || getEquipox().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione Equipo ."));
        } else {
            print();
        }

    }

    public void print() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        List<RptProyeccionYProgramacionMantenimientosPreventivos> list = new ArrayList<RptProyeccionYProgramacionMantenimientosPreventivos>();
        for (int i = 0; i < 100; i++) {
            RptProyeccionYProgramacionMantenimientosPreventivos prueba = new RptProyeccionYProgramacionMantenimientosPreventivos();
            //     prueba.setEquipox(12.2);
            //     prueba.setEquipoy(Double.NaN);
            list.add(prueba);
        }

        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("compgastosrep", "rpt_comparativo_gasto_reparaciones", request);

        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptProyeccionYProgramacionMantenimientosPreventivos>(list)));
//        reporte.addParameter("fechaInicial", formatter.format(date1));
//        reporte.addParameter("fechaFinal", formatter.format(date2));
        reporte.addParameter("usuario", user.getSessionUser().getUsername());

        reporte.setReportInSession(request, response);
        reportName = reporte.getNombreLogico();
        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
    }

    public EquipoService getEquipoService() {
        return equipoService;
    }

    public void setEquipoService(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getEquipox() {
        return equipox;
    }

    public void setEquipox(String equipox) {
        this.equipox = equipox;
    }

}
