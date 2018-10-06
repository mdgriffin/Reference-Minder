package com.mdgriffin.referenceminder.entity;

public class Reference {

    private String author;

    public Reference () {}

    public Reference (String author) {
        this.author = author;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
