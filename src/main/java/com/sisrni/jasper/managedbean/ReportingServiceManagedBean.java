package com.sisrni.jasper.managedbean;

import com.sisrni.jasper.service.ReportingService;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Named("reportingServiceManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ReportingServiceManagedBean {

    @Autowired
    private ReportingService reportingService;

    public void report() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        reportingService.executeReport(request, response);
    }

    public void report(String reportName) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        request.setAttribute("reportName", reportName);
        reportingService.executeReport(request, response);
    }

}
