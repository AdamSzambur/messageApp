package org.example.model;

public class Link {
    private String link;
    private String rel;

    public Link() {
    }

    public Link(String uri, String rel) {
        this.rel = rel;
        this.link = uri;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }


}
