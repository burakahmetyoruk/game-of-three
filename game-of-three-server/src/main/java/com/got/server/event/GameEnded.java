package com.got.server.event;

import com.got.server.domain.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GameEnded implements BaseEvent {
    private String gameId;
    private final GameStatus status = GameStatus.ENDED;
    private String winner;
}
