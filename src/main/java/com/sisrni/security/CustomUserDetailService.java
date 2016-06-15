package com.sisrni.security;

import com.sisrni.dao.SsUsuariosDao;
import com.sisrni.model.SsUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "authServiceProvider")
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private SsUsuariosDao ssUsuariosDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SsUsuarios user = ssUsuariosDao.findByUser(username);
        AppUserDetails ret = new AppUserDetails(user);
        return ret;
    }

    public SsUsuariosDao getSsUsuariosDao() {
        return ssUsuariosDao;
    }

    public void setSsUsuariosDao(SsUsuariosDao ssUsuariosDao) {
        this.ssUsuariosDao = ssUsuariosDao;
    }

}
