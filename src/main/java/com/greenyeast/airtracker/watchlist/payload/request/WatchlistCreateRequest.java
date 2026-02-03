package com.greenyeast.airtracker.watchlist.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WatchlistCreateRequest {
    // 필수
    private String icao24;

    // 선택
    private String callsign;
    private String originCountry;
    private String nickname;
    private String note;
}
