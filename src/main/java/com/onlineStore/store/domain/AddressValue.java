package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class AddressValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private AddressDesc addressDesc;

    private float value;
    private String valueString;
    private String measure;

    public AddressValue() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}