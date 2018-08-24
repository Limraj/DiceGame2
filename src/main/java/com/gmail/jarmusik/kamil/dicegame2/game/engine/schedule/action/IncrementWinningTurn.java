/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameActionException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;


/**
 *
 * @author Kamil-Tomasz
 */
class IncrementWinningTurn implements Executable, GameAction {
    
    private final GamePlayer player;

    public IncrementWinningTurn(GamePlayer playerGame) {
        this.player = playerGame;
    }

    @Override
    public void execute(GameResultsModifier modifier, GameRules rulesGame) throws GameActionException {
        try {
            modifier.incrementWinningTurnFor(player);
        } catch (Exception ex) {
            throw new GameActionException("player: " + player, ex);
        }
    }
    
}