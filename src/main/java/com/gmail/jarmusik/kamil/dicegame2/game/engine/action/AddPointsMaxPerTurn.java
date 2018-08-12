/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamil-Tomasz
 */
public class AddPointsMaxPerTurn implements GameAction {
    
    private final GamePlayer player;

    public AddPointsMaxPerTurn(GamePlayer player) {
        this.player = player;
    }

    @Override
    public boolean execute(GameResultsModifier modifier, GameRules rules) {
        try {
            modifier.addPointsFor(player, rules.maxPointsToEndTurn(1));
            return true;
        } catch (GameException ex) {
            Logger.getLogger(AddPointsMaxToEndTurn.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
