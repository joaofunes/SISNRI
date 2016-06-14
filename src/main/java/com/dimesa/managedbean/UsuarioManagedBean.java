package com.dimesa.managedbean;

import com.dimesa.managedbean.generic.GenericManagedBean;
import com.dimesa.managedbean.lazymodel.UsuarioLazyModel;
import com.dimesa.model.SsUsuarios;
import com.dimesa.service.SsUsuariosService;
import com.dimesa.service.generic.GenericService;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Named("usuarioManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UsuarioManagedBean extends GenericManagedBean<SsUsuarios, Integer> {

    @Autowired
    @Qualifier(value = "ssUsuariosService")
    private SsUsuariosService ssUsuarioService;

    
    @PostConstruct
    public void init() {
        loadLazyModels();
    }

    @Override
    public GenericService<SsUsuarios, Integer> getService() {
        return ssUsuarioService;
    }

    @Override
    public LazyDataModel<SsUsuarios> getNewLazyModel() {
        return new UsuarioLazyModel(ssUsuarioService);
    }
    
    @Override
    public void saveNew(ActionEvent event) {
       if(getUsuario()!=null){
        String msg = ResourceBundle.getBundle("/crudbundle").getString(SsUsuarios.class.getSimpleName() + "Usuario creado con exito");
        getSelected().setUsuarioRegistro(getUsuario());
        getSelected().setFechaRegistro(new Date());
        persist(PersistAction.CREATE, msg);
       }
    }

    @Override
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/crudbundle").getString(SsUsuarios.class.getSimpleName() + "Usuario actualizado con exito");
        getSelected().setUsuarioUltimamodificacion(getUsuario());
        getSelected().setFechaUltimamodificacion(new Date());
        persist(PersistAction.UPDATE, msg);
        if (!isValidationFailed()) {
           items = null; // Invalidate list of items to trigger re-query.
        }
    }

}
