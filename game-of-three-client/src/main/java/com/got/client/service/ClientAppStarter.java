package com.got.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ClientAppStarter
        implements ApplicationRunner {

    private final PlayerService playerService;

    @Override
    public void run(ApplicationArguments arg0) {
        playerService.joinGame();
    }

}
