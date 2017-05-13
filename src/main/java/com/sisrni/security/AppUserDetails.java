package com.sisrni.security;

import com.sisrni.model.SsRoles;
import com.sisrni.model.SsUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {

    private final static Log log = LogFactory.getLog(AppUserDetails.class);
    private SsUsuarios usuario;
    private Boolean esAdmConvenio;
    private Boolean esAdm;

    public AppUserDetails(SsUsuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList();
        if (usuario != null) {
            try {
                if (usuario.getSsRolesList() != null && !usuario.getSsRolesList().isEmpty()) {
                    for (SsRoles role : usuario.getSsRolesList()) {
                        if (role.getCodigoRol().equalsIgnoreCase("ROL_ADM_CONV")) {
                            esAdmConvenio = Boolean.TRUE;
                        }
                        if (role.getCodigoRol().equalsIgnoreCase("ROL_ADMI")) {
                            esAdm = Boolean.TRUE;
                        }
                        authorities.add(new GrantedAuthorityImpl(role.getCodigoRol()));
                    }
                }
            } catch (Exception e) {
                log.error(e);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario != null ? usuario.getClave() : null;
    }

    @Override
    public String getUsername() {
        return usuario != null ? usuario.getNombreUsuario() : null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return usuario != null && usuario.getBloqueado() != null ? usuario.getBloqueado().equals("N") : true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return usuario != null && usuario.getBloqueado() != null ? usuario.getBloqueado().equals("N") : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return usuario != null && usuario.getBloqueado() != null ? usuario.getBloqueado().equals("N") : true;
    }

    @Override
    public boolean isEnabled() {
        return usuario != null && usuario.getBloqueado() != null ? usuario.getBloqueado().equals("N") : true;
    }

    public SsUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(SsUsuarios usuario) {
        this.usuario = usuario;
    }

    public Boolean getEsAdmConvenio() {
        return esAdmConvenio;
    }

    public void setEsAdmConvenio(Boolean esAdmConvenio) {
        this.esAdmConvenio = esAdmConvenio;
    }

      public Boolean getEsAdm() {
        return esAdm;
    }

    public void setEsAdm(Boolean esAdm) {
        this.esAdm = esAdm;
    }
}
