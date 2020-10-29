package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
