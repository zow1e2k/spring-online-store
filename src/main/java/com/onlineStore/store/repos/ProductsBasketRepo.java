package com.onlineStore.store.repos;

import com.onlineStore.store.domain.ProductsBasket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductsBasketRepo extends CrudRepository <ProductsBasket, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM products_basket WHERE pb_basket_id = ?1 && pb_product_id = ?2", nativeQuery = true)
    void deleteProductFromBasket(Long basketId, Integer productId);

    @Query(value = "SELECT * FROM products_basket pb WHERE pb.pb_basket_id = ?1", nativeQuery = true)
    List<ProductsBasket> findPBByBasketId(Long id);
}
