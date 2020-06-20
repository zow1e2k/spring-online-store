package com.onlineStore.store.domain;
import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tag;
    private String text;

    public Message() { }

    public Message(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }
}
