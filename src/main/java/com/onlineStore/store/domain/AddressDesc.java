package com.onlineStore.store.domain;
import javax.persistence.*;

@Entity
public class AddressDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Address address;

    private String name;

    public AddressDesc() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAddress() {
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }
}
