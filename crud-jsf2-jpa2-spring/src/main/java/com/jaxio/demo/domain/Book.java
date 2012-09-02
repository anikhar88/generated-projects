/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/Entity.e.vm.java
 */
package com.jaxio.demo.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.NotEmpty;
import com.google.common.base.Objects;
import com.jaxio.demo.domain.Account;
import com.jaxio.demo.domain.PersistableHashBuilder;

;

@Entity
@Table(name = "BOOK")
@Cache(usage = NONSTRICT_READ_WRITE)
@FilterDef(name = "myBookFilter", defaultCondition = "account_id = :currentAccountId ", parameters = @ParamDef(name = "currentAccountId", type = "org.hibernate.type.StringType"))
@Filter(name = "myBookFilter")
public class Book implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Book.class);

    // Raw attributes
    private Integer id; // pk
    private String title; // not null
    private Integer numberOfPages; // not null
    private Integer version;

    // Technical attributes for query by example
    private String accountId;

    // Many to one
    private Account account; // (accountId)

    // ---------------------------
    // Constructors
    // ---------------------------

    public Book() {
    }

    public Book(Integer primaryKey) {
        setId(primaryKey);
    }

    // -------------------------------
    // Getter & Setter
    // -------------------------------

    // -- [id] ------------------------

    @Column(name = "ID", precision = 10)
    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Transient
    public boolean isIdSet() {
        return id != null;
    }

    // -- [accountId] ------------------------

    @Column(name = "account_id", length = 32, insertable = false, updatable = false)
    public String getAccountId() {
        return accountId;
    }

    private void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    // -- [title] ------------------------

    @Size(max = 100)
    @NotEmpty
    @Column(name = "TITLE", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // -- [numberOfPages] ------------------------

    @NotNull
    @Column(name = "NUMBER_OF_PAGES", nullable = false, precision = 10)
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    // -- [version] ------------------------

    @Column(name = "VERSION", precision = 10)
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    // --------------------------------------------------------------------
    // Many to One support
    // --------------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Book.accountId ==> Account.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Cache(usage = NONSTRICT_READ_WRITE)
    @JoinColumn(name = "account_id")
    @ManyToOne(cascade = PERSIST, fetch = LAZY)
    public Account getAccount() {
        return account;
    }

    /**
     * Set the account without adding this Book instance on the passed account
     * If you want to preserve referential integrity we recommend to use
     * instead the corresponding adder method provided by {@link Account}
     */
    public void setAccount(Account account) {
        this.account = account;

        // We set the foreign key property so it can be used by our JPA search by Example facility.
        if (account != null) {
            setAccountId(account.getId());
        } else {
            setAccountId(null);
        }
    }

    /**
     * Set the default values.
     */
    public void initDefaultValues() {
    }

    /**
     * equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Book && hashCode() == other.hashCode());
    }

    private PersistableHashBuilder persistableHashBuilder = new PersistableHashBuilder();

    @Override
    public int hashCode() {
        return persistableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this Book instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("id", getId()) //
                .add("accountId", getAccountId()) //
                .add("title", getTitle()) //
                .add("numberOfPages", getNumberOfPages()) //
                .add("version", getVersion()) //
                .toString();
    }
}