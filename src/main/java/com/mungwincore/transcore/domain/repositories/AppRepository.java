package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findFirstByKey(String key);

    @Query("SELECT a FROM App a where a.token1 = :t or a.token2 = :t")
    List<App> findByTokens(String t);
}
