package com.onlineStore.store.repos;

import com.onlineStore.store.domain.Basket;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepo extends CrudRepository <Basket, Long> {
}
