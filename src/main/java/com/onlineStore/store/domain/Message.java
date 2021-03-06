package com.onlineStore.store.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Transient
    private List<ReplyMessage> replies;

    public Message() { }

    public Message(String text, User user, Product product) {
        this.text = text;
        this.author = user;
        this.product = product;
    }

    public List<ReplyMessage> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyMessage> replies) {
        this.replies = replies;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorname() {
        return this.author != null ? this.author.getUsername() : "None";
    }
}
