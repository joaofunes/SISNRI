/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.ProgramaBecaLazyModel;
import com.sisrni.model.ProgramaBeca;
import com.sisrni.service.ProgramaBecaService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis
 */
@Named("programaBecaMB")
@ViewScoped
public class ProgramaBecaMB extends GenericManagedBean<ProgramaBeca, Integer> {

    /**
     * para errores
     */
    private final static Log log = LogFactory.getLog(ProgramaBecaMB.class);

    /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "programaBecaService")
    private ProgramaBecaService programaBecaService;

    /**
     * listas a utulizar
     */
    private ProgramaBeca programaBeca;
    private ProgramaBeca delProgramaBeca;
    private List<ProgramaBeca> listadoProgramaBeca;
    private boolean actualizar;

    /**
     * implementacion de GenericManagedBen
     *
     */
    @Override
    public GenericService<ProgramaBeca, Integer> getService() {
        return programaBecaService;
    }

    @Override
    public LazyDataModel<ProgramaBeca> getNewLazyModel() {
        return new ProgramaBecaLazyModel(programaBecaService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarProgramaBeca();
    }

    private void cargarProgramaBeca() {
        try {
            actualizar = false;
            programaBeca = new ProgramaBeca();
            listadoProgramaBeca = programaBecaService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

    /**
     * Metodo para almacenar registro en Tipo Persona
     *
     */
    public void guardarProgramaBeca() {
        String msg = "Programa de Beca Almacenado Exitosamente!";
        try {
            programaBecaService.save(programaBeca);
            cargarProgramaBeca();
            RequestContext.getCurrentInstance().update(":formPrincipal");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", msg));

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar Programa de Beca");
            e.printStackTrace();
        }
        cargarProgramaBeca();
    }

    /**
     * Metodo para Actualizar registro en Tipo Persona
     *
     */
    public void updateProgramaBeca() {
        String msg = "Programa de Beca Actualizado Exitosamente!";
        try {
            programaBecaService.merge(programaBeca);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar = false;
            cancelarProgramaBeca();
            cargarProgramaBeca();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar Programa de Beca");
        }
        cargarProgramaBeca();
    }

    /**
     * metodo para precarga registro a actualizar
     *
     * @param programaBeca
     */
    public void preUpdate(ProgramaBeca programaBeca) {
        try {
            this.programaBeca = programaBeca;
            actualizar = true;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }

    /**
     * Metodo para eliminar registro en Tipo Persona
     *
     */
    public void deleteProgramaBeca() {
        String msg = "Programa de Beca Eliminado Exitosamente!";
        try {
            programaBecaService.delete(this.delProgramaBeca);
            listadoProgramaBeca = programaBecaService.getAllByIdDesc();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Programa de Beca"));
        } finally {
            actualizar = false;
            delProgramaBeca = new ProgramaBeca();
        }

    }

    /**
     * Metodo para precargar eliminacion de registro tipo persona
     *
     * @param programaBeca
     */
    public void preDeleteProgramaBeca(ProgramaBeca programaBeca) {
        try {
            this.delProgramaBeca = programaBeca;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').show();");
        } catch (Exception e) {
        }
    }

    /**
     * Metodo para eliminar registro en Tipo Persona
     *
     */
    public void cancelarProgramaBeca() {
        String msg = "Programa de Beca cancelado!";
        try {
            programaBeca = null;
            programaBeca = new ProgramaBeca();
            RequestContext.getCurrentInstance().reset(":formAdmin");
            if (actualizar) {
                JsfUtil.addSuccessMessage(msg);
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro de Programa de Beca");
        }
        cargarProgramaBeca();
    }

    public List<ProgramaBeca> listaProgramas() {
        listadoProgramaBeca = programaBecaService.getAllByNameAsc();
        ProgramaBeca tipoProyectoNew1 = new ProgramaBeca();
        List<ProgramaBeca> copy = new ArrayList<ProgramaBeca>();
        for (ProgramaBeca tipoProyectoNew : listadoProgramaBeca) {
            if (!tipoProyectoNew.getNombrePrograma().equalsIgnoreCase("Agregar Nuevo")) {
                copy.add(tipoProyectoNew);
            } else {
                tipoProyectoNew1 = tipoProyectoNew;
            }
        }
        copy.add(tipoProyectoNew1);
        listadoProgramaBeca.clear();
        return listadoProgramaBeca = copy;
    }

    public ProgramaBeca getProgramaBeca() {
        return programaBeca;
    }

    public void setProgramaBeca(ProgramaBeca programaBeca) {
        this.programaBeca = programaBeca;
    }

    public List<ProgramaBeca> getListadoProgramaBeca() {
        return listadoProgramaBeca;
    }

    public void setListadoProgramaBeca(List<ProgramaBeca> listadoProgramaBeca) {
        this.listadoProgramaBeca = listadoProgramaBeca;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public ProgramaBeca getDelProgramaBeca() {
        return delProgramaBeca;
    }

    public void setDelProgramaBeca(ProgramaBeca delProgramaBeca) {
        this.delProgramaBeca = delProgramaBeca;
    }
}
