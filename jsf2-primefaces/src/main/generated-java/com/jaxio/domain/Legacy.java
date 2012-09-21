/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/Entity.e.vm.java
 */
package com.jaxio.domain;

import javax.xml.bind.annotation.XmlTransient;
import com.jaxio.domain.PersistableHashBuilder;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Legacy implements Identifiable<LegacyPk>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Legacy.class);

    // Composite primary key
    private LegacyPk legacyPk = new LegacyPk();

    // Raw attributes
    private String extraInfo; // not null

    // ---------------------------
    // Constructors
    // ---------------------------

    public Legacy() {
    }

    public Legacy(LegacyPk primaryKey) {
        setId(primaryKey);
    }

    // ---------------------------
    // Identifiable implementation
    // ---------------------------

    @Override
    @Transient
    @XmlTransient
    public LegacyPk getId() {
        return getLegacyPk();
    }

    @Override
    public void setId(LegacyPk legacyPk) {
        setLegacyPk(legacyPk);
    }

    @Override
    @Transient
    @XmlTransient
    public boolean isIdSet() {
        return isLegacyPkSet();
    }

    // -----------------------
    // Composite Primary Key
    // -----------------------

    /**
     * Returns the composite primary key.
     */
    @EmbeddedId
    public LegacyPk getLegacyPk() {
        return legacyPk;
    }

    /**
     * Set the composite primary key.
     * @param legacyPk the composite primary key.
     */
    public void setLegacyPk(LegacyPk legacyPk) {
        this.legacyPk = legacyPk;
    }

    /**
     * Tells whether or not this instance has a non empty composite primary key set.
     * @return true if a non empty primary key is set, false otherwise
     */
    @Transient
    @XmlTransient
    public boolean isLegacyPkSet() {
        return getLegacyPk() != null && getLegacyPk().isLegacyPkSet();
    }

    /**
     * Helper method to set directly the code into the LegacyPk corresponding field.
     * todo document $pkAttribute.comment
     * @param code the code
     */
    public void setCode(String code) {
        if (getLegacyPk() == null) {
            setLegacyPk(new LegacyPk());
        }

        getLegacyPk().setCode(code);
    }

    /**
     * Helper method to get directly the code from the entity.root.primaryKey.type corresponding field.
     * @return the code
     */
    @Transient
    @XmlTransient
    @Size(max = 8)
    @NotEmpty
    public String getCode() {
        return getLegacyPk() != null ? getLegacyPk().getCode() : null;
    }

    /**
     * Helper method to set directly the dept into the LegacyPk corresponding field.
     * todo document $pkAttribute.comment
     * @param dept the dept
     */
    public void setDept(Integer dept) {
        if (getLegacyPk() == null) {
            setLegacyPk(new LegacyPk());
        }

        getLegacyPk().setDept(dept);
    }

    /**
     * Helper method to get directly the dept from the entity.root.primaryKey.type corresponding field.
     * @return the dept
     */
    @Transient
    @XmlTransient
    @NotNull
    public Integer getDept() {
        return getLegacyPk() != null ? getLegacyPk().getDept() : null;
    }

    /**
     * Helper method to set directly the name into the LegacyPk corresponding field.
     * todo document $pkAttribute.comment
     * @param name the name
     */
    public void setName(String name) {
        if (getLegacyPk() == null) {
            setLegacyPk(new LegacyPk());
        }

        getLegacyPk().setName(name);
    }

    /**
     * Helper method to get directly the name from the entity.root.primaryKey.type corresponding field.
     * @return the name
     */
    @Transient
    @XmlTransient
    @Size(max = 16)
    @NotEmpty
    public String getName() {
        return getLegacyPk() != null ? getLegacyPk().getName() : null;
    }

    // -------------------------------
    // Getter & Setter
    // -------------------------------

    // -- [extraInfo] ------------------------

    @Size(max = 100)
    @NotEmpty
    @Column(name = "EXTRA_INFO", nullable = false, length = 100)
    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
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
        return this == other || (other instanceof Legacy && hashCode() == other.hashCode());
    }

    private PersistableHashBuilder persistableHashBuilder = new PersistableHashBuilder();

    @Override
    public int hashCode() {
        return persistableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this Legacy instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("extraInfo", getExtraInfo()) //
                .toString();
    }
}