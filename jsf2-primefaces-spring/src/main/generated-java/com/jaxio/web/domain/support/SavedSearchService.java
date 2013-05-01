/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/domain/support/SavedSearchService.p.vm.java
 */
package com.jaxio.web.domain.support;

import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static com.google.common.collect.Lists.newArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import com.google.common.base.Throwables;
import com.jaxio.context.UserContext;
import com.jaxio.dao.support.SearchParameters;
import com.jaxio.domain.Account;
import com.jaxio.domain.SavedSearch;
import com.jaxio.repository.AccountRepository;
import com.jaxio.repository.SavedSearchRepository;
import com.jaxio.web.domain.support.GenericSearchForm;
import com.jaxio.web.util.MessageUtil;

/**
 * Store/Load Search Form Content to db.
 */
@Named
@Singleton
@SuppressWarnings( { "rawtypes", "unchecked" })
public class SavedSearchService {
    @Inject
    private SavedSearchRepository savedSearchRepository;
    @Inject
    private MessageUtil messageUtil;
    @Inject
    private AccountRepository accountRepository;

    public void reload(GenericSearchForm searchForm) {
        if (StringUtils.isNotBlank(searchForm.getSearchFormName())) {
            searchForm.resetWithOther(load(searchForm));
        }
        // silently ignore it
    }

    public void onChange(SelectEvent selectEvent) {
        GenericSearchForm searchForm = (GenericSearchForm) selectEvent.getComponent().getAttributes().get("searchForm");
        searchForm.setSearchFormName((String) selectEvent.getObject()); // set the new value
        reload(searchForm);
    }

    @Transactional
    public void save(GenericSearchForm searchFrom) {
        SavedSearch savedSearch = savedSearchRepository.findUniqueOrNone(example(searchFrom));
        if (savedSearch == null) {
            savedSearch = example(searchFrom);
            savedSearch.setAccount(accountRepository.get(savedSearch.getAccount()));
        }

        savedSearch.setFormContent(toByteArray(searchFrom));
        savedSearchRepository.save(savedSearch);
        messageUtil.info("saved_search_saved", savedSearch.getName());
    }

    protected <F extends GenericSearchForm, T> F load(F searchFrom) {
        SavedSearch savedSearch = savedSearchRepository.findUnique(example(searchFrom));
        messageUtil.info("saved_search_loaded", savedSearch.getName());
        return fromByteArray(savedSearch.getFormContent());
    }

    private SavedSearch example(GenericSearchForm searchFrom) {
        SavedSearch savedSearch = new SavedSearch();
        savedSearch.setName(searchFrom.getSearchFormName());
        savedSearch.setFormClassname(searchFrom.getClass().getSimpleName());
        Account currentAccount = new Account();
        currentAccount.setId(String.valueOf(UserContext.getId()));
        savedSearch.setAccount(currentAccount);
        return savedSearch;
    }

    public <F extends GenericSearchForm> Finder finderFor(F searchFrom) {
        // we use a Finder in order to have the required List<String> find(String) method
        return new Finder(savedSearchRepository, searchFrom);
    }

    public class Finder {
        private SavedSearchRepository savedSearchRepository;
        private GenericSearchForm searchFrom;

        public Finder(SavedSearchRepository savedSearchRepository, GenericSearchForm searchFrom) {
            this.savedSearchRepository = savedSearchRepository;
            this.searchFrom = searchFrom;
        }

        public List<String> find(String name) {
            List<String> results = newArrayList();
            for (SavedSearch savedSearch : savedSearchRepository.find(example(searchFrom), new SearchParameters().anywhere())) {
                results.add(savedSearch.getName());
            }
            return results;
        }
    }

    private byte[] toByteArray(GenericSearchForm form) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(form);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        } finally {
            closeQuietly(oos);
            closeQuietly(baos);
        }
    }

    private <F> F fromByteArray(byte[] bytes) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (F) ois.readObject();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        } finally {
            closeQuietly(ois);
        }
    }
}