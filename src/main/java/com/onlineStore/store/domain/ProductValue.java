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
    private String valueStr;
    private String measure;

    public ProductValue() { }

    public ProductValue(ProductDesc productDesc, String measure, String valueStr) {
        this.productDesc = productDesc;
        this.measure = measure;
        this.valueStr = valueStr;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getMeasure() {
        return this.measure;
    }

    public void setValueStr(String value) {
        this.valueStr = value;
    }

    public String getValueStr() {
        return this.valueStr;
    }
}
