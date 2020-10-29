package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
}
