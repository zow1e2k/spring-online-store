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

    @Query(value = "SELECT DISTINCT p.car_model FROM product p;", nativeQuery = true)
    List<String> findAllCarModels();

    @Query(value = "SELECT * FROM product p WHERE p.tag = ?1 AND p.car_model = ?2", nativeQuery = true)
    List<Product> findByTagAndCarModel(String tag, String carModel);
}
