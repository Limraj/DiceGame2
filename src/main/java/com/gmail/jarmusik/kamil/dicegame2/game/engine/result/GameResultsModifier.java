/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.math.BigDecimal;


/**
 *
 * @author Kamil-Tomasz
 */
public interface GameResultsModifier {
    void addPointsFor(GamePlayer player, BigDecimal points) throws GameException;
    void incrementWinningTurnFor(GamePlayer player) throws GameException;
    void incrementTurnFor(GamePlayer player) throws GameException;
    void reset();
    GameResults newGameResults();
}
