/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

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

    public void uploadListener(FileUploadEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        String fileName = FilenameUtils.getName(event.getFile().getFileName());
        String fileNamePrefix = FilenameUtils.getBaseName(fileName) + "_";
        String fileNameSuffix = "." + FilenameUtils.getExtension(fileName);

        File uploadFolder = new File("/var/webapp/uploads");

        try {
            File result = File.createTempFile(fileNamePrefix, fileNameSuffix, uploadFolder);

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[1024];
            int bulk;

            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            String value = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("editor_input");
            setText(value + "<img src=\"/rais/uploads/" + result.getName() + "\" />");

            RequestContext.getCurrentInstance().update("editor_input");

            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }
}
