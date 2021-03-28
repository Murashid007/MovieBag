package com.greedygame.moviebag.model;

public class Review {
    private String author;
    private String content;
    private String created_at;
    private AuthorDetails author_details;

    public AuthorDetails getAuthor_details() {
        return author_details;
    }

    public void setAuthor_details(AuthorDetails author_details) {
        this.author_details = author_details;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
