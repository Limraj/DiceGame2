/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Builder;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;

/**
 *
 * @author Kamil-Tomasz
 */

public class AddPoints implements GameAction {
    
    private final RollDicesResult result;

    public AddPoints(RollDicesResult result) {
        this.result = result;
    }

    @Override
    public boolean execute(GameResultsModifier modifier, GameRules rules) {
        try {
            modifier.addPointsFor(result.getGamePlayer(), rules.getGameFlow().pointsScoredPerRoll(result));
            return true;
        } catch (GameException ex) {
            Logger.getLogger(AddPoints.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
