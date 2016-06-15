package com.sisrni.security;

import com.sisrni.config.SpringApplicationContextServiceProvider;
import com.sisrni.managedbean.CurrentUserSessionBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        logger.debug(user.getUsername() + " login successfully.");
        CurrentUserSessionBean currentUserSessionBean = (CurrentUserSessionBean) SpringApplicationContextServiceProvider.getBean("currentUserSessionBean");
        if (currentUserSessionBean != null) {
            if (currentUserSessionBean.getForm() != null) {
                currentUserSessionBean.getForm().setSessionUser(user);
                currentUserSessionBean.calculateMenu();
            }
        } else {
            logger.error("There is no #{currentUserSessionBean} in session.");
        }
        //...
        this.setDefaultTargetUrl("/views/index.xhtml");
        this.setAlwaysUseDefaultTargetUrl(false);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
