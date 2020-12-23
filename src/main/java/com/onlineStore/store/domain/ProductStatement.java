package com.onlineStore.store.domain;
import javax.persistence.*;

@Entity
public class ProductStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ps_product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ps_cart_id")
    private Statement statement;

    public ProductStatement() { }

    public ProductStatement(Product product, Statement statement) {
        this.product = product;
        this.statement = statement;
    }

    public Statement getStatement() {
        return this.statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

