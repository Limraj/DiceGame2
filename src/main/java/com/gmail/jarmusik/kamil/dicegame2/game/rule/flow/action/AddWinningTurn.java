/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;

/**
 *
 * @author Kamil-Tomasz
 */
public class AddWinningTurn implements ActionGame {
    
    private final GamePlayer playerGame;

    public AddWinningTurn(GamePlayer playerGame) {
        this.playerGame = playerGame;
    }

    @Override
    public boolean execute(GameResultsModifier modifier, GameRules rulesGame) {
        try {
            modifier.incrementWinningTurnFor(playerGame);
            return true;
        } catch (GameException ex) {
            Logger.getLogger(AddWinningTurn.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
