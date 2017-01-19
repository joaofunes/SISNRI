/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.model.Beca;
import com.sisrni.pojo.rpt.BecasGestionadasPojo;
import com.sisrni.service.BecaService;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
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

    private String reportName;
    @Autowired
    BecaService becaService;

    @PostConstruct
    public void init() {

    }

    public void print() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Reporte reporte = new Reporte("beca", "rpt_becas_gestionadas_blank", request);
            List<BecasGestionadasPojo> dataBecasGestionadasReportes = becaService.getDataBecasGestionadasReportes();
            reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            String pathImage= getBaseDir();
            reporte.addParameter("uesImageUrl",pathImage);
            //reporte.addParameter("srniImageUrl", "C:\\Users\\Cortez\\Desktop\\TESIS\\ues.png");
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
