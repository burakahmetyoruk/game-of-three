package com.got.client.event;

import com.got.client.domain.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GameStatusChanged implements BaseEvent {
    private String gameId;
    private GameStatus status;
}
