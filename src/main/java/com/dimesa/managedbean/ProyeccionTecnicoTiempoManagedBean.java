/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean;

import com.dimesa.jasper.Reporte;
import com.dimesa.managedbean.form.CurrentUserSessionForm;
import com.dimesa.managedbean.generic.GenericManagedBean;
import com.dimesa.managedbean.lazymodel.EmpleadoLazyModel;
import com.dimesa.model.Empleado;
import com.dimesa.pojo.rpt.RptProyeccionTecnicoTiempo;
import com.dimesa.service.EmpleadoService;
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
@Named("proyeccionTecnicoTiempoManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProyeccionTecnicoTiempoManagedBean extends GenericManagedBean<Empleado, Integer> {

    @Autowired
    @Qualifier(value = "empleadoService")
    private EmpleadoService empleadoService;

    private List<Empleado> empleadoList;
    private Empleado empleado;
    private String tecnico;

    private Date date1;
    private Date date2;
    private boolean value1;
    private boolean value2;
    private boolean value3;
    private String reportName;

    private Date dateFinal = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    private String fecha;

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    public ProyeccionTecnicoTiempoManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }

    @PostConstruct
    public void init() {
        empleadoList = new ArrayList<Empleado>();
        empleadoList = empleadoService.getTecnicosExterno();

    }

    public void click() {

        if (getTecnico() == null || getTecnico().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccionar Area Hospitalaria"));
        } else if (getDate1() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Inicial Vacia."));
        } else if (getDate2() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin Vacia."));
        } else if (getDate2().before(getDate1())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin es Menor que Fecha Inicio."));
        } else if (getDate2().equals(getDate1())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fechas No Son Iguales."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Procesasdo Reporte."));
            print();
        }
    }

    public void print() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        List<RptProyeccionTecnicoTiempo> list = new ArrayList<RptProyeccionTecnicoTiempo>();
        for (int i = 0; i < 100; i++) {
            RptProyeccionTecnicoTiempo prueba = new RptProyeccionTecnicoTiempo();
            //     prueba.setEquipox(12.2);
            //     prueba.setEquipoy(Double.NaN);
            list.add(prueba);
        }
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("vidautil", "rpt_comparativo_vida_util", request);

        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptProyeccionTecnicoTiempo>(list)));
        reporte.addParameter("usuario", user.getSessionUser().getUsername());

        reporte.setReportInSession(request, response);
        reportName = reporte.getNombreLogico();
        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
    }

    @Override
    public GenericService<Empleado, Integer> getService() {
        return empleadoService;
    }

    @Override
    public LazyDataModel<Empleado> getNewLazyModel() {
        return new EmpleadoLazyModel(empleadoService);
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
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

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public boolean isValue2() {
        return value2;
    }

    public void setValue2(boolean value2) {
        this.value2 = value2;
    }

    public boolean isValue3() {
        return value3;
    }

    public void setValue3(boolean value3) {
        this.value3 = value3;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(Date dateFinal) {
        this.dateFinal = dateFinal;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public String getFecha() {
        fecha = formatter.format(dateFinal);
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
