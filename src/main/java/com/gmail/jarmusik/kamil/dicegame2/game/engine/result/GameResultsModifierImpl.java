/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import java.math.BigDecimal;
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import lombok.extern.java.Log;

/**
 *
 * @author Kamil-Tomasz
 */
@Log
public final class GameResultsModifierImpl implements GameResultsModifier {
    
    private final Map<GamePlayer, PlayerResultModifier> modifiers = new LinkedHashMap<>();
    private final Map<String, PlayerResultModifier> modifierForNames = new LinkedHashMap<>();

    public GameResultsModifierImpl(Set<GamePlayer> players) {
        players.forEach((GamePlayer player) -> {
            PlayerResultModifier mod = new PlayerResultModifierImpl();
            modifiers.put(player, mod);
            modifierForNames.put(player.getName(), mod);
        });
    }
    
    public GameResultsModifierImpl(GameResults gameResults) {
        gameResults.getPeleton().forEach((player) -> {
            try {
                PlayerResultModifier mod = new PlayerResultModifierImpl(gameResults.getPlayerResultFor(player));
                modifiers.put(player, mod);
                modifierForNames.put(player.getName(), mod);
            } catch (PlayerHasNotBeenAddedToGameException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public boolean addPointsFor(GamePlayer player, BigDecimal points) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        BigDecimal before = modifiers.get(player).toPlayerResult().getPoints();
        modifiers.get(player).addPoints(points);
        BigDecimal after = modifiers.get(player).toPlayerResult().getPoints();
        return after.subtract(before).equals(points);
    }

    @Override
    public boolean addTurnFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        int before = modifiers.get(player).toPlayerResult().getNumberTurnCurrent();
        int after = modifiers.get(player).incrementAndGetNumberTurnCurrent();
        return after == before + 1;
    }

    @Override
    public GameResults toGameResults(RulesOfWinning rulesOfWinning) {
        return new GameResultsImpl(modifiers , rulesOfWinning);
    }
    
    @Override
    public boolean addWinningTurnFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        PlayerResultModifier mod = modifiers.get(player);
        int before = mod.toPlayerResult().getNumberWinningTurns();
        int after = mod.incrementAndGetNumberWinningTurns();
        return after == before + 1;
    }

    @Override
    public void reset() {
        modifiers.entrySet().forEach((entry) -> {
            entry.getValue().reset();
        });
    }
    
    private void throwIfPlayerHasNotBeenAddedToGame(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        if(!modifiers.containsKey(player))
            throw new PlayerHasNotBeenAddedToGameException(player);
        if(!modifierForNames.containsKey(player.getName()))
            throw new PlayerHasNotBeenAddedToGameException(player);
    }
}
