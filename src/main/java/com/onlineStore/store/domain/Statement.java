package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Producer producer;

    @OneToOne
    private Product product;

    @OneToOne
    private User moderator;

    private boolean isAgreed;
    private String date;

    public Statement() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}

