package com.onlineStore.store.domain;
import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tag;
    private String brandName;
    private String name;
    private String text;
    private int difficulty;
    private float price;

    @OneToOne
    private Box box;

    @OneToOne
    private Producer producer;

    private String filename;

    public Product() { }

    public Product(String tag, String brandName, String text, float price) {
        this.tag = tag;
        this.brandName = brandName;
        this.text = text;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
