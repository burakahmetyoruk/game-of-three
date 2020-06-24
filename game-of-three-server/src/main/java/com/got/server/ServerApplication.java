package com.got.server;

import com.got.server.config.QueueProcessor;
import com.got.server.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ConfigurableApplicationContext;

@EnableBinding(QueueProcessor.class)
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(ServerApplication.class, args);
        GameService gameService = context.getBean(GameService.class);
        gameService.create();
    }

}
