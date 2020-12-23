package com.onlineStore.store.repos;

import com.onlineStore.store.domain.ProductStatement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ProductStatementRepo extends CrudRepository <ProductStatement, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_statement WHERE ps_statement_id = ?1 && ps_product_id = ?2", nativeQuery = true)
    void deleteProductFromStatement(Long statementID, Integer productID);

    @Query(value = "SELECT * FROM product_statement ps WHERE ps.ps_statement_id = ?1", nativeQuery = true)
    ProductStatement findPSByStatementID(Long id);
}
