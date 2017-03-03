/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

/**
 *
 * @author Cortez
 */
public interface FreeMarkerMailService {

    public void sendEmail(final Object object);
    
    public void sendEmail(Object object, String subJect, String setToMail, String nameTemplate);
}
