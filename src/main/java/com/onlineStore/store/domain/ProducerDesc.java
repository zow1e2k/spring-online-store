package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class ProducerDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Producer producer;

    private String name;

    public ProducerDesc() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
