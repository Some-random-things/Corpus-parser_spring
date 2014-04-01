package com.imilkaeu.sprcrp.models;

import javax.persistence.*;

/**
 * Created by imilka on 27.12.13.
 */
@Entity
@Table(name = "hashqueries")
public class HashQuery {

    @Id
    @GeneratedValue
    long id;

    @Basic
    String hash;

    @Basic
    String query;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
