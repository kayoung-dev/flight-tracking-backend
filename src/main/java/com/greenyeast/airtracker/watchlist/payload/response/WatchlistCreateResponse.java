package com.greenyeast.airtracker.watchlist.payload.response;

import lombok.Builder;

@Builder
public record WatchlistCreateResponse(
        Long id,
        String icao24,
        String callsign,
        String originCountry,
        String nickname,
        boolean active
) {

}
