package com.got.server.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "client.queue.names")
public class QueueConfig {

    public String clientQueue;
    public String gameQueue;
    public String playerListenerQueue;
    public String playerSenderQueue;
}
