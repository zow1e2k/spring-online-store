package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class BoxDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Box box;

    private String name;

    public BoxDesc() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}