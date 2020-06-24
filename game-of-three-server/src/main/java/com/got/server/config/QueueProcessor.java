package com.got.server.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import static com.got.server.config.QueueNames.CLIENT_QUEUE;
import static com.got.server.config.QueueNames.GAME_QUEUE;
import static com.got.server.config.QueueNames.PLAYER_LISTENER_QUEUE;
import static com.got.server.config.QueueNames.PLAYER_SENDER_QUEUE;

public interface QueueProcessor {

    @Input(PLAYER_LISTENER_QUEUE)
    SubscribableChannel playerListener();

    @Input(GAME_QUEUE)
    SubscribableChannel game();

    @Output(PLAYER_SENDER_QUEUE)
    MessageChannel playerSender();

    @Output(CLIENT_QUEUE)
    MessageChannel client();
}
