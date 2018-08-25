/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.FailedOperationException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Kamil-Tomasz
 */
final class GameResultsModifierImpl implements GameResultsModifier {
    
    private final Map<GamePlayer, PlayerResultModifier> modifiers = new LinkedHashMap<>();
    private final Comparator<PlayerResult> rulesOfWinning;

    GameResultsModifierImpl(Set<GamePlayer> players, Comparator<PlayerResult> rulesOfWinning) {
        players.forEach((GamePlayer player) -> {
            PlayerResultModifier mod = new PlayerResultModifierImpl();
            modifiers.put(player, mod);
        });
        this.rulesOfWinning = rulesOfWinning;
    }

    @Override
    public void addPointsFor(GamePlayer player, BigDecimal points) throws GameException {
        throwExceptionIfPlayerHasNotBeenAddedToGame(player);
        BigDecimal before = modifiers.get(player).newPlayerResult().getPoints();
        BigDecimal after = modifiers.get(player).addPoints(points);
        throwExceptionIfFalse(after.subtract(before).equals(points));
    }

    @Override
    public void incrementTurnFor(GamePlayer player) throws GameException {
        throwExceptionIfPlayerHasNotBeenAddedToGame(player);
        int before = modifiers.get(player).newPlayerResult().getNumberTurnCurrent();
        int after = modifiers.get(player).incrementAndGetNumberTurnCurrent();
        throwExceptionIfFalse(after == before + 1);
    }

    @Override
    public GameResults newGameResults() {
        return new GameResultsImpl(modifiers , rulesOfWinning);
    }
    
    @Override
    public void incrementWinningTurnFor(GamePlayer player) throws GameException {
        throwExceptionIfPlayerHasNotBeenAddedToGame(player);
        PlayerResultModifier mod = modifiers.get(player);
        int before = mod.newPlayerResult().getNumberWinningTurns();
        int after = mod.incrementAndGetNumberWinningTurns();
        throwExceptionIfFalse(after == before + 1);
    }

    @Override
    public void reset() {
        modifiers.entrySet().forEach((entry) -> {
            entry.getValue().reset();
        });
    }
    
    private void throwExceptionIfFalse(boolean condition) throws FailedOperationException {
        if(!condition)
            throw new FailedOperationException();
    }
    
    private void throwExceptionIfPlayerHasNotBeenAddedToGame(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        if(!modifiers.containsKey(player))
            throw new PlayerHasNotBeenAddedToGameException(player);
    }
}
