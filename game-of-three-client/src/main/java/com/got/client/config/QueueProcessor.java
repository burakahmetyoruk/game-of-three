package com.got.client.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import static com.got.client.config.QueueNames.CLIENT_QUEUE;
import static com.got.client.config.QueueNames.GAME_QUEUE;
import static com.got.client.config.QueueNames.PLAYER_LISTENER_QUEUE;
import static com.got.client.config.QueueNames.PLAYER_SENDER_QUEUE;

public interface QueueProcessor {

    @Input(PLAYER_LISTENER_QUEUE)
    SubscribableChannel playerListener();

    @Output(GAME_QUEUE)
    MessageChannel game();

    @Output(PLAYER_SENDER_QUEUE)
    MessageChannel playerSender();

    @Input(CLIENT_QUEUE)
    SubscribableChannel client();
}
