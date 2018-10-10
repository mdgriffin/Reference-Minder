package com.mdgriffin.referenceminder.entity;

public enum ReferenceType {
    CONFERENCE_PAPER ("Conference Paper"),
    BOOK_SECTION ("Book Section"),
    JOURNAL_ARTICLE ("Journal Article"),
    WEBSITE ("Website");

    private String displayName;

    ReferenceType (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
