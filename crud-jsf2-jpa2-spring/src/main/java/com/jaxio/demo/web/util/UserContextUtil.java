/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces:src/main/java/util/UserContextUtil.p.vm.java
 */
package com.jaxio.demo.web.util;

import java.util.List;
import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.context.i18n.LocaleContextHolder;

import com.jaxio.demo.context.UserContext;

/**
 * Simple pass over allowing org.springframework.faces.mvc.JsfView to access 'userContext' from EL.
 * 
 * @see org.springframework.security.web.authentication.AnonymousAuthenticationFilter
 */
@Named("userContext")
@Singleton
public class UserContextUtil {

    public String getUsername() {
        return UserContext.getUsername();
    }

    public boolean isAnonymousUser() {
        return UserContext.ANONYMOUS_USER.equalsIgnoreCase(getUsername());
    }

    public boolean isLoggedIn() {
        return !isAnonymousUser();
    }

    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public List<String> getRoles() {
        return UserContext.getRoles();
    }

    public boolean hasRole(String role) {
        return UserContext.getRoles().contains(role);
    }
}