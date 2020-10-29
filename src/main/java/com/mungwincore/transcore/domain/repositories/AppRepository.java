package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findFirstByKey(String key);
}
