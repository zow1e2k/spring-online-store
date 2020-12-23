package com.onlineStore.store.repos;

import com.onlineStore.store.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepo extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM address a WHERE a.id = ?1", nativeQuery = true)
    Address findByIntegerId(Integer id);

}
