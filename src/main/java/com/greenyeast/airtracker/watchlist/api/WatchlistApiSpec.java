package com.greenyeast.airtracker.watchlist.api;

import com.greenyeast.airtracker.watchlist.payload.request.WatchlistCreateRequest;
import com.greenyeast.airtracker.watchlist.payload.response.WatchlistCreateResponse;
import com.greenyeast.airtracker.watchlist.payload.response.WatchlistResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Watchlist", description = "Aircraft watchlist")
public interface WatchlistApiSpec {

    @Operation(summary = "워치리스트 항목 생성")
    ResponseEntity<WatchlistCreateResponse> create(WatchlistCreateRequest req);
    
    @Operation(summary = "워치리스트 전체 조회")
    public List<WatchlistResponse> getAllWatchlists();
}
