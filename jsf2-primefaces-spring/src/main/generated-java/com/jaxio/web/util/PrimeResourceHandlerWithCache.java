/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring:src/main/java/util/PrimeResourceHandlerWithCache.p.vm.java
 */
package com.jaxio.web.util;

import static com.google.common.collect.Maps.newConcurrentMap;

import java.util.Map;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;

import org.primefaces.application.PrimeResourceHandler;

/**
 * _HACK_
 * This class to circumvent performance limitations observed when using composite component.
 * Indeed, createResource is invoked over and over and consumes a lot of cpu.
 */
public class PrimeResourceHandlerWithCache extends PrimeResourceHandler {

    private Map<String, Resource> resCache = newConcurrentMap();

    public PrimeResourceHandlerWithCache(ResourceHandler wrapped) {
        super(wrapped);
    }

    @Override
    public Resource createResource(String resourceName, String libraryName) {
        String key = resourceName + "__" + libraryName;
        Resource resource = resCache.get(key);

        if (resource == null) {
            resource = super.createResource(resourceName, libraryName);
            if (resource != null) {
                resCache.put(key, resource);
            }
        }
        return resource;
    }
}