package com.onlineStore.store.repos;

import com.onlineStore.store.domain.Statement;
import com.onlineStore.store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepo extends JpaRepository<Statement, Long> {
    Statement findStatementById(Long id);

}