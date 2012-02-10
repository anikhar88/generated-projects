/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces-sd:src/main/java/SearchForm.e.vm.java
 */
package com.jaxio.demo.web.domain;

import static com.google.common.collect.Lists.newArrayList;
import static com.jaxio.demo.domain.Book_.*;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.jaxio.demo.domain.Book;
import com.jaxio.demo.repository.BookRepository;
import com.jaxio.demo.repository.support.Range;
import com.jaxio.demo.repository.support.Ranges.RangeInteger;
import com.jaxio.demo.web.domain.support.GenericSearchForm;

/**
 * SearchForm for {@link Book}
 */
@Component
@Scope("session")
public class BookSearchForm extends GenericSearchForm<Book> implements Serializable {
    private static final long serialVersionUID = 1L;
    // make it static to avoid http://jira.springframework.org/browse/SWF-1224
    private static BookRepository bookRepository;

    private Book book = new Book();
    private RangeInteger<Book> numberOfPagesRange = new RangeInteger<Book>(numberOfPages);

    @Autowired
    public BookSearchForm(BookRepository instance) {
        if (bookRepository == null) {
            bookRepository = instance;
        }
    }

    /**
     * Server side pagination with lazy model.
     * Automatically called by PrimeFaces component (via the GenericSearchForm).
     */
    @Override
    protected List<Book> load(PageRequest pageRequest) {
        List<Range<Book, ?>> ranges = newArrayList();
        ranges.add(numberOfPagesRange);

        Page<Book> page = bookRepository.findWithExample(book, ranges, pageRequest);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    /**
     * The root book for search by example.
     * Used from the view.
     */
    public Book getBook() {
        return book;
    }

    // Ranges, used from the view.

    public RangeInteger<Book> getNumberOfPagesRange() {
        return numberOfPagesRange;
    }
}