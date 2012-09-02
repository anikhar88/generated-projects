/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/DAOImpl.e.vm.java
 */
package com.jaxio.demo.dao;

import javax.inject.Named;
import javax.inject.Singleton;
import com.jaxio.demo.dao.DocumentDao;
import com.jaxio.demo.dao.support.GenericDao;
import com.jaxio.demo.domain.Document;

/**
 * JPA 2 Data Access Object for {@link com.jaxio.demo.domain.Document}.
 * Note: do not use @Transactional in the DAO layer.
 */
@Named
@Singleton
public class DocumentDao extends GenericDao<Document, String> {
    public DocumentDao() {
        super(Document.class);
    }
}