/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import java.math.BigDecimal;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameResultsModifier {
    boolean addPointsFor(GamePlayer player, BigDecimal points) throws PlayerHasNotBeenAddedToGameException;
    boolean addWinningTurnFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException;
    boolean addTurnFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException;
    void reset();
    GameResults toGameResults(RulesOfWinning rulesOfWinning);
}
