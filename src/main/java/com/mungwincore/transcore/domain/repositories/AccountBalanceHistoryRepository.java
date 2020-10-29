package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.AccountBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceHistoryRepository extends JpaRepository<AccountBalanceHistory, Long> {
}
