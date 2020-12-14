package com.onlineStore.store.repos;

import com.onlineStore.store.domain.ProductsCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductsCartRepo extends CrudRepository <ProductsCart, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM products_cart WHERE pc_cart_id = ?1 && pc_product_id = ?2", nativeQuery = true)
    void deleteProductFromCart(Long cartID, Integer productId);

    @Query(value = "SELECT * FROM products_cart pc WHERE pc.pc_cart_id = ?1", nativeQuery = true)
    List<ProductsCart> findPCByCartId(Long id);
}
