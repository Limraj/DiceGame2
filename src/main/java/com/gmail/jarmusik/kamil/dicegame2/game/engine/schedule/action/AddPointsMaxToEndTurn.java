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
class AddPointsMaxToEndTurn implements Executable, GameAction {
    
    private final GamePlayer player;
    private final int numberRollCurrent;

    public AddPointsMaxToEndTurn(GamePlayer player, int numberRollCurrent) {
        this.player = player;
        this.numberRollCurrent = numberRollCurrent;
    }

    @Override
    public void execute(GameResultsModifier modifier, GameRules rules) throws GameActionException {
        try {
            modifier.addPointsFor(player, rules.maxPointsToEndTurn(numberRollCurrent));
        } catch (Exception ex) {
            throw new GameActionException("player: " + player + ", numberRollCurrent: " + numberRollCurrent, ex);
        }
    }
    
}
