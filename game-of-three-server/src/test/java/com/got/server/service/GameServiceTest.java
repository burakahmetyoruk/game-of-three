package com.got.server.service;

import com.got.server.domain.Game;
import com.got.server.domain.Player;
import com.got.server.event.PlayerMoved;
import com.got.server.infrastructure.GameRepository;
import com.got.server.infrastructure.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameServiceTest {

    private static final int WINNER_NUMBER = 1;

    @Autowired
    private GameService gameService;

    @MockBean
    private GameEventHandler gameEventHandler;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private PlayerRepository playerRepository;


    @Test
    public void should_end_game_when_opponent_send_winning_number() {
        final String gameId = UUID.randomUUID().toString();
        final String playerId = UUID.randomUUID().toString();
        final PlayerMoved playerMoved = new PlayerMoved(playerId,
                gameId,
                WINNER_NUMBER);

        when(gameRepository.findGame(gameId)).thenReturn(new Game(Boolean.FALSE));
        when(playerRepository.findPlayer(playerId)).thenReturn(new Player());

        gameService.playerMove(playerMoved);

        verify(gameEventHandler).gameEnded(any());
        verify(gameEventHandler, times(0)).playerMoved(any());
    }

    @Test
    public void should_end_game_when_player_reached_winning_number() {
        final String gameId = UUID.randomUUID().toString();
        final String playerId = UUID.randomUUID().toString();
        final PlayerMoved playerMoved = new PlayerMoved(playerId,
                gameId,
                WINNER_NUMBER * 3);

        final Game game = new Game(Boolean.FALSE);
        when(gameRepository.findGame(game.getId())).thenReturn(game);
        when(gameRepository.findGame(gameId)).thenReturn(game);
        when(playerRepository.findPlayer(anyString())).thenReturn(new Player());

        gameService.playerMove(playerMoved);

        verify(gameEventHandler).gameEnded(any());
        verify(gameEventHandler, times(0)).playerMoved(any());
    }

    @Test
    public void should_throw_illegal_state_exception_when_max_allowed_player_number_reached() {
        final Game game = new Game(Boolean.TRUE);
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        when(gameRepository.getGame()).thenReturn(game);
        Assertions.assertThrows(IllegalStateException.class, () -> gameService.addPlayer(new Player()));
    }

    @Test
    public void should_start_game_and_make_initial_move() {
        final Game game = new Game(Boolean.FALSE);
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        when(gameRepository.getGame()).thenReturn(game);
        gameService.start();

        verify(gameEventHandler).gameStarted(any());
        verify(gameEventHandler).playerMoved(any());
    }
}