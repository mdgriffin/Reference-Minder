package com.mdgriffin.referenceminder.entity;

import org.springframework.data.annotation.Id;

import java.util.*;

public class Reference {

    @Id
    private String id;

    private ReferenceType type;

    private String title;

    private Date createdAt;

    private Date updatedAt;

    private Date date;

    private Date dateAccessed;

    private PageRange pages;

    private Set<Author> authors = new HashSet<>();

    Set<Tag> tags = new HashSet<>();

    public Reference () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReferenceType getType() {
        return type;
    }

    public void setType(ReferenceType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateAccessed() {
        return dateAccessed;
    }

    public void setDateAccessed(Date dateAccessed) {
        this.dateAccessed = dateAccessed;
    }

    public PageRange getPages() {
        return pages;
    }

    public void setPages(PageRange pages) {
        this.pages = pages;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
