package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
