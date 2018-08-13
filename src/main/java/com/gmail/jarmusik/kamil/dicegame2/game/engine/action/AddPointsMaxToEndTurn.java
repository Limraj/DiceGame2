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
class AddPointsMaxToEndTurn implements GameActionToExecute  {
    
    private final GamePlayer player;
    private final int numberRollCurrent;

    public AddPointsMaxToEndTurn(GamePlayer player, int numberRollCurrent) {
        this.player = player;
        this.numberRollCurrent = numberRollCurrent;
    }

    @Override
    public boolean execute(GameResultsModifier modifier, GameRules rules) {
        try {
            modifier.addPointsFor(player, rules.maxPointsToEndTurn(numberRollCurrent));
            return true;
        } catch (GameException ex) {
            log.log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
