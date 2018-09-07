/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameRuntimeException;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;


/**
 *
 * @author Kamil-Tomasz
 */
public interface GameResultsModifier {
    void addPointsFor(GamePlayer player, BigDecimal points);
    void incrementWinningTurnFor(GamePlayer player);
    void incrementTurnFor(GamePlayer player);
    void reset();
    GameResults snapshot();
    
    static GameResultsModifier newModifier(Set<GamePlayer> players, Comparator<PlayerResult> rulesOfWinning) {
        return new GameResultsModifierImpl(players, rulesOfWinning);
    }
}
