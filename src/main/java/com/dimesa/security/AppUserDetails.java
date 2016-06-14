package com.dimesa.security;

import com.dimesa.model.SsRoles;
import com.dimesa.model.SsUsuarios;
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

    public AppUserDetails(SsUsuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList();
        if (usuario != null) {
            try {
                if (usuario.getSsRolesSet() != null && !usuario.getSsRolesSet().isEmpty()) {
                    for (SsRoles role : usuario.getSsRolesSet()) {
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

}
