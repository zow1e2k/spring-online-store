package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class ProducerValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private ProducerDesc producerDesc;

    private float value;
    private String measure;

    public ProducerValue() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
