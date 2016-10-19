package com.sisrni.managedbean.generic;

import com.sisrni.managedbean.CurrentUserSessionBean;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;

public abstract class GenericManagedBean<T, ID extends Serializable> {

    public abstract GenericService<T, ID> getService();

    private Class<T> myclass;
    private T selected;
    protected List<T> items;
    private LazyDataModel<T> lazyItems;
    private SelectItem[] activoOptionsFilter;
    private SelectItem[] activoOptions;
    private CurrentUserSessionBean currentUserSession;
    private String fechInicio;
    private String fechFinal;
    
    public abstract LazyDataModel<T> getNewLazyModel();

    public String getFechInicio() {
        return fechInicio;
    }

    public void setFechInicio(String fechInicio) {
        this.fechInicio = fechInicio;
    }

    public String getFechFinal() {
        return fechFinal;
    }

    public void setFechFinal(String fechFinal) {
        this.fechFinal = fechFinal;
    }

    protected enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    public GenericManagedBean() {
        myclass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        activoOptionsFilter = createFilterOptions();
        activoOptions = createActivoOptions();
        currentUserSession = new CurrentUserSessionBean();
    }

    public GenericManagedBean(Class<T> itemClass) {
        this.myclass = itemClass;
    }

    public LazyDataModel<T> getLazyItems() {
        return lazyItems;
    }

    public void setLazyItems(LazyDataModel<T> lazyItems) {
        this.lazyItems = lazyItems;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public List<T> getItems() {
        if (items == null) {
            items = this.getService().findAll();
        }
        return items;
    }

    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/crudbundle").getString(myclass.getSimpleName() + "Updated");
        persist(PersistAction.UPDATE, msg);
    }

    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/crudbundle").getString(myclass.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/crudbundle").getString(myclass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    protected void persist(PersistAction persistAction, String successMessage) {
         if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    this.getService().merge(selected);
                } else {
                    this.getService().delete(selected);
                }
                selected = null;
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                String msg = "";
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(ResourceBundle.getBundle("/crudbundle").getString("PersistenceErrorOccured"));
                    //JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/crudbundle").getString("PersistenceErrorOccured"));
                }
            }
        }
    }

    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = myclass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    

    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    public void loadLazyModels() {
        lazyItems = getNewLazyModel();
        
    }

    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    public SelectItem[] getActivoOptionsFilter() {
        return activoOptionsFilter;
    }

    public void setActivoOptionsFilter(SelectItem[] activoOptionsFilter) {
        this.activoOptionsFilter = activoOptionsFilter;
    }

    public SelectItem[] getActivoOptions() {
        return activoOptions;
    }

    public void setActivoOptions(SelectItem[] activoOptions) {
        this.activoOptions = activoOptions;
    }

    public CurrentUserSessionBean getCurrentUserSession() {
        return currentUserSession;
    }

    public void setCurrentUserSession(CurrentUserSessionBean currentUserSession) {
        this.currentUserSession = currentUserSession;
    }

    private SelectItem[] createFilterOptions() {

        SelectItem[] options = new SelectItem[3];
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem("SI", "Si");
        options[2] = new SelectItem("NO", "No");

        return options;
    }

    private SelectItem[] createActivoOptions() {

        SelectItem[] options = new SelectItem[2];
        options[0] = new SelectItem("SI", "Si");
        options[1] = new SelectItem("NO", "No");

        return options;
    }

    public String getUsuario() {

        if (currentUserSession != null && currentUserSession.getSessionUser() != null && currentUserSession.getSessionUser().getUsuario().getCodigoUsuario() != null && !currentUserSession.getSessionUser().getUsuario().getCodigoUsuario().trim().equalsIgnoreCase("")) {
            return currentUserSession.getSessionUser().getUsuario().getCodigoUsuario();
        }

        return null;

    }
}
