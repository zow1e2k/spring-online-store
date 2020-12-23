package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class ProductDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product;

    private String name;

    public ProductDesc() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}