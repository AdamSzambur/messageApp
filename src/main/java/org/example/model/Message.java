package org.example.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class Message {

    private Long id;
    private String message;
    private Date created;
    private String author;
    private Map<Long, Comment> comments = new HashMap<>();
    private List<Link> links = new ArrayList<>();

    public Message(Long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.created = new Date();
    }
    public Message(){
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
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

    @JsonbTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public void addLink(String uri, String ref) {
        // first we need to check that link ref to rel is not available
        if (links.stream().filter(l->l.getRel().equals(ref)).count() == 0) {
            this.links.add(new Link(uri,ref));
        }
    }
}
