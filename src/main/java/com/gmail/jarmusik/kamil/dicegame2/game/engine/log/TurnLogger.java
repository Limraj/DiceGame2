/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.log;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import lombok.extern.java.Log;

/**
 *
 * @author Kamil-Tomasz
 */
public class TurnLogger implements TurnLoggable {

    @Override
    public void startStepLog(GamePlayer player, GameResults resultsGame) throws PlayerHasNotBeenAddedToGameException {
        PlayerResult result = resultsGame.getPlayerResultFor(player);
        System.out.println(String.format("Turn number: %d, for: %s\nResult: %s", result.getNumberTurnCurrent(), player, result));
    }
    
    @Override
    public void turnLog(int numberOfRollCurrent, int pointsRoll, GameFlow master) {
        System.out.println(String.format("roll[%d]: %d, points: %s", numberOfRollCurrent, pointsRoll, master.pointsScoredPerRoll(numberOfRollCurrent, pointsRoll)));
    }
    
    @Override
    public void endStepLog(GamePlayer player, GameResults resultsGame) throws PlayerHasNotBeenAddedToGameException {
        PlayerResult resultPlayerCurrent = resultsGame.getPlayerResultFor(player);
        GamePlayer playerLeader = resultsGame.getLeader();
        System.out.println(String.format("Result turn: %s\nLeader: %s\n", resultPlayerCurrent, playerLeader));
    }

    @Override
    public void loadingEngine() {
        System.out.print("Loading engine...");
    }

    @Override
    public void okEngine() {
        System.out.println("ok");
    }
    
}
