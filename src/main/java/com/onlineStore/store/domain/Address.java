package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Address() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}