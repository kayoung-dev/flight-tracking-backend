package com.greenyeast.airtracker.watchlist.payload.request;

public class WatchlistUpdateRequest {

    // 부분 수정(PATCH)용: null이면 변경 안 함
    private String callsign;
    private String originCountry;
    private String nickname;
    private Boolean active;
    private String note;
}
