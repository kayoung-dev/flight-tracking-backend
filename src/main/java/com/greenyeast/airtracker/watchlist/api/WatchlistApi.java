package com.greenyeast.airtracker.watchlist.api;

import com.greenyeast.airtracker.watchlist.application.WatchlistService;
import com.greenyeast.airtracker.watchlist.payload.request.WatchlistCreateRequest;
import com.greenyeast.airtracker.watchlist.payload.response.WatchlistCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistApi implements WatchlistApiSpec {

    private final WatchlistService watchlistService;

    @Override
    @PostMapping
    public ResponseEntity<WatchlistCreateResponse> create(@RequestBody WatchlistCreateRequest req) {
        WatchlistCreateResponse response = watchlistService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
