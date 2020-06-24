package com.got.server.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface QueueProcessor {
    String clientQueue = "clientQueue";
    String gameQueue= "gameQueue";
    String playerListenerQueue= "playerListenerQueue";
    String playerSenderQueue= "playerSenderQueue";


    @Input(playerListenerQueue)
    SubscribableChannel playerListener();

    @Input(gameQueue)
    SubscribableChannel game();

    @Output(playerSenderQueue)
    MessageChannel playerSender();

    @Output(clientQueue)
    MessageChannel client();
}
