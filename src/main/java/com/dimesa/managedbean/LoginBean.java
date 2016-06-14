package com.dimesas.managedbean;


//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.context.WebApplicationContext;
//
///**
// * Login bean.
// *
// * @author ALVARADO
// */
////@ManagedBean
////@SessionScoped
//@Named("loginBean")
//@Scope(WebApplicationContext.SCOPE_SESSION)
//public class LoginBean implements Serializable {
//
//    private static final long serialVersionUID = 7765876811740798583L;
//    @Autowired
//    private SsUsuariosService usuarioService;
//    @Autowired
//    private SsOpcionesService ssOpcionesService;
//    @Autowired
//    private SsMenusService ssMenusService;
//    @Autowired
//    private NavigationBean navigationBean;
////...
//    private String username;
//    private String password;
//    private List<SsOpciones> options;
//    private List<MenuList> menusLst;
//    private boolean loggedIn;
//
//    /**
//     * Login operation.
//     *
//     * @return
//     */
//    public String doLogin() {
//        // obtener usuario de bdd
//        SsUsuarios usuario = this.usuarioService.checkLogin(username, Md5Generator.generar(password));
//
//        if (usuario != null) { //si existe 
//            options = new ArrayList<SsOpciones>();
//            menusLst = new ArrayList<MenuList>();
//            
//            
//            
//            Iterator it = usuario.getSsRolesList().iterator();
//
//            while (it.hasNext()) { //iterar roles 
//                SsRoles rol = (SsRoles) it.next();
//
//                List<SsMenus> mns = this.ssMenusService.getMenusByrol(rol);
//
//                menusLst = MenuList.GenerarMenu(mns);
//                Iterator itMenu = menusLst.iterator();
//
//                while (itMenu.hasNext()) {
//                    MenuList menu = (MenuList) itMenu.next();
//
//                    List<SsOpciones> opcionesMenu = obtenerMenuOpcion(rol, menu.getSsMenu()); //establecer opciones de menu
//                    menu.setSsOpciones(opcionesMenu);
//
//                    Iterator itSubMenu = menu.getSubMenu().iterator();
//
//                    while (itSubMenu.hasNext()) {
//                        MenuList subMenu = (MenuList) itSubMenu.next();
//                        List<SsOpciones> opcionesSubMenu = obtenerMenuOpcion(rol, subMenu.getSsMenu());
//                        subMenu.setSsOpciones(opcionesSubMenu);
//                    }
//
//                }
//
//            }
//
//            loggedIn = true;
//
//            return navigationBean.redirectToIndex(); //redireccionar a index
//        }
//
//        // login ERROR
//        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
//        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//
//        // redireccionar a login page
//        return navigationBean.toLogin();
//
//    }
//
//    /**
//     * Logout.
//     *
//     * @return
//     */
//    public String doLogout() {
//        // establecer a falso bandera que usado esta logeado en el sistema
//        loggedIn = false;
//
//        // mostrar mensaje de cerrar sesion con exito
//        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//
//        return navigationBean.toLogin();
//    }
//
//    // ------------------------------
//    // Getters & Setters
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isLoggedIn() {
//        return loggedIn;
//    }
//
//    public void setLoggedIn(boolean loggedIn) {
//        this.loggedIn = loggedIn;
//    }
//
//    public void setNavigationBean(NavigationBean navigationBean) {
//        this.navigationBean = navigationBean;
//    }
//
//    public List<SsOpciones> getOptions() {
//        return options;
//    }
//
//    public void setOptions(List<SsOpciones> options) {
//        this.options = options;
//    }
//
//    public SsUsuariosService getUsuarioService() {
//        return usuarioService;
//    }
//
//    public void setUsuarioService(SsUsuariosService usuarioService) {
//        this.usuarioService = usuarioService;
//    }
//
//    public SsOpcionesService getSsOpcionesService() {
//        return ssOpcionesService;
//    }
//
//    public void setSsOpcionesService(SsOpcionesService ssOpcionesService) {
//        this.ssOpcionesService = ssOpcionesService;
//    }
//
//    public SsMenusService getSsMenusService() {
//        return ssMenusService;
//    }
//
//    public void setSsMenusService(SsMenusService ssMenusService) {
//        this.ssMenusService = ssMenusService;
//    }
//
//    private List<SsOpciones> obtenerMenuOpcion(SsRoles rol, SsMenus menu) {
//        List<SsOpciones> opt = this.ssOpcionesService.getOpcionesByMenuRol(rol, menu);
//        options.addAll(opt);
//        return opt;
//
//    }
//
//    public List<MenuList> getMenusLst() {
//        return menusLst;
//    }
//
//    public void setMenusLst(List<MenuList> menusLst) {
//        this.menusLst = menusLst;
//    }
//
//}
