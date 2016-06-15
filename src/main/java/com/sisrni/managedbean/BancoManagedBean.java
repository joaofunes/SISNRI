package com.sisrni.managedbean;

//package com.siapa.managedbean;
//

//import java.util.Date;
//import java.util.HashSet;
//import java.util.ResourceBundle;
//import javax.annotation.PostConstruct;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;
//import javax.inject.Named;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import org.primefaces.context.RequestContext;
//import org.primefaces.model.LazyDataModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.context.WebApplicationContext;
//
///**
// *
// * @author marlon.andrade
// */
//@Named("bancoManagedBean")
//@Scope(WebApplicationContext.SCOPE_SESSION)
//public class BancoManagedBean extends GenericManagedBean<Banco, Integer> {
//
//    @Autowired
//    @Qualifier(value = "bancoService")
//    private BancoService bancoService;
//  
//    private String reportName;
//
//    @Override
//    public GenericService<Banco, Integer> getService() {
//        return bancoService;
//    }
//
//    @PostConstruct
//    public void init() {
//        loadLazyModels();
//    }
//
//    public BancoManagedBean() {
//    }
//
//    @Override
//    public LazyDataModel<Banco> getNewLazyModel() {
//        return new BancoLazyModel(bancoService);
//    }
//
//    public void print() {
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        HttpServletRequest request = (HttpServletRequest) context.getRequest();
//        HttpServletResponse response = (HttpServletResponse) context.getResponse();
//        Reporte reporte = new Reporte("mnt", "bancos", request);
//        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<Banco>(bancoService.findAll())));
//        reporte.setReportInSession(request, response);
//        reportName = reporte.getNombreLogico();
//        RequestContext.getCurrentInstance().
//                addCallbackParam("reportName", reportName);
//    }
//
//    public String getReportName() {
//        return reportName;
//    }
//
//    public void setReportName(String reportName) {
//        this.reportName = reportName;
//    }
//
//    @Override
//    public void saveNew(ActionEvent event) {
//       if(getUsuario()!=null){
//        String msg = ResourceBundle.getBundle("/crudbundle").getString(Banco.class.getSimpleName() + "Created");
//        getSelected().setUsuarioRegistro(getUsuario());
//        getSelected().setFechaRegistro(new Date());
//        persist(PersistAction.CREATE, msg);
//       }
//    }
//
//    @Override
//    public void save(ActionEvent event) {
//        String msg = ResourceBundle.getBundle("/crudbundle").getString(Banco.class.getSimpleName() + "Updated");
//        getSelected().setUsuarioUltimamodificacion(getUsuario());
//        getSelected().setFechaUltimamodificacion(new Date());
//        persist(PersistAction.UPDATE, msg);
//        if (!isValidationFailed()) {
//           items = null; // Invalidate list of items to trigger re-query.
//        }
//    }
//}
