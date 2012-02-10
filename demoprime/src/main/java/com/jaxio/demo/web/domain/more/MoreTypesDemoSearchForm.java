/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces-sd:src/main/java/SearchForm.e.vm.java
 */
package com.jaxio.demo.web.domain.more;

import static com.google.common.collect.Lists.newArrayList;
import static com.jaxio.demo.domain.more.MoreTypesDemo_.*;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.jaxio.demo.domain.more.MoreTypesDemo;
import com.jaxio.demo.repository.more.MoreTypesDemoRepository;
import com.jaxio.demo.repository.support.Range;
import com.jaxio.demo.repository.support.Ranges.RangeBigDecimal;
import com.jaxio.demo.repository.support.Ranges.RangeBigInteger;
import com.jaxio.demo.repository.support.Ranges.RangeDate;
import com.jaxio.demo.repository.support.Ranges.RangeDouble;
import com.jaxio.demo.repository.support.Ranges.RangeFloat;
import com.jaxio.demo.repository.support.Ranges.RangeInteger;
import com.jaxio.demo.repository.support.Ranges.RangeLocalDate;
import com.jaxio.demo.repository.support.Ranges.RangeLocalDateTime;
import com.jaxio.demo.repository.support.Ranges.RangeLong;
import com.jaxio.demo.web.domain.support.GenericSearchForm;

/**
 * SearchForm for {@link MoreTypesDemo}
 */
@Component
@Scope("session")
public class MoreTypesDemoSearchForm extends GenericSearchForm<MoreTypesDemo> implements Serializable {
    private static final long serialVersionUID = 1L;
    // make it static to avoid http://jira.springframework.org/browse/SWF-1224
    private static MoreTypesDemoRepository moreTypesDemoRepository;

    private MoreTypesDemo moreTypesDemo = new MoreTypesDemo();
    private RangeInteger<MoreTypesDemo> numberIntRange = new RangeInteger<MoreTypesDemo>(numberInt);
    private RangeLong<MoreTypesDemo> numberLongRange = new RangeLong<MoreTypesDemo>(numberLong);
    private RangeDouble<MoreTypesDemo> numberDoubleRange = new RangeDouble<MoreTypesDemo>(numberDouble);
    private RangeFloat<MoreTypesDemo> numberFloatRange = new RangeFloat<MoreTypesDemo>(numberFloat);
    private RangeBigInteger<MoreTypesDemo> numberBigIntegerRange = new RangeBigInteger<MoreTypesDemo>(numberBigInteger);
    private RangeBigDecimal<MoreTypesDemo> numberBigDecimalRange = new RangeBigDecimal<MoreTypesDemo>(numberBigDecimal);
    private RangeDate<MoreTypesDemo> dateJavaTemporalDateRange = new RangeDate<MoreTypesDemo>(dateJavaTemporalDate);
    private RangeDate<MoreTypesDemo> dateJavaTemporalTimestampRange = new RangeDate<MoreTypesDemo>(
            dateJavaTemporalTimestamp);
    private RangeLocalDate<MoreTypesDemo> dateJodaRange = new RangeLocalDate<MoreTypesDemo>(dateJoda);
    private RangeLocalDateTime<MoreTypesDemo> dateTimeJodaRange = new RangeLocalDateTime<MoreTypesDemo>(dateTimeJoda);

    @Autowired
    public MoreTypesDemoSearchForm(MoreTypesDemoRepository instance) {
        if (moreTypesDemoRepository == null) {
            moreTypesDemoRepository = instance;
        }
    }

    /**
     * Server side pagination with lazy model.
     * Automatically called by PrimeFaces component (via the GenericSearchForm).
     */
    @Override
    protected List<MoreTypesDemo> load(PageRequest pageRequest) {
        List<Range<MoreTypesDemo, ?>> ranges = newArrayList();
        ranges.add(numberIntRange);
        ranges.add(numberLongRange);
        ranges.add(numberDoubleRange);
        ranges.add(numberFloatRange);
        ranges.add(numberBigIntegerRange);
        ranges.add(numberBigDecimalRange);
        ranges.add(dateJavaTemporalDateRange);
        ranges.add(dateJavaTemporalTimestampRange);
        ranges.add(dateJodaRange);
        ranges.add(dateTimeJodaRange);

        Page<MoreTypesDemo> page = moreTypesDemoRepository.findWithExample(moreTypesDemo, ranges, pageRequest);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    /**
     * The root moreTypesDemo for search by example.
     * Used from the view.
     */
    public MoreTypesDemo getMoreTypesDemo() {
        return moreTypesDemo;
    }

    // Ranges, used from the view.

    public RangeInteger<MoreTypesDemo> getNumberIntRange() {
        return numberIntRange;
    }

    public RangeLong<MoreTypesDemo> getNumberLongRange() {
        return numberLongRange;
    }

    public RangeDouble<MoreTypesDemo> getNumberDoubleRange() {
        return numberDoubleRange;
    }

    public RangeFloat<MoreTypesDemo> getNumberFloatRange() {
        return numberFloatRange;
    }

    public RangeBigInteger<MoreTypesDemo> getNumberBigIntegerRange() {
        return numberBigIntegerRange;
    }

    public RangeBigDecimal<MoreTypesDemo> getNumberBigDecimalRange() {
        return numberBigDecimalRange;
    }

    public RangeDate<MoreTypesDemo> getDateJavaTemporalDateRange() {
        return dateJavaTemporalDateRange;
    }

    public RangeDate<MoreTypesDemo> getDateJavaTemporalTimestampRange() {
        return dateJavaTemporalTimestampRange;
    }

    public RangeLocalDate<MoreTypesDemo> getDateJodaRange() {
        return dateJodaRange;
    }

    public RangeLocalDateTime<MoreTypesDemo> getDateTimeJodaRange() {
        return dateTimeJodaRange;
    }
}