package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class BoxValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private BoxDesc boxDesc;

    private float value;
    private String measure;

    public BoxValue() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}