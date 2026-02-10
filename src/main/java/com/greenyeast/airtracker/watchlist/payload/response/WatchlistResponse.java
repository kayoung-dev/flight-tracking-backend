package com.greenyeast.airtracker.watchlist.payload.response;

public record WatchlistResponse(
        Long id,
        String icao24,
        String callsign,
        String originCountry,
        String nickname,
        boolean active
) {

}
