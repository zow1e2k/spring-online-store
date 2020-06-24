package com.onlineStore.store.domain;
import javax.persistence.*;

@Entity
public class ReplyMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mainMessage_id")
    private Message mainMessage;

    public ReplyMessage() { }

    public ReplyMessage(String text, User user, Message msg) {
        this.text = text;
        this.author = user;
        this.mainMessage = msg;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Message getMainMessage() {
        return mainMessage;
    }

    public void setMainMessage(Message mainMessage) {
        this.mainMessage = mainMessage;
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

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorname() {
        return this.author != null ? this.author.getUsername() : "None";
    }
}
