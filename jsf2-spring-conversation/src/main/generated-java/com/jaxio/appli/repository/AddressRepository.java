/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/repository/Repository.e.vm.java
 */
package com.jaxio.appli.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import com.jaxio.appli.domain.Address;
import com.jaxio.appli.repository.support.GenericRepository;

/**
 * {@link GenericRepository} for {@link Address} 
 */
@Named
@Singleton
public class AddressRepository extends GenericRepository<Address, Integer> {

    public AddressRepository() {
        super(Address.class);
    }

    @Override
    public Address getNew() {
        return new Address();
    }

    @Override
    public Address getNewWithDefaults() {
        return getNew().withDefaults();
    }
}