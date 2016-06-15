package com.sisrni.utils;

import com.sisrni.model.SsMenus;
import com.sisrni.model.SsOpciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase auxliar para construir menu de app
 *
 */
public class MenuList implements Serializable {

    private SsMenus ssMenu;
    private List<SsOpciones> SsOpciones;
    private List<MenuList> SubMenu;

    public MenuList(SsMenus ssMenu, List<MenuList> submenu) {
        this.ssMenu = ssMenu;
        this.SubMenu = submenu;
    }

    public static List<MenuList> GenerarMenu(List<SsMenus> ssMenusList) {
        List<MenuList> menuList = new ArrayList<MenuList>();
        for (SsMenus ssMenu : ssMenusList) {
            //null indica que se trata de un nodo padre
            if (ssMenu.getSsIdMenu() == null) {

                menuList.add(new MenuList(ssMenu, GenerarSubMenu(ssMenusList, ssMenu)));
            }

        }
        return menuList;
    }

    public static List<MenuList> GenerarSubMenu(List<SsMenus> ssMenusList, SsMenus ssMenu) {
        List<MenuList> menuList = new ArrayList<MenuList>();
        for (SsMenus currentSsMenu : ssMenusList) {
            //la igualdad indica que objeto es hijo de sObj
           if (currentSsMenu.getSsIdMenu() != null ) {
                if (currentSsMenu.getSsIdMenu().getIdMenu() == ssMenu.getIdMenu()) {
                    menuList.add(new MenuList(currentSsMenu, GenerarSubMenu(ssMenusList, currentSsMenu)));
                }
            }
        }
        return menuList;
    }

    public SsMenus getSsMenu() {
        return ssMenu;
    }

    public void setSsMenu(SsMenus ssMenu) {
        this.ssMenu = ssMenu;
    }

    public List<MenuList> getSubMenu() {
        return SubMenu;
    }

    public void setSubMenu(List<MenuList> SubMenu) {
        this.SubMenu = SubMenu;
    }

    public List<SsOpciones> getSsOpciones() {
        return SsOpciones;
    }

    public void setSsOpciones(List<SsOpciones> SsOpciones) {
        this.SsOpciones = SsOpciones;
    }

}
