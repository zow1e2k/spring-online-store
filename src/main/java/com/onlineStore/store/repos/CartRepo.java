package com.onlineStore.store.repos;

import com.onlineStore.store.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository <Cart, Long> {
}
