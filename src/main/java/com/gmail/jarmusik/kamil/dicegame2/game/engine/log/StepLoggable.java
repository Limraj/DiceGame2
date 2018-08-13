/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.log;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;

/**
 *
 * @author Kamil-Tomasz
 */
public interface StepLoggable {
    void loadingEngine();
    void okEngine();
    
    void startStepLog(GamePlayer player, GameResults results) throws PlayerHasNotBeenAddedToGameException;
    void stepLog(RollDicesResult result, GameFlow flow);
    void endStepLog(GamePlayer player, GameResults results) throws PlayerHasNotBeenAddedToGameException;
}
