package com.greenyeast.airtracker.watchlist.application;

import com.greenyeast.airtracker.watchlist.domain.WatchlistItem;
import com.greenyeast.airtracker.watchlist.infrastructure.WatchlistRepository;
import com.greenyeast.airtracker.watchlist.payload.request.WatchlistCreateRequest;
import com.greenyeast.airtracker.watchlist.payload.request.WatchlistUpdateRequest;
import com.greenyeast.airtracker.watchlist.payload.response.WatchlistCreateResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class WatchlistServiceImpl implements WatchlistService {
    private final WatchlistRepository watchlistRepository;

    @Override
    public WatchlistCreateResponse create(WatchlistCreateRequest request) {
        String icao24 = normalizeIcao24(request.getIcao24());

        // icao24는 unique라서 사전에 중복 체크(에러 메시지 깔끔하게)
        if (watchlistRepository.existsByIcao24AndDeletedAtIsNull(icao24)) {
            throw new IllegalArgumentException("이미 등록된 icao24 입니다: " + icao24);
        }

        WatchlistItem item = WatchlistItem.builder()
                .icao24(icao24)
                .callsign(request.getCallsign())
                .originCountry(request.getOriginCountry())
                .nickname(request.getNickname())
                .note(request.getNote())
                .build();

        WatchlistItem saved = watchlistRepository.save(item);
        return WatchlistCreateResponse.builder()
                .id(saved.getId())
                .icao24(saved.getIcao24())
                .callsign(saved.getCallsign())
                .originCountry(saved.getOriginCountry())
                .nickname(saved.getNickname())
                .active(saved.isActive())
                .build();
    }
/*
    @Override
    @Transactional
    public WatchlistItem getById(Long id) {
        return watchlistRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("watchlist_item not found. id=" + id));
    }

    @Override
    @Transactional
    public WatchlistItem getByIcao24(String icao24) {
        String key = normalizeIcao24(icao24);
        return watchlistRepository.findByIcao24AndDeletedAtIsNull(key)
                .orElseThrow(() -> new IllegalArgumentException("watchlist_item not found. icao24=" + key));
    }

    @Override
    @Transactional
    public List<WatchlistItem> getAllActive() {
        return watchlistRepository.findAllByActiveTrueAndDeletedAtIsNullOrderByIdDesc();
    }

    @Override
    @Transactional
    public List<WatchlistItem> getAll() {
        return watchlistRepository.findAllByDeletedAtIsNullOrderByIdDesc();
    }

    @Override
    public WatchlistItem update(Long id, WatchlistUpdateRequest request) {
        WatchlistItem item = getById(id);

        // icao24는 보통 식별자라 변경 금지(설계 안정성)
        // 필요하면 request에 icao24 넣고 "변경 허용" 로직을 추가하면 됨

        item.update(
                request.getCallsign(),
                request.getOriginCountry(),
                request.getNickname(),
                request.getActive(),
                request.getNote()
        );

        // 영속 상태라 save() 없어도 되지만, 명시적으로 둬도 OK
        return watchlistRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        WatchlistItem item = getById(id);
        item.delete(); // BaseEntity.softDelete() + active=false
        watchlistRepository.save(item);
    }

 */

    // ===== util =====
    private String normalizeIcao24(String v) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException("icao24는 필수입니다.");
        String s = v.trim().toLowerCase();
        if (!s.matches("^[0-9a-f]{6}$")) {
            throw new IllegalArgumentException("icao24 형식이 올바르지 않습니다. (예: 71c123)");
        }
        return s;
    }
}
