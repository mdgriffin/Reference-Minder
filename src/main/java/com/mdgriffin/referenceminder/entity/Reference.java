package com.mdgriffin.referenceminder.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reference {

    @Id
    private String id;

    private ReferenceType type;

    private String title;

    private ReferenceDate date;

    private ReferenceDate dateAccessed;

    private PageRange pages;

    private Set<Author> author = new HashSet<>();

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

    public ReferenceDate getDate() {
        return date;
    }

    public void setDate(ReferenceDate date) {
        this.date = date;
    }

    public ReferenceDate getDateAccessed() {
        return dateAccessed;
    }

    public void setDateAccessed(ReferenceDate dateAccessed) {
        this.dateAccessed = dateAccessed;
    }

    public PageRange getPages() {
        return pages;
    }

    public void setPages(PageRange pages) {
        this.pages = pages;
    }

    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
