package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Cart() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
