package com.greenyeast.airtracker.watchlist.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.greenyeast.airtracker.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "watchlist_item")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WatchlistItem extends BaseEntity {

    // OpenSky: ICAO24 (hex 6자리)
    @Column(nullable = false, length = 6)
    private String icao24;

    // OpenSky: callsign (없을 수도 있음)
    @Column(length = 8)
    private String callsign;

    // OpenSky: origin_country (표시용)
    @Column(name = "origin_country", length = 64)
    private String originCountry;

    @Column(length = 50)
    private String nickname;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(length = 255)
    private String note;

    @Builder
    public WatchlistItem(String icao24, String callsign, String originCountry, String nickname, String note) {
        this.icao24 = normalizeIcao24(icao24);
        this.callsign = normalizeCallsign(callsign);
        this.originCountry = safeTrim(originCountry);
        this.nickname = safeTrim(nickname);
        this.note = safeTrim(note);
        this.active = true;
    }

    public void update(String callsign, String originCountry, String nickname, Boolean active, String note) {
        if (callsign != null) this.callsign = normalizeCallsign(callsign);
        if (originCountry != null) this.originCountry = safeTrim(originCountry);
        if (nickname != null) this.nickname = safeTrim(nickname);
        if (active != null) this.active = active;
        if (note != null) this.note = safeTrim(note);
    }

    // (선택) 삭제를 "소멸" 개념으로 처리하고 싶을 때
    public void delete() {
        softDelete();          // BaseEntity의 deletedAt 설정
        this.active = false;   // 추적도 같이 비활성화
    }

    private static String normalizeIcao24(String v) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException("icao24는 필수입니다.");
        String s = v.trim().toLowerCase();
        if (!s.matches("^[0-9a-f]{6}$")) {
            throw new IllegalArgumentException("icao24 형식이 올바르지 않습니다. (예: 71c123)");
        }
        return s;
    }

    private static String normalizeCallsign(String v) {
        if (v == null || v.isBlank()) return null;
        String s = v.trim().replaceAll("\\s+", "").toUpperCase();
        return s.isEmpty() ? null : s;
    }

    private static String safeTrim(String v) {
        if (v == null) return null;
        String s = v.trim();
        return s.isEmpty() ? null : s;
    }
}
