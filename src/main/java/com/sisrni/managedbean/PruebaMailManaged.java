///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sisrni.managedbean;
//
//import com.sisrni.model.Beca;
//import com.sisrni.pojo.rpt.PojoBeca;
//import com.sisrni.service.BecaService;
//import java.util.List;
//import javax.inject.Named;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.context.WebApplicationContext;
//
///**
// *
// * @author Cortez
// */
//@Named("pruebaMailManaged")
//@Scope(WebApplicationContext.SCOPE_SESSION)
//public class PruebaMailManaged {
//
//    /**
//     * Creates a new instance of PruebaMailManaged
//     */
//    @Autowired
//    BecaService becaService;
//
//    public PruebaMailManaged() {
//    }
//
//    public void sendMail() {
//        PojoBeca beca = becaService.getBecas(2).get(0);
//        becaService.sendOrderConfirmation(beca);
//
//    }
//}
