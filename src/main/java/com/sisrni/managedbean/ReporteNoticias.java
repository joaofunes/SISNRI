/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.model.CategoriaNoticia;
import com.sisrni.model.Noticia;
import com.sisrni.pojo.rpt.RptNoticiasPojo;
import com.sisrni.service.CategoriaNoticiaService;
import com.sisrni.service.NoticiaService;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
@Named("reporteNoticias")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ReporteNoticias {

    private Date desde;
    private Date hasta;
    private Date currentDate = new Date();
    private String reportName;

    private List<CategoriaNoticia> categoriaList;
    private CategoriaNoticia categoriaSelected;

    @Autowired
    NoticiaService noticiaService;

    @Autowired
    CategoriaNoticiaService categoriaNoticiaService;

    @PostConstruct
    public void init() {
        desde = new Date();
        hasta = new Date();
        categoriaList = categoriaNoticiaService.findAll();
        categoriaSelected = new CategoriaNoticia();

    }

    public void obtenerDatos(String formato) {
        List<String> categoriaList = categoriaNoticiaService.getCategoriaNoticiaName();
        List<RptNoticiasPojo> list = new ArrayList<RptNoticiasPojo>();
        RptNoticiasPojo aux;
        for (String cat : categoriaList) {
            aux = new RptNoticiasPojo();
            aux.setCategoria(cat);
            aux.setCantidad(noticiaService.getCountNoticiasByCat(cat, desde, hasta));
            list.add(aux);
        }
        Collections.sort(list, new Comparator<RptNoticiasPojo>() {
            @Override
            public int compare(RptNoticiasPojo p1, RptNoticiasPojo p2) {
                return p1.getCategoria().compareTo(p2.getCategoria());
            }
        });
        print(list, formato);
    }

    public void print(List<RptNoticiasPojo> list, String formato) {
        try {

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Reporte reporte = new Reporte("noticias", "rpt_resumenNoticias", request);
            reporte.setDataSource(new JRBeanCollectionDataSource(list));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", desde);
            reporte.addParameter("hasta", hasta);
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

    public void printDetalle(String formato) {
        try {
            String categoria = "";
            if (categoriaSelected.getIdCategoria() != 0) {
                CategoriaNoticia findById = categoriaNoticiaService.findById(categoriaSelected.getIdCategoria());
                categoria = findById.getCategoriaNoticia();
            } else {
                categoria = "Todas";
            }

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Reporte reporte = new Reporte("noticias", "rpt_detalleByCategoria", request);
            List<Noticia> list = noticiaService.getNoticiasDetalle(categoriaSelected.getIdCategoria(), this.desde, this.hasta);
            reporte.setDataSource(new JRBeanCollectionDataSource(list));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", desde);
            reporte.addParameter("hasta", hasta);
            reporte.addParameter("categoria", categoria);
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public CategoriaNoticia getCategoriaSelected() {
        return categoriaSelected;
    }

    public void setCategoriaSelected(CategoriaNoticia categoriaSelected) {
        this.categoriaSelected = categoriaSelected;
    }

    public List<CategoriaNoticia> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<CategoriaNoticia> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

}
