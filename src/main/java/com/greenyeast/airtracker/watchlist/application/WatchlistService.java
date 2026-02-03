package com.greenyeast.airtracker.watchlist.application;

import com.greenyeast.airtracker.watchlist.payload.request.WatchlistCreateRequest;
import com.greenyeast.airtracker.watchlist.payload.response.WatchlistCreateResponse;

public interface WatchlistService {

    WatchlistCreateResponse create(WatchlistCreateRequest request);

    // WatchlistItem getById(Long id);

    // WatchlistItem getByIcao24(String icao24);

    // List<WatchlistItem> getAllActive();     // 삭제되지 않은(active=true) 목록

    // List<WatchlistItem> getAll();           // 삭제되지 않은 전체(활성/비활성 포함)

    // WatchlistItem update(Long id, WatchlistUpdateRequest request);

    // void delete(Long id);                   // soft delete
}
