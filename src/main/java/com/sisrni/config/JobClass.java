/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.config;

import com.sisrni.service.PropuestaConvenioService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author Joao
 */
public class JobClass extends QuartzJobBean {
    
      //Servicios que se usarán en la tarea. En un Job no es posible usar la funcionalidad de Spring para inyectar las dependencias.
      private PropuestaConvenioService propuestaConvenioService;
 
      @Override
      protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
 
            //Recuperamos las dependencias de servicios necesarios del contexto de Spring (porque fueron previamente definidos en spring-quartz.xml)
            this.propuestaConvenioService = (PropuestaConvenioService) jobContext.getJobDetail().getJobDataMap().get("propuestaConvenioService"); 
            
            System.out.println("***********************************************");
            System.out.println("*******************JOB*************************");
            System.out.println("***********************************************");
            // ... cosas después
 
      }
       
}
