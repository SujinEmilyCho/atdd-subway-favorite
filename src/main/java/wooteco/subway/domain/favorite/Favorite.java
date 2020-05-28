package wooteco.subway.domain.favorite;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Favorite {
    @Id
    Long id;
    @NotNull
    Long preStation;
    @NotNull
    Long station;

    public Favorite() {
    }

    public Favorite(Long preStation, Long station) {
        this(null, preStation, station);
    }

    public Favorite(Long id, Long preStation, Long station) {
        // Todo: NullPointerException advice 추가
        Objects.requireNonNull(preStation, "즐겨찾기: 출발역이 null일 수 없습니다.");
        Objects.requireNonNull(station, "즐겨찾기: 도착역이 null일 수 없습니다.");

        this.id = id;
        this.preStation = preStation;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public Long getPreStation() {
        return preStation;
    }

    public Long getStation() {
        return station;
    }

    public boolean isSameId(Long favoriteId) {
        return this.id.equals(favoriteId);
    }
}
