package com.sisrni.managedbean;

import com.sisrni.managedbean.form.CurrentUserSessionForm;
import com.sisrni.model.SsMenus;
import com.sisrni.model.SsOpciones;
import com.sisrni.model.SsRoles;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.SsMenusService;
import com.sisrni.service.SsOpcionesService;
import com.sisrni.service.SsRolesService;
import com.sisrni.utils.MenuList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

@Named("currentUserSessionBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CurrentUserSessionBean implements Serializable{
    
    private static long serialVersionUID = 1113799434508676095L;
    private final static Log log = LogFactory.getLog(CurrentUserSessionBean.class);

    @Autowired
    private SsOpcionesService ssOpcionesService;
    @Autowired
    private SsMenusService ssMenusService;
    @Autowired
    private SsRolesService ssRolesService;
            
    private CurrentUserSessionForm currentUserSessionForm;

    public CurrentUserSessionBean() {
    }

    public AppUserDetails getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (getForm().getSessionUser() == null) {
            try {
                getForm().setSessionUser((AppUserDetails) authentication.getPrincipal());
            } catch (Exception e) {
                log.error("Could not retrieve session user. ", e);
            }
        }
        return getForm().getSessionUser();
    }

    private List<SsOpciones> obtenerMenuOpcion(SsRoles rol, SsMenus menu) {
        List<SsOpciones> opt = this.ssOpcionesService.getOpcionesByMenuRol(rol, menu);
        getForm().getOptions().addAll(opt);
        return opt;

    }

    public void calculateMenu() {
        if (getForm().getOptions() == null || getForm().getOptions().isEmpty()) {
            AppUserDetails usuario = getSessionUser();
            if (usuario != null && usuario.getUsuario() != null) {
                getForm().setMenusLst(new ArrayList<MenuList>());
                for (SsRoles rol : usuario.getUsuario().getSsRolesList()) {
                    List<SsMenus> mns = ssMenusService.getMenusByrol(rol);
                    
                    Collections.sort(mns, new Comparator<SsMenus>() {
                        @Override
                        public int compare(SsMenus lhs, SsMenus rhs) {
                            return lhs.getOrdenMenu(). compareTo(rhs.getOrdenMenu());
                        }
                    });
                    
                    getForm().setMenusLst(MenuList.GenerarMenu(mns));
                    for (MenuList menu : getForm().getMenusLst()) {
                        List<SsOpciones> opcionesMenu = obtenerMenuOpcion(rol, menu.getSsMenu());
                        menu.setSsOpciones(opcionesMenu);
                        for (MenuList subMenu : menu.getSubMenu()) {
                            List<SsOpciones> opcionesSubMenu = obtenerMenuOpcion(rol, subMenu.getSsMenu());
                            subMenu.setSsOpciones(opcionesSubMenu);
                        }
                    }
                }
            }
        }
//        return getForm().getOptions();
    }
    
    

    public CurrentUserSessionForm getForm() {
        if (currentUserSessionForm == null) {
            currentUserSessionForm = new CurrentUserSessionForm();
        }
        return currentUserSessionForm;
    }
          
}
