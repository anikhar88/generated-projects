/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/service/ServiceImplTest.e.vm.java
 */
package com.jaxio.demo.repository;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.persistence.NonUniqueResultException;
import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;

import com.jaxio.demo.domain.Document;
import com.jaxio.demo.repository.DocumentRepositoryImpl;
import com.jaxio.demo.dao.DocumentDao;
import com.jaxio.demo.dao.support.SearchParameters;

/**
 * Unit test on DocumentRepositoryImpl
 */
public class DocumentRepositoryImplTest {

    private DocumentRepositoryImpl documentRepositoryImpl;
    private DocumentDao documentDao;

    @Before
    public void setUp() {
        // called before each test.
        documentRepositoryImpl = new DocumentRepositoryImpl();
        documentDao = mock(DocumentDao.class);
        documentRepositoryImpl.setDocumentDao(documentDao);
    }

    @Test
    public void testFindUniqueOrNoneCaseNone() {
        Document none = null;

        when(documentDao.findUniqueOrNone(any(Document.class), any(SearchParameters.class))).thenReturn(none);

        Document result = documentRepositoryImpl.findUniqueOrNone(new Document());

        assertThat(result).isNull();
        verify(documentDao, times(1)).findUniqueOrNone(any(Document.class), any(SearchParameters.class));
    }

    @Test
    public void testFindUniqueOrNoneCaseUnique() {
        Document unique = new Document();

        when(documentDao.findUniqueOrNone(any(Document.class), any(SearchParameters.class))).thenReturn(unique);

        Document result = documentRepositoryImpl.findUniqueOrNone(new Document());

        assertThat(result).isNotNull();
        verify(documentDao, times(1)).findUniqueOrNone(any(Document.class), any(SearchParameters.class));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NonUniqueResultException.class)
    public void testFindUniqueOrNoneCaseMultiple() {
        when(documentDao.findUniqueOrNone(any(Document.class), any(SearchParameters.class))).thenThrow(NonUniqueResultException.class);

        documentRepositoryImpl.findUniqueOrNone(new Document());
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NoResultException.class)
    public void testFindUniqueCaseNone() {
        when(documentDao.findUnique(any(Document.class), any(SearchParameters.class))).thenThrow(NoResultException.class);

        documentRepositoryImpl.findUnique(new Document());
    }

    @Test
    public void testFindUniqueCaseUnique() {
        Document unique = new Document();

        when(documentDao.findUnique(any(Document.class), any(SearchParameters.class))).thenReturn(unique);

        Document result = documentRepositoryImpl.findUnique(new Document());

        assertThat(result).isNotNull();
        verify(documentDao, times(1)).findUnique(any(Document.class), any(SearchParameters.class));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NonUniqueResultException.class)
    public void testFindUniqueCaseMultiple() {
        when(documentDao.findUnique(any(Document.class), any(SearchParameters.class))).thenThrow(NonUniqueResultException.class);

        documentRepositoryImpl.findUnique(new Document());
    }
}