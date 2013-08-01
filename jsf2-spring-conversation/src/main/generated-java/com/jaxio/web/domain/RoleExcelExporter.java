/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/domain/ExcelExporter.e.vm.java
 */
package com.jaxio.web.domain;

import static org.apache.commons.lang.StringUtils.join;

import javax.inject.Inject;
import javax.inject.Named;

import com.jaxio.domain.Role;
import com.jaxio.web.domain.support.GenericExcelExporter;
import com.jaxio.web.faces.ConversationContextScoped;

/**
 * View Helper to search {@link Role}.
 * It exposes a {@link Role} instance so it can be used in search by-example-query.
 */
@Named
@ConversationContextScoped
public class RoleExcelExporter extends GenericExcelExporter<Role> {
    @Inject
    protected RoleSearchForm sf;

    public RoleExcelExporter() {
        super("role_roleName");
    }

    @Override
    protected void fillResultItem(int row, Role item) {
        int col = 0;
        setValue(row, col++, item.getRoleName());
    }

    @Override
    public void fillSearchCriteria(int row) {
        useCriteriaSheet();

        setLeftHeader(row, 0, "search_full_text");
        setValue(row++, 1, join(sf.getTermsOnAll().getSelected(), ' '));

        setSelector(row++, 0, "role_roleName", sf.getRoleNameSelector());
    }
}