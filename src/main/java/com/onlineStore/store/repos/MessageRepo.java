package com.onlineStore.store.repos;

import com.onlineStore.store.domain.Message;
import com.onlineStore.store.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository <Message, Long> {

    @Query(value = "SELECT * FROM message p WHERE p.id = ?1", nativeQuery = true)
    Message findByIntegerId(Integer id);

    @Query(value = "SELECT * FROM message m WHERE m.product_id = ?1", nativeQuery = true)
    List<Message> findForProduct(Integer product_id);

}
