package com.dimesa.jasper.test;


//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import sv.gob.mh.dgii.controller.configuration.MainController;
//import com.siapa.jasper.Reporte;
//
//@Controller
//public class TestCorrReporteController extends MainController{
//
//    private final static Log log = LogFactory.getLog(TestCorrReporteController.class);
//
//    @RequestMapping(value = "/impresion")
//    public String impresion() {
//        return "report-tests/impresion";
//    }
//
//    @RequestMapping(value = "/testCorrelativos")
//    public String testCorrelativos(HttpServletRequest request,HttpServletResponse response) {
//        String ret = null;
//        Reporte reporte = new Reporte("corr", "corr_doc_autorizados_contrib_y_no informados F945", request);
//        reporte.setReportInSession(request, response);
//        ret = "redirect:/report/report?reportName=" + reporte.getNombreLogico();
//        return ret;
//    }
//}
