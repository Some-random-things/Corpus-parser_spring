package com.imilkaeu.sprcrp.models;

import javax.persistence.*;

/**
 * Created by imilka on 27.12.13.
 */
@Entity
@Table(name = "meta")
public class Meta {

    @Id
    @GeneratedValue
    long id;

    @Basic
    String property;

    @Basic
    String dbfield;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDbfield() {
        return dbfield;
    }

    public void setDbfield(String dbfield) {
        this.dbfield = dbfield;
    }
}
