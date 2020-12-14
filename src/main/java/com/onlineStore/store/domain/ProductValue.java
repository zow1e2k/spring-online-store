package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class ProductValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private ProductDesc productDesc;

    private float value;
    private String measure;

    public ProductValue() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
