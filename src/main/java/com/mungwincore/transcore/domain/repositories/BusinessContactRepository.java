package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.BusinessContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessContactRepository extends JpaRepository<BusinessContact, Long> {
}
