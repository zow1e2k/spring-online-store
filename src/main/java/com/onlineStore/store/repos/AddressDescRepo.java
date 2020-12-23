package com.onlineStore.store.repos;

import com.onlineStore.store.domain.AddressDesc;
import com.onlineStore.store.domain.AddressValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressDescRepo extends JpaRepository<AddressDesc, Long> {
    @Query(value = "SELECT * FROM address_desc ad WHERE ad.address_id = ?1", nativeQuery = true)
    AddressValue findAddressValueByAddressName(String name);

}
