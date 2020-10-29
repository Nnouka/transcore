package com.mungwincore.transcore.domain.repositories;

import com.mungwincore.transcore.domain.models.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, String> {
}
