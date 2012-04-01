/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/repository/support/ByRangeSpecifications.p.vm.java
 */
package com.company.demo.repository.support;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * Helper to create {@link Specification} out of {@link Range}s.
 */
public class ByRangeSpecifications {

    public static <E> Specification<E> byRanges(final List<Range<E, ?>> ranges) {
        return new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = newArrayList();
                for (Range<E, ?> range : ranges) {
                    if (range.isSet()) {
                        Predicate rangePredicate = buildRangePredicate(range, root, builder);

                        if (rangePredicate != null) {
                            if (!range.isIncludeNullSet() || range.getIncludeNull() == FALSE) {
                                predicates.add(rangePredicate);
                            } else {
                                predicates.add(builder.or(rangePredicate, builder.isNull(root.get(range.getField()))));
                            }
                        }

                        // no range at all, let's take the opportunity to keep only null...
                        if (TRUE == range.getIncludeNull()) {
                            predicates.add(builder.isNull(root.get(range.getField())));
                        } else if (FALSE == range.getIncludeNull()) {
                            predicates.add(builder.isNotNull(root.get(range.getField())));
                        }
                    }
                }
                return predicates.isEmpty() ? builder.conjunction() : builder.and(toArray(predicates, Predicate.class));
            }

            private <D extends Comparable<? super D>> Predicate buildRangePredicate(Range<E, D> range, Root<E> root,
                    CriteriaBuilder builder) {
                if (range.isBetween()) {
                    return builder.between(root.get(range.getField()), range.getFrom(), range.getTo());
                } else if (range.isFromSet()) {
                    return builder.greaterThanOrEqualTo(root.get(range.getField()), range.getFrom());
                } else if (range.isToSet()) {
                    return builder.lessThanOrEqualTo(root.get(range.getField()), range.getTo());
                }
                return null;
            }
        };
    }
}