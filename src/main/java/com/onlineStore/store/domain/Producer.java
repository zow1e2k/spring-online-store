package com.onlineStore.store.domain;

import javax.persistence.*;

@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @ManyToOne
    private Address address;

    private float rating;

    public Producer(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRating(float value) {
        this.rating = value;
    }

    public float getRating() {
        return this.rating;
    }

}
