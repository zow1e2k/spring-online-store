package com.onlineStore.store.repos;

import com.onlineStore.store.domain.AddressValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressValueRepo extends JpaRepository<AddressValue, Long> {
    @Query(value = "SELECT value_string FROM address_value av WHERE av.address_desc_id = ?1", nativeQuery = true)
    String findAddressNameByAddressDescID(Long id);

}

