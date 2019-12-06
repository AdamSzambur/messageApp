package org.example.model;

import java.util.Date;

public class Comment {
    private Long id;
    private String message;
    private Date created;
    private String author;

    public Comment(Long id, String message, Date date, String author) {
        this.id = id;
        this.message = message;
        this.created = new Date();
        this.author = author;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
