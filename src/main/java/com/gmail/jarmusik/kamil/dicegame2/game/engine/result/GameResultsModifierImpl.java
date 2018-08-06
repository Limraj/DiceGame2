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

/**
 *
 * @author Kamil-Tomasz
 */
public final class GameResultsModifierImpl extends GameResultsImpl implements GameResultsModifier {

    public GameResultsModifierImpl(Set<GamePlayer> players, RulesOfWinning rulesOfWinning) {
        super(players, rulesOfWinning);
    }

    @Override
    public void addPointsFor(GamePlayer player, BigDecimal points) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        getResults().get(player).addPoints(points);
    }

    @Override
    public void addTurnFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        getResults().get(player).addTurn();
    }

    @Override
    public ResultsGame toGameResults() {
        return (ResultsGame) this;
    }
    
    @Override
    public void addWinningTurnFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        PlayerResultModifier mod = getResults().get(player);
        mod.addWinningTurn(mod.toPlayerResult().getCurrentTurnNumber());
    }

    @Override
    public void reset() {
        getResults().entrySet().forEach((entry) -> {
            entry.getValue().reset();
        });
    }
}
