package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.pojo.rpt.RptMovilidadesSegunEtapaPojo;
import com.sisrni.service.MovilidadService;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Usuario
 */
@Named(value = "reporteMovilidad")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ReporteMovilidad {

    //Variables
    private int yearActual;
    private String anioDesde;
    //private String anioHasta;
    
    private String reportName;

    @Autowired
    MovilidadService movilidadService;

    @PostConstruct
    public void init() {
      yearActual = getYearOfDate(new Date());
    }
    
    
    //Generar reporte de Movilidades segun Etapa
     public void printTipoEtapa(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            
            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidadesTipoEtapa", request);
            List<RptMovilidadesSegunEtapaPojo> dataMovilidadesEtapaTipo = movilidadService.getCantidadMovilidadesSegunEtapa(desdeYear);
            
            reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptMovilidadesSegunEtapaPojo>(dataMovilidadesEtapaTipo)));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if(!formato.equalsIgnoreCase("pdf")){
               reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
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

    //Getter y Setter
    public int getYearActual() {
        return yearActual;
    }

    public void setYearActual(int yearActual) {
        this.yearActual = yearActual;
    }

   
 //   public String getAnioHasta() {
 //       return anioHasta;
 //   }

 //   public void setAnioHasta(String anioHasta) {
 //       this.anioHasta = anioHasta;
 //   }

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
    
    

}
