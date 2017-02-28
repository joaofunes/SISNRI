/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.pojo.rpt.PojoBeca;
import com.sisrni.service.BecaService;
import com.sisrni.service.FreeMarkerMailService;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Cortez
 */
@Named("pruebaMailManaged")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PruebaMailManaged {

    @Autowired
    BecaService becaService;

    @Autowired
    FreeMarkerMailService mailService;

    public PruebaMailManaged() {
    }

    public void preSendMail() {
        PojoBeca beca = becaService.getBecas(1).get(0);
        enviarConfirmacion(beca);
    }

    public void enviarConfirmacion(PojoBeca beca) {
        mailService.sendEmail(beca);
    }
}
