package com.got.server.service;


import com.got.server.domain.Game;
import com.got.server.domain.Player;
import com.got.server.event.GameEnded;
import com.got.server.event.GameStarted;
import com.got.server.event.PlayerMoved;
import com.got.server.infrastructure.GameConfig;
import com.got.server.infrastructure.GameRepository;
import com.got.server.infrastructure.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private static final int WINNER_NUMBER = 1;

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final GameEventHandler gameEventHandler;
    private final GameConfig gameConfig;
    private final Player player;

    public GameServiceImpl(final GameRepository gameRepository,
                           final PlayerRepository playerRepository,
                           final GameEventHandler gameEventHandler,
                           final GameConfig gameConfig) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gameEventHandler = gameEventHandler;
        this.gameConfig = gameConfig;
        this.player = new Player();
    }

    @Override
    public Game create() {
        boolean isManual = gameConfig.isManual();
        final Game game = new Game(isManual);
        game.addPlayer(player);
        player.joinGame();
        playerRepository.save(player);
        return gameRepository.saveGame(game);
    }

    @Override
    public boolean addPlayer(final Player player) {
        final Game game = gameRepository.getGame();
        boolean isAdded = game.addPlayer(player);
        if (isAdded) {
            playerRepository.save(player);
        }
        return isAdded;
    }

    @Override
    public void start() {
        final Game game = gameRepository.getGame();
        game.startGame();
        gameEventHandler.gameStarted(new GameStarted(game.getId()));
        final int initialMove = player.getInitialMove(game.isManual());
        gameEventHandler.playerMoved(new PlayerMoved(player.getId(), game.getId(), initialMove));
    }

    @Override
    public void playerMove(final PlayerMoved opponentMove) {
        final Game game = gameRepository.findGame(opponentMove.getGameId());
        if (isGameEnded(opponentMove)) {
            return;
        }
        final int nextMove = player.move(opponentMove.getNumber(), game.isManual());
        PlayerMoved playerMoved = new PlayerMoved(player.getId(), game.getId(), nextMove);
        if (isGameEnded(playerMoved)) {
            return;
        }
        gameEventHandler.playerMoved(playerMoved);
    }

    private boolean isGameEnded(final PlayerMoved playerMoved) {
        if (playerMoved.getNumber() == WINNER_NUMBER) {
            final Game game = gameRepository.findGame(playerMoved.getGameId());
            final Player winner = playerRepository.findPlayer(playerMoved.getPlayerId());
            game.endGame(winner);
            end(game);
            return true;
        }
        return false;
    }

    private void end(final Game game) {
        final GameEnded gameEnded = new GameEnded(game.getId(), game.getWinner().getId());
        gameRepository.endGame(game.getId(), game.getWinner());
        gameEventHandler.gameEnded(gameEnded);
    }
}
