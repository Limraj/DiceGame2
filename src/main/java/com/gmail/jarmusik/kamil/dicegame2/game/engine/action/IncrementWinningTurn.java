/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import java.util.logging.Level;
import lombok.extern.java.Log;


/**
 *
 * @author Kamil-Tomasz
 */
@Log
class IncrementWinningTurn implements GameActionToExecute, GameAction {
    
    private final GamePlayer playerGame;

    public IncrementWinningTurn(GamePlayer playerGame) {
        this.playerGame = playerGame;
    }

    @Override
    public boolean execute(GameResultsModifier modifier, GameRules rulesGame) {
        try {
            modifier.incrementWinningTurnFor(playerGame);
            return true;
        } catch (GameException ex) {
            log.log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
