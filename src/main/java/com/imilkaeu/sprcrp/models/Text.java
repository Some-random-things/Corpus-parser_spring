package com.imilkaeu.sprcrp.models;

import javax.persistence.*;

/**
 * Created by imilka on 05.12.13.
 */
@Entity
@Table(name = "texts")
public class Text {
    @Id
    @GeneratedValue
    private long id;

    @Basic
    private String annot;

    @Basic
    private String author;

    @Basic
    private String textdate;

    @Basic
    private String editor;

    @Basic
    private String source;

    @Basic
    private String title;

    @Basic
    private String relativepath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnnot() {
        return annot;
    }

    public void setAnnot(String annot) {
        this.annot = annot;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTextdate() {
        return textdate;
    }

    public void setTextdate(String textdate) {
        this.textdate = textdate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelativepath() {
        return relativepath;
    }

    public void setRelativepath(String relativepath) {
        this.relativepath = relativepath;
    }
}
