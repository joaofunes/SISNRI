package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.UsuarioLazyModel;
import com.sisrni.model.Persona;
import com.sisrni.model.SsRoles;
import com.sisrni.model.SsUsuarios;
import com.sisrni.security.AppUserDetails;
import com.sisrni.security.CustomPasswordEncoder;
import com.sisrni.service.FreeMarkerMailService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.SsRolesService;
import com.sisrni.service.SsUsuariosService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import static com.sisrni.utils.UserContext.getSessionUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Named("usuarioMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UsuarioMB extends GenericManagedBean<SsUsuarios, Integer> {

    @Autowired
    @Qualifier(value = "ssUsuariosService")
    private SsUsuariosService ssUsuarioService;
    @Autowired
    @Qualifier(value = "ssRolesService")
    private SsRolesService ssRolesService;
    
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
     @Autowired
    @Qualifier(value = "currentUserSessionBean")
    private CurrentUserSessionBean currentUserSessionBean;
    
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    
    @Autowired
    FreeMarkerMailService mailService;

    private String clave;
    private String clave2;
    private Boolean existe;
    private String codigo;
    private String email;
    private String bloqueado;
    public SsUsuarios usuario;
    private List<SsUsuarios> listadoUsuarios;
    private SsUsuarios ssUsuariosRol;
    private List<SsRoles> listadoRoles;

    private DualListModel<SsRoles> roles;

    private List<SsRoles> rolesSource;
    private List<SsRoles> rolesTarget;
    private List<SsRoles> rolesTargetTemp;
    
    public Persona persona;

    @PostConstruct
    public void init() {
        persona = new Persona();
        usuario = new SsUsuarios();
        clave = "";
        clave2 = "";
        codigo = "";
        email = "";
        existe = false;
        bloqueado = "";
        ssUsuariosRol = new SsUsuarios();
        rolesSource = new ArrayList<SsRoles>();
        rolesTarget = new ArrayList<SsRoles>();
        roles = new DualListModel<SsRoles>(rolesSource, rolesTarget);
        listadoUsuarios=ssUsuarioService.getAllUser();
        loadLazyModels();
    }

    @Override
    public GenericService<SsUsuarios, Integer> getService() {
        return ssUsuarioService;
    }

    @Override
    public LazyDataModel<SsUsuarios> getNewLazyModel() {
        return new UsuarioLazyModel(listadoUsuarios, ssUsuarioService);
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (getUsuario() != null) {
            String msg = ResourceBundle.getBundle("/crudbundle").getString(SsUsuarios.class.getSimpleName() + "Usuario creado con exito");
            getSelected().setUsuarioRegistro(getUsuario());
            getSelected().setFechaRegistro(new Date());
            persist(PersistAction.CREATE, msg);
        }
    }
    
    public boolean usuarioExiste(Persona persona){
        try {
            usuario = new SsUsuarios();
            usuario=  ssUsuarioService.findByIdPersona(persona.getIdPersona());
           if(usuario!=null){
             existe = true;
          }else{
             existe = false;
          }
        } catch (Exception e) {
        }
        return existe;
    }
    
     public void guardarUsuario(){
        String msg ="";
        try{
          getSelected().setUsuarioRegistro(getUsuario());
          getSelected().setFechaRegistro(new Date());
          getSelected().setIdUsuario(Integer.MIN_VALUE);
          ssUsuariosRol=  ssUsuarioService.findByUser(getSelected().getCodigoUsuario());
          if(ssUsuariosRol==null){
              msg ="Usuario Creado Exitosamente!";
          ssUsuarioService.save(getSelected());
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
          }else{
            msg ="Ya existe este Usuario!";  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado!!", msg));  
          }
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Usuario!");
            e.printStackTrace();
        }
        init();
    } 
     
     
                /**
     * Metodo para setear persona a ser crear Usuario a persona
     * @param persona 
     */
    public void preCrearUsuario(Persona persona){
        try {
            this.persona=persona;
            String msg = "";
            RequestContext context = RequestContext.getCurrentInstance();
            usuario = new SsUsuarios();
            rolesSource = new ArrayList<SsRoles>();
            rolesTarget = new ArrayList<SsRoles>();
            rolesTargetTemp = new ArrayList<SsRoles>();

            usuario=  ssUsuarioService.findByIdPersona(persona.getIdPersona());
            rolesSource = ssRolesService.findAll();
            //codigo = persona.getNombrePersona().substring(0,3)+ persona.getApellidoPersona().substring(0,2)+persona.getIdPersona().toString(); 
          if(usuario!=null){
              //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", "Encontrado"));
              codigo= usuario.getCodigoUsuario();
              bloqueado = usuario.getBloqueado();
              rolesTarget = usuario.getSsRolesList();
              rolesTargetTemp = usuario.getSsRolesList();
              msg = "PF('UsuarioEditDialog').show();";
          }else{
              codigo=persona.getEmailPersona();
              msg = "PF('UsuarioCreateDialog').show();";
          }
            rolesSource.removeAll(rolesTarget);//elimina las roles  ya seleccionadas para usuario             
            roles = new DualListModel<SsRoles>(rolesSource, rolesTarget);
            context.execute(msg);
        } catch (Exception e) {
        }
    }
     
     /**
     * Metodo para almacenar una nueva persona y un Usuario
     */ 
    public void crearUsuario(){
        try {
            String msg = "";
            usuario = new SsUsuarios();
            ssUsuariosRol=  ssUsuarioService.findByUser(codigo);
          if(ssUsuariosRol==null){
             msg ="Usuario Creado Exitosamente!";
             usuario.setCodigoUsuario(codigo);
             clave = getCadenaAlfanumAleatoria (9); 
            String encode = passwordEncoder.encode(clave);
            usuario.setClave(encode);
             
            usuario.setFechaRegistro(new Date());
            usuario.setFechaUltimamodificacion(new Date());
            usuario.setIdPersona(persona.getIdPersona());
            usuario.setNombreUsuario(persona.getNombrePersona()+ " " + persona.getApellidoPersona());
            usuario.setCargo(persona.getCargoPersona());
            usuario.setBloqueado(bloqueado);
            ssUsuarioService.save(usuario);
            asignarRol();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           RequestContext context = RequestContext.getCurrentInstance();
           context.execute("PF('UsuarioCreateDialog').hide();");
           enviarCorreo();
           init();        
           FacesContext.getCurrentInstance().getExternalContext().redirect("List.xhtml");
          }else{
            msg ="Ya existe este Usuario!";  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado!!", msg));  
          }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
     public void editarUsuario(){
        try {
            usuario = new SsUsuarios();
            usuario =  ssUsuarioService.findByUser(codigo);
            usuario.setFechaUltimamodificacion(new Date());
            usuario.setBloqueado(bloqueado);
            ssUsuarioService.merge(usuario);
            asignarRol();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!","Usuario editado con exito"));
           RequestContext context = RequestContext.getCurrentInstance();
           context.execute("PF('UsuarioEditDialog').hide();");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void asignarRol() {
         try {
             for (SsRoles us : rolesTargetTemp) {
                int deleteMenuOpciones = ssUsuarioService.deleteUserRoles(usuario.getIdUsuario(), us.getIdRol());
            }
            List<SsRoles> target = roles.getTarget();
            for (SsRoles it : target) {
                ssUsuarioService.guardarUserRol(usuario.getIdUsuario(), it.getIdRol());
            }
            listadoUsuarios=ssUsuarioService.getAllUser();
            //ssUsuarioService.merge(ssUsuariosRol)
        } catch (Exception e) {
         e.printStackTrace();
        }
    }

    
       String getCadenaAlfanumAleatoria (int longitud){
    String cadenaAleatoria = "";
    long milis = new java.util.GregorianCalendar().getTimeInMillis();
    Random r = new Random(milis);
    int i = 0;
    while ( i < longitud){
    char c = (char)r.nextInt(255);
    if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
    cadenaAleatoria += c;
        i ++;
    }
    }
    return cadenaAleatoria;
    }
       
       public void enviarCorreo() {
        try {
            Map<String, Object> templateData = new HashMap<String, Object>();
            templateData.put("subJect", "Usuario Creado");
            templateData.put("nameTemplate", "usuario_mailTemplat.xhtml");
            templateData.put("persona", persona);
            templateData.put("usuario", usuario);
            templateData.put("clave", clave);
            templateData.put("setToMail", persona.getEmailPersona());
            mailService.sendEmailMap(templateData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
              public void enviarCorreo2() {
        try {
            Map<String, Object> templateData = new HashMap<String, Object>();
            templateData.put("subJect", "Credenciales de Acceso");
            templateData.put("nameTemplate", "usuario_mailTemplat.xhtml");
            templateData.put("persona", persona);
            templateData.put("usuario", usuario);
            templateData.put("clave", clave);
            templateData.put("setToMail", persona.getEmailPersona());
            mailService.sendEmailMap(templateData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
       public void recuperarPass(){
        try {
            String msg = "";
            usuario = new SsUsuarios();
            persona = new Persona();
            persona = personaService.getPersonaByEmail(email);
           if (persona != null) {
                usuario =  ssUsuarioService.findByIdPersona(persona.getIdPersona());
                if(usuario!=null){
                   msg ="Correo confirmado, favor revise su bandeja de entrada!";
                   clave = getCadenaAlfanumAleatoria (9); 
                   String encode = passwordEncoder.encode(clave);
                   usuario.setClave(encode);  
                   usuario.setFechaUltimamodificacion(new Date());
                   ssUsuarioService.merge(usuario);
                   enviarCorreo2();
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Confirmado!!", msg)); 
                   RequestContext context = RequestContext.getCurrentInstance();
                   context.execute("PF('RecuperarPassDialog').hide();");
          }else{
            msg ="No existen usuarios vinculados a su persona!";  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario no Asigando", msg));  
          }
           }else{ 
               msg ="No existe informaci&oacute;n vinculada a su correo electr&oacute;nico!"; 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No Registrado!!", msg));  
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
     public void cambiarPass(){
        try {
        
                   usuario = new SsUsuarios();
                   ssUsuariosRol = new SsUsuarios();
                   AppUserDetails user = currentUserSessionBean.getSessionUser();
                   if (user != null){
                        //usuario =  user.getUsuario();
                        String encode = passwordEncoder.encode(clave);
                        if(user.getPassword().equals(encode)){
                            ssUsuariosRol =  user.getUsuario();
                            usuario =  ssUsuarioService.findByUser(ssUsuariosRol.getCodigoUsuario());
                            encode = passwordEncoder.encode(clave2);
                            usuario.setClave(encode);  
                            usuario.setFechaUltimamodificacion(new Date());
                            ssUsuarioService.merge(usuario);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Confirmado!!", "Clave cambiada con Exito"));
                            RequestContext context = RequestContext.getCurrentInstance();
                            context.execute("PF('RecuperarPassDialog').hide();");
                        }
                        else{
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "Clave antigua incorrecta"));
                        }
                        }
                   else{
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error!!", "Usuario No encontrado"));    
                   }
       
        } catch (Exception e) {
            e.printStackTrace();
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
            

    public void preEditarRol() {
        try {
            rolesSource = new ArrayList<SsRoles>();
            rolesTarget = new ArrayList<SsRoles>();
            rolesTargetTemp = new ArrayList<SsRoles>();

            ssUsuariosRol = ssUsuarioService.findByUser(getSelected().getCodigoUsuario());

            rolesTarget = ssUsuariosRol.getSsRolesList();
            rolesTargetTemp = ssUsuariosRol.getSsRolesList();
            rolesSource = ssRolesService.findAll();

            rolesSource.removeAll(rolesTarget);//elimina las roles  ya seleccionadas para usuario             
            roles = new DualListModel<SsRoles>(rolesSource, rolesTarget);

        } catch (Exception e) {
        }
    }

    
    public void guardarWithRol(){
        try {
             for (SsRoles us : rolesTargetTemp) {
                int deleteMenuOpciones = ssUsuarioService.deleteUserRoles(ssUsuariosRol.getIdUsuario(), us.getIdRol());
            }
            List<SsRoles> target = roles.getTarget();
            for (SsRoles it : target) {
                ssUsuarioService.guardarUserRol(ssUsuariosRol.getIdUsuario(), it.getIdRol());
            }
            listadoUsuarios=ssUsuarioService.getAllUser();
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formMenu");
            JsfUtil.addSuccessMessage("Guardado Exitosamente");
            //ssUsuarioService.merge(ssUsuariosRol)
        } catch (Exception e) {
         e.printStackTrace();
        }
    }
    
        public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
   
    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }
    
    public SsUsuarios getSsUsuariosRol() {
        return ssUsuariosRol;
    }

    public void setSsUsuariosRol(SsUsuarios ssUsuariosRol) {
        this.ssUsuariosRol = ssUsuariosRol;
    }

    public List<SsRoles> getListadoRoles() {
        return listadoRoles;
    }

    public void setListadoRoles(List<SsRoles> listadoRoles) {
        this.listadoRoles = listadoRoles;
    }

    public DualListModel<SsRoles> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<SsRoles> roles) {
        this.roles = roles;
    }

    public List<SsRoles> getRolesSource() {
        return rolesSource;
    }

    public void setRolesSource(List<SsRoles> rolesSource) {
        this.rolesSource = rolesSource;
    }

    public List<SsRoles> getRolesTarget() {
        return rolesTarget;
    }

    public void setRolesTarget(List<SsRoles> rolesTarget) {
        this.rolesTarget = rolesTarget;
    }

    public List<SsRoles> getRolesTargetTemp() {
        return rolesTargetTemp;
    }

    public void setRolesTargetTemp(List<SsRoles> rolesTargetTemp) {
        this.rolesTargetTemp = rolesTargetTemp;
    }

    public List<SsUsuarios> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public void setListadoUsuarios(List<SsUsuarios> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    public Boolean getExiste() {
        return existe;
    }

    public void setExiste(Boolean existe) {
        this.existe = existe;
    }
}
