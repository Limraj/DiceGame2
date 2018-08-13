/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import static java.lang.Math.log;
import java.util.logging.Level;
import lombok.extern.java.Log;


/**
 *
 * @author Kamil-Tomasz
 */
@Log
class AddPoints implements GameActionToExecute {
    
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
            log.log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
