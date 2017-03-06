/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Cortez
 */
@Named(value = "globalCounterView")
@ApplicationScoped
public  class GlobalCounterView {

    /**
     * Creates a new instance of GlobalCounterView
     */
    public GlobalCounterView() {
    }
    private volatile int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

public void increment(Integer conteo) {
//    public void increment() {
   count = conteo;
//        count++;
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/counter", String.valueOf(count));
    }

}
