package com.sisrni.managedbean;

import com.sisrni.managedbean.form.CurrentUserSessionForm;
import com.sisrni.model.SsMenus;
import com.sisrni.model.SsOpciones;
import com.sisrni.model.SsRoles;
import com.sisrni.service.SsMenusService;
import com.sisrni.service.SsOpcionesService;
import com.sisrni.service.SsRolesService;
import com.sisrni.utils.MenuList;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 */
//@ManagedBean
//@SessionScoped
@Named("loginBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SsOpcionesService ssOpcionesService;
    @Autowired
    private SsMenusService ssMenusService;
    @Autowired
    private SsRolesService ssRolesService;

    private CurrentUserSessionForm currentUserSessionForm;
    
    private String ROL="ROL_PUBLICO";

    @PostConstruct
    public void init() {
        try {

            calculateMenuTest();

        } catch (Exception e) {
        }
    }

    public void calculateMenuTest() {
        if (getForm().getOptions() == null || getForm().getOptions().isEmpty()) {
            SsRoles rol = ssRolesService.getRolByName(ROL);
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

    public CurrentUserSessionForm getForm() {
        if (currentUserSessionForm == null) {
            currentUserSessionForm = new CurrentUserSessionForm();
        }
        return currentUserSessionForm;
    }

    private List<SsOpciones> obtenerMenuOpcion(SsRoles rol, SsMenus menu) {
        List<SsOpciones> opt = this.ssOpcionesService.getOpcionesByMenuRol(rol, menu);
        getForm().getOptions().addAll(opt);
        return opt;

    }
}
