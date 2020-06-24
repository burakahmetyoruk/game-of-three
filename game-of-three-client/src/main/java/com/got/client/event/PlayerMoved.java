package com.got.client.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlayerMoved implements BaseEvent {

    private String playerId;
    private String gameId;
    private int number;
}
