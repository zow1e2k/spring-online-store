package com.onlineStore.store.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User producer;

    @OneToOne
    private Product product;

    @OneToOne
    private User moderator;

    private boolean isAgreed;
    private Date date;

    public Statement() {
    }

    public Statement(User producer, Product product) {
        this.producer = producer;
        this.product = product;
        this.isAgreed = false;
        this.date = new Date(System.currentTimeMillis());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return formatter.format(date);
    }

    public boolean isAgreed() {
        return this.isAgreed;
    }

    public Product getProduct() {
        return this.product;
    }

}

