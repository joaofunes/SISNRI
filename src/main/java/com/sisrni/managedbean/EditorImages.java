/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Cortez
 */
@Named(value = "editorImages")
@SessionScoped
public class EditorImages implements Serializable {

    /**
     * Creates a new instance of EditorImages
     */
    String text;

    public EditorImages() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
