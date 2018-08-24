/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameActionException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import java.math.BigDecimal;


/**
 *
 * @author Kamil-Tomasz
 */
class AddPoints implements Executable, GameAction {
    
    private final GamePlayer player;
    private final BigDecimal points;

    public AddPoints(GamePlayer player, BigDecimal points) {
        this.player = player;
        this.points = points;
    }

    @Override
    public void execute(GameResultsModifier modifier, GameRules rules) throws GameActionException {
        try {
            modifier.addPointsFor(player, points);
        } catch (GameException ex) {
            throw new GameActionException("player: " + player, ex);
        }
    }

}
