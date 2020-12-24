package com.onlineStore.store.repos;

import com.onlineStore.store.domain.ProductValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductValueRepo extends JpaRepository<ProductValue, Long> {
    @Query(value = "SELECT * FROM product_value pv WHERE pv.product_desc_id = ?1", nativeQuery = true)
    ProductValue findProductValueByProductDescId(Long id);

}
