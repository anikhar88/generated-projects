/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces-sd:src/main/java/GenericSearchForm.p.vm.java
 */
package com.jaxio.demo.web.domain.support;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

/**
 * Base search form for JPA entities. It supports 'Server Side Pagination' using PrimeFaces LazyDataModel.
 */
public abstract class GenericSearchForm<E> extends LazyDataModel<E> {
    private static final long serialVersionUID = 1L;
    private E selectedRow;
    private boolean resetPaginator = true;

    /**
     * Call it from your flow when a 'search' event is received, 
     * store the result in your viewState and use it from your view.
     * Important note: the returned instance as soon as it is serialized  lives its own life. 
     * 
     * @return the model expected by dataTable with lazy loading support.  
     */
    public LazyDataModel<E> getLazyDataModel() {
        resetPaginator = true;
        return this;
    }

    abstract protected List<E> load(PageRequest pageRequest);

    /**
     * Server side pagination with lazy model.
     * Automatically called by PrimeFaces component.
     */
    public List<E> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return load(toPageRequest(first, pageSize, sortField, sortOrder, filters));
    }

    /**
     * Applies the passed parameters to the passed SearchTemplate.
     * Note: filters are not supported for the moment
     * @return the passed searchTemplate
     */
    protected PageRequest toPageRequest(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, String> filters) {
        int start = resetPaginator ? 0 : first;
        resetPaginator = false;

        if (sortField != null && sortOrder != null) {
            return new PageRequest(start / pageSize, pageSize, convert(sortOrder), sortField);
        } else {
            return new PageRequest(start / pageSize, pageSize);
        }
    }

    /**
     * Returns the currently selected row. To be called from your flow upon a "selectXxx" transition.
     */
    public E getSelectedRow() {
        return selectedRow;
    }

    /**
     * Set the currently selected row. To be called from your dataTable.
     */
    public void setSelectedRow(E selectedRow) {
        this.selectedRow = selectedRow;
    }

    /**
     * Convert PrimeFaces SortOrder to our OrderByDirection.
     */
    public static Direction convert(SortOrder ob) {
        switch (ob) {
        case DESCENDING:
            return Direction.DESC;
        default:
            return Direction.ASC;
        }
    }
}