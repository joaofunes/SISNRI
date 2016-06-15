package com.sisrni.managedbean.form;

import com.sisrni.model.SsOpciones;
import com.sisrni.security.AppUserDetails;
import com.sisrni.utils.MenuList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

public class CurrentUserSessionForm implements Serializable {

    private AppUserDetails sessionUser;
    private List<SsOpciones> options;
    private List<MenuList> menusLst;
    private MenuModel model;

    public AppUserDetails getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(AppUserDetails sessionUser) {
        this.sessionUser = sessionUser;
    }

    public List<SsOpciones> getOptions() {
        if (options == null) {
            options = new ArrayList<SsOpciones>();
        }
        return options;
    }

    public void setOptions(List<SsOpciones> options) {
        this.options = options;
    }

    public List<MenuList> getMenusLst() {
        if (menusLst == null) {
            menusLst = new ArrayList<MenuList>();
        }
        return menusLst;
    }

    public void setMenusLst(List<MenuList> menusLst) {
        this.menusLst = menusLst;
    }

    public MenuModel getModel() {
        if (menusLst == null) {
            model = new DefaultMenuModel();
        } else {
            model = new DefaultMenuModel();

            for (MenuList menu : menusLst) {
                createModel(menu, model);
            }
        }

        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    private void createModel(MenuList menu, MenuModel model) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (menu != null && model != null) {
            if (menu.getSsOpciones() != null) {
                for (SsOpciones opcion : menu.getSsOpciones()) {
                    if (opcion.getUrl() != null) {
                        if (opcion.getNombreOpcion() != null && !opcion.getNombreOpcion().trim().equalsIgnoreCase("")) {
                            DefaultMenuItem item = new DefaultMenuItem(opcion.getNombreOpcion());
                            if (opcion.getImagenOpcion() != null && !opcion.getImagenOpcion().trim().equalsIgnoreCase("")) {
                                item.setIcon("/img/menu/" + opcion.getImagenOpcion());
                            } else {
                                item.setIcon("/img/menu/default.png");
                            }
                            item.setUrl("http://" + context.getExternalContext().getRequestServerName() + ":" + context.getExternalContext().getRequestServerPort() + opcion.getUrl());
                            model.addElement(item);
                        }
                    }
                }
            }

            if (menu.getSubMenu() != null) {
                for (MenuList subMenu : menu.getSubMenu()) {
                    createModel(subMenu, model);
                }
            }
        }
    }
}
