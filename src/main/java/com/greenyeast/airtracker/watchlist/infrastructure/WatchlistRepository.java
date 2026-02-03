package com.greenyeast.airtracker.watchlist.infrastructure;

import com.greenyeast.airtracker.watchlist.domain.WatchlistItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<WatchlistItem, Long> {
    boolean existsByIcao24AndDeletedAtIsNull(String icao24);

    Optional<WatchlistItem> findByIdAndDeletedAtIsNull(Long id);

    Optional<WatchlistItem> findByIcao24AndDeletedAtIsNull(String icao24);

    List<WatchlistItem> findAllByDeletedAtIsNullOrderByIdDesc();

    List<WatchlistItem> findAllByActiveTrueAndDeletedAtIsNullOrderByIdDesc();
}
