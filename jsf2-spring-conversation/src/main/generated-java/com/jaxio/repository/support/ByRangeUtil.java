/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/repository/support/ByRangeUtil.p.vm.java
 */
package com.jaxio.repository.support;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Helper to create a predicate out of {@link Range}s.
 */
@Named
@Singleton
public class ByRangeUtil {
    public <E> Predicate byRanges(Root<E> root, CriteriaBuilder builder, SearchParameters sp, Class<E> type) {
        List<Range<?, ?>> ranges = sp.getRanges();
        List<Predicate> predicates = newArrayList();
        for (Range<?, ?> r : ranges) {
            @SuppressWarnings("unchecked")
            Range<E, ?> range = (Range<E, ?>) r;
            if (range.isSet()) {
                Predicate rangePredicate = buildRangePredicate(range, root, builder);

                if (rangePredicate != null) {
                    if (!range.isIncludeNullSet() || range.getIncludeNull() == FALSE) {
                        predicates.add(rangePredicate);
                    } else {
                        predicates.add(builder.or(rangePredicate, builder.isNull(root.get(range.getField()))));
                    }
                } else {
                    // no from/to is set, but include null or not could be:
                    if (TRUE == range.getIncludeNull()) {
                        predicates.add(builder.isNull(root.get(range.getField())));
                    } else if (FALSE == range.getIncludeNull()) {
                        predicates.add(builder.isNotNull(root.get(range.getField())));
                    }
                }
            }
        }

        return JpaUtil.concatPredicate(sp, builder, predicates);
    }

    private static <D extends Comparable<? super D>, E> Predicate buildRangePredicate(Range<E, D> range, Root<E> root, CriteriaBuilder builder) {
        if (range.isBetween()) {
            return builder.between(root.get(range.getField()), range.getFrom(), range.getTo());
        } else if (range.isFromSet()) {
            return builder.greaterThanOrEqualTo(root.get(range.getField()), range.getFrom());
        } else if (range.isToSet()) {
            return builder.lessThanOrEqualTo(root.get(range.getField()), range.getTo());
        }
        return null;
    }
}