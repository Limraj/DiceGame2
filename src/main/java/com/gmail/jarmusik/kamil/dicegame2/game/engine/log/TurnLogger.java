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
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;

/**
 *
 * @author Kamil-Tomasz
 */
public class TurnLogger implements TurnLoggable {

    @Override
    public void startStepLog(GamePlayer player, GameResults results) throws PlayerHasNotBeenAddedToGameException {
        PlayerResult result = results.getPlayerResultFor(player);
        System.out.println(String.format("Turn number: %d, for: %s\nResult: %s", result.getNumberTurnCurrent(), player, result));
    }
    
    @Override
    public void stepLog(RollDicesResult result, GameFlow flow) {
        System.out.println(String.format("roll[%d]: %d, points: %s", result.getNumberRollCurrent(), result.getNumberMeshes(), flow.pointsScoredPerRoll(result)));
    }
    
    @Override
    public void endStepLog(GamePlayer player, GameResults results) throws PlayerHasNotBeenAddedToGameException {
        PlayerResult resultPlayerCurrent = results.getPlayerResultFor(player);
        GamePlayer playerLeader = results.getLeader();
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
