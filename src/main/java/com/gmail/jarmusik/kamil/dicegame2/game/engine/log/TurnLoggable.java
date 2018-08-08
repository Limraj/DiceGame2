/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.log;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;

/**
 *
 * @author Kamil-Tomasz
 */
public interface TurnLoggable {
    void startTurnLog(GamePlayer player, GameResults resultsGame) throws PlayerHasNotBeenAddedToGameException;
    void turnLog(int numberOfRollCurrent, int pointsRoll, GameFlow master);
    void endTurnLog(GamePlayer player, GameResults resultsGame) throws PlayerHasNotBeenAddedToGameException;
}
