package com.sisrni.utils;

import com.sisrni.model.SsUsuarios;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELResolver;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserContext {

    public final static String SESSION_USER_VARIABLE = "SISRNI_SESSION_USER";
    private final static Log log = LogFactory.getLog(UserContext.class);

    public static SsUsuarios getSessionUser() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return (SsUsuarios) request.getSession().getAttribute(SESSION_USER_VARIABLE);
    }

    public static SsUsuarios getSessionUser(HttpServletRequest request) {
        return (SsUsuarios) request.getSession().getAttribute(SESSION_USER_VARIABLE);
    }

    public static void setSessionUser(SsUsuarios user) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        request.getSession().setAttribute(SESSION_USER_VARIABLE, user);
    }

    public static void setSessionUser(HttpServletRequest request, SsUsuarios user) {
        request.getSession().setAttribute(SESSION_USER_VARIABLE, user);
    }

    public static HttpServletRequest getHttpServletRequest() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return (HttpServletRequest) context.getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return (HttpServletResponse) context.getResponse();
    }

    public static String getRemoteAddress() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getRemoteAddr();
    }

    public static void openSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        request.getSession(true);
    }

    public static void closeSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        request.getSession().invalidate();
    }

    public static void closeSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public static void setSessionVar(String paramName, Object paramValue) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        request.getSession().setAttribute(paramName, paramValue);
    }

    public static void setSessionVar(HttpServletRequest request, String paramName, Object paramValue) {
        request.getSession().setAttribute(paramName, paramValue);
    }

    public static Object getSessionVar(String paramName) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getSession().getAttribute(paramName);
    }

    public static Object getSessionVar(HttpServletRequest request, String paramName) {
        return request.getSession().getAttribute(paramName);
    }

    public static void setRequestVar(String paramName, Object paramValue) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        request.setAttribute(paramName, paramValue);
    }

    public static Object getRequestVar(String paramName) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getAttribute(paramName);
    }

    public static Object getParameterVar(String paramName) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getParameter(paramName);
    }

    public static String getRequestParameter(String paramName) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getParameter(paramName);
    }

    public static String getRequestParameter(HttpServletRequest request, String paramName) {
        return request.getParameter(paramName);
    }

    public static String[] getRequestParameterValues(String paramName) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getParameterValues(paramName);
    }

    public static void initSession() {
        openSession();
        Calendar expires = Calendar.getInstance();
        expires.setTimeZone(TimeZone.getTimeZone("GMT"));
        expires.add(Calendar.HOUR, 24);
        SimpleDateFormat format = new java.text.SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        format.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext econtext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) econtext.getRequest();
        ((HttpServletResponse) context.getExternalContext().getResponse()).setHeader("Set-Cookie", "JSESSIONID=" + (request.getSession().getId()) + "; expires=" + (format.format(expires.getTime())) + "; HttpOnly");
        log.error((new Date()) + " - jsessionid cookie expires: " + (format.format(expires.getTime())));
    }

    public static void saveToCookie(String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        FacesContext context = FacesContext.getCurrentInstance();
        ((HttpServletResponse) context.getExternalContext().getResponse()).addCookie(cookie);
    }

    public static void removeCookie(String name) {
        saveToCookie(name, "", 0);
    }

    public static String getCookieValue(String name) {
        String ret = null;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Cookie cookies[] = ((HttpServletRequest) context.getExternalContext().getRequest()).getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie c : cookies) {
                    if (c.getName().equals(name)) {
                        ret = c.getValue();
                    }
                }
            } else {
                log.error("NO HAY COOKIES!!!!!!!!!!!!");
            }
        } catch (Exception e) {
            Logger.getLogger(UserContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return ret;
    }

    public static String getCookieValue(Cookie[] cookies, String name) {
        String ret = null;
        try {
            if (cookies != null && cookies.length > 0) {
                for (Cookie c : cookies) {
                    if (c.getName().equals(name)) {
                        ret = c.getValue();
                    }
                }
            } else {
                log.error("NO HAY COOKIES!!!!!!!!!!!!");
            }
        } catch (Exception e) {
            Logger.getLogger(UserContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return ret;
    }

    public static boolean isCookiesEnabled() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        Cookie[] cookies = request.getCookies();
        int i = -1;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().toUpperCase().equals("JSESSIONID")) {
                    break;
                }
            }
        }
        return (i > -1 && i < (cookies != null ? cookies.length : Integer.MAX_VALUE));
    }

    public static String getUserAgent() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getHeader("User-Agent").toUpperCase();
    }

    public static void redirect(String newUrl) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().dispatch(newUrl);
        } catch (IOException e) {
            Logger.getLogger(UserContext.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            context.responseComplete();
        }
    }

    public static int getInternalFolderCount() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        String url = request.getServletPath();
        String[] carpetas = url.split("/");
        return carpetas.length >= 2 ? carpetas.length - 2 : 0;
    }

    public static int getInternalFolderCount(HttpServletRequest request) {
        String url = request.getServletPath();
        String[] carpetas = url.split("/");
        return carpetas.length >= 2 ? carpetas.length - 2 : 0;
    }

    public static Object getBean(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getVariableResolver().resolveVariable(context, name);
    }

    public static Object getBean(String name, Object base) { //base = null
        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver elres = context.getApplication().getELResolver();
        return elres.getValue(context.getELContext(), base, name);
    }

    public static Object getBeanAlternately(String name, Class<?> type) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{" + name + "}", type);
    }

    public static void removeBean(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove(name);
    }
}
