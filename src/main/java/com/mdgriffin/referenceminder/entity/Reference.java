package com.mdgriffin.referenceminder.entity;

import org.springframework.data.annotation.Id;

public class Reference {

    @Id
    public String id;

    private String author;

    public Reference () {}

    public Reference (String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
