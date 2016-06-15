package com.sisrni.security;

import com.sisrni.config.SpringApplicationContextServiceProvider;
import com.sisrni.model.SsRoles;
import com.sisrni.service.SsRolesService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class CustomFilterInvocationDefinition implements FilterInvocationSecurityMetadataSource, InitializingBean {

    private final static Log log = LogFactory.getLog(CustomFilterInvocationDefinition.class);

    protected String lowercaseAndStripQuerystring(final String url) {
        String fixed = url;
        fixed = fixed.toLowerCase();
        int firstQuestionMarkIndex = fixed.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            fixed = fixed.substring(0, firstQuestionMarkIndex);
        }
        return fixed;
    }

    protected String determineUrl(final FilterInvocation filterInvocation) {
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String requestUrl = request.getRequestURI().substring(request.getContextPath().length());
        return lowercaseAndStripQuerystring(requestUrl);
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String url = determineUrl(filterInvocation);
        SsRolesService ssRolesService = (SsRolesService) SpringApplicationContextServiceProvider.getBean("ssRolesService");

        List<SsRoles> rolesfounded = ssRolesService.findByUrl(url);
        List<String> aroles = new ArrayList<String>();
        if (rolesfounded != null && !rolesfounded.isEmpty()) {
            for (SsRoles r : rolesfounded) {
                aroles.add(r.getCodigoRol());
            }
        }
        // remove duplicate roles
        HashSet<String> hs = new HashSet<String>();
        hs.addAll(aroles);
        aroles.clear();
        aroles.addAll(hs);
        //...
        String[] roles = new String[aroles.size()];
        roles = aroles.toArray(roles);
        List<ConfigAttribute> ret = SecurityConfig.createList(roles);
//        if(url.contains("report")){
//            log.info("URL (" + url + "), requires roles (" + ret + ")");
//        }
        log.info("URL (" + url + "), requires roles (" + ret + ")");
        return ret;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    private boolean supports(Class<?> config, final AccessDecisionVoter voter) {
        return voter != null && voter.supports(config);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ afterPropertiesSet");
    }

}
