package com.onlineStore.store.repos;

import com.onlineStore.store.domain.ProductDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDescRepo extends JpaRepository<ProductDesc, Long> {
    @Query(value = "SELECT * FROM product_desc pd WHERE pd.product_id = ?1", nativeQuery = true)
    ProductDesc findProductDescByProductId(Integer id);

    @Query(value = "SELECT * FROM product_desc pd WHERE pd.name = ?1", nativeQuery = true)
    ProductDesc findProductDescByDescName(String name);

}