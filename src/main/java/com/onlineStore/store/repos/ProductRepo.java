package com.onlineStore.store.repos;

import com.onlineStore.store.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository <Product, Long> {
    @Query(value = "SELECT * FROM product p WHERE p.id = ?1", nativeQuery = true)
    Product findByIntegerId(Integer id);

    List<Product> findByTag(String tag);

    @Query(value = "SELECT DISTINCT p.tag FROM product p;", nativeQuery = true)
    List<String> findAllTags();

    @Query(value = "SELECT DISTINCT p.brand_name FROM product p;", nativeQuery = true)
    List<String> findAllBrandNames();

    @Query(value = "SELECT * FROM product p WHERE p.tag = ?1 AND p.brand_name = ?2", nativeQuery = true)
    List<Product> findByTagAndBrandName(String tag, String brandName);
}
