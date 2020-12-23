package com.onlineStore.store.repos;

import com.onlineStore.store.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProducerRepo extends JpaRepository<Producer, Long> {
    @Query(value = "SELECT * FROM producer p WHERE p.id = ?1", nativeQuery = true)
    Producer findByIntegerId(Integer id);

}

