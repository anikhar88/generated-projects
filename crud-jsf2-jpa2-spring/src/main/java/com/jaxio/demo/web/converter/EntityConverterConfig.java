/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces:src/main/java/converter/EntityConverterConfig.p.vm.java
 */
package com.jaxio.demo.web.converter;

import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jaxio.demo.domain.Account;
import com.jaxio.demo.domain.Address;
import com.jaxio.demo.domain.Book;
import com.jaxio.demo.domain.Document;
import com.jaxio.demo.domain.Legacy;
import com.jaxio.demo.domain.LegacyPk;
import com.jaxio.demo.domain.Role;
import com.jaxio.demo.domain.more.MoreTypesDemo;
import com.jaxio.demo.repository.AccountRepository;
import com.jaxio.demo.repository.AddressRepository;
import com.jaxio.demo.repository.BookRepository;
import com.jaxio.demo.repository.DocumentRepository;
import com.jaxio.demo.repository.LegacyRepository;
import com.jaxio.demo.repository.RoleRepository;
import com.jaxio.demo.repository.more.MoreTypesDemoRepository;

/**
 * Responsible for creating Entity JSF converters.
 * Each converter provides a 'print' method to convert an entity instance to a friendly string representation (readable by humans...).
 */
@Configuration
public class EntityConverterConfig {

    // -- AccountConverter

    @Inject
    AccountRepository accountRepository;

    @Bean
    public AccountConverter accountConverter() {
        return new AccountConverter(accountRepository);
    }

    public class AccountConverter extends GenericJsfConverter<Account, String> {
        public AccountConverter(AccountRepository accountRepository) {
            super(accountRepository);
        }

        @Override
        public String print(Account account) {
            return account == null ? "" : "" + account.getUsername();
        }

    }

    // -- AddressConverter

    @Inject
    AddressRepository addressRepository;

    @Bean
    public AddressConverter addressConverter() {
        return new AddressConverter(addressRepository);
    }

    public class AddressConverter extends GenericJsfConverter<Address, Integer> {
        public AddressConverter(AddressRepository addressRepository) {
            super(addressRepository);
        }

        @Override
        public String print(Address address) {
            return address == null ? "" : "" + address.getCity();
        }

    }

    // -- BookConverter

    @Inject
    BookRepository bookRepository;

    @Bean
    public BookConverter bookConverter() {
        return new BookConverter(bookRepository);
    }

    public class BookConverter extends GenericJsfConverter<Book, Integer> {
        public BookConverter(BookRepository bookRepository) {
            super(bookRepository);
        }

        @Override
        public String print(Book book) {
            return book == null ? "" : "" + book.getTitle();
        }

    }

    // -- DocumentConverter

    @Inject
    DocumentRepository documentRepository;

    @Bean
    public DocumentConverter documentConverter() {
        return new DocumentConverter(documentRepository);
    }

    public class DocumentConverter extends GenericJsfConverter<Document, String> {
        public DocumentConverter(DocumentRepository documentRepository) {
            super(documentRepository);
        }

        @Override
        public String print(Document document) {
            return document == null ? "" : "" + document.getDocumentContentType() + "/" + document.getDocumentFileName();
        }

    }

    // -- LegacyConverter

    @Inject
    LegacyRepository legacyRepository;

    @Bean
    public LegacyConverter legacyConverter() {
        return new LegacyConverter(legacyRepository);
    }

    public class LegacyConverter extends GenericJsfConverter<Legacy, LegacyPk> {
        public LegacyConverter(LegacyRepository legacyRepository) {
            super(legacyRepository);
        }

        @Override
        public String print(Legacy legacy) {
            return legacy == null ? "" : "" + legacy.getExtraInfo();
        }

    }

    // -- MoreTypesDemoConverter

    @Inject
    MoreTypesDemoRepository moreTypesDemoRepository;

    @Bean
    public MoreTypesDemoConverter moreTypesDemoConverter() {
        return new MoreTypesDemoConverter(moreTypesDemoRepository);
    }

    public class MoreTypesDemoConverter extends GenericJsfConverter<MoreTypesDemo, Integer> {
        public MoreTypesDemoConverter(MoreTypesDemoRepository moreTypesDemoRepository) {
            super(moreTypesDemoRepository);
        }

        @Override
        public String print(MoreTypesDemo moreTypesDemo) {
            return moreTypesDemo == null ? "" : "" + moreTypesDemo.getNumberInt() + "/" + moreTypesDemo.getNumberLong() + "/" + moreTypesDemo.getNumberDouble();
        }

    }

    // -- RoleConverter

    @Inject
    RoleRepository roleRepository;

    @Bean
    public RoleConverter roleConverter() {
        return new RoleConverter(roleRepository);
    }

    public class RoleConverter extends GenericJsfConverter<Role, Integer> {
        public RoleConverter(RoleRepository roleRepository) {
            super(roleRepository);
        }

        @Override
        public String print(Role role) {
            return role == null ? "" : "" + role.getRoleName();
        }

    }
}