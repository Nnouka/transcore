package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
