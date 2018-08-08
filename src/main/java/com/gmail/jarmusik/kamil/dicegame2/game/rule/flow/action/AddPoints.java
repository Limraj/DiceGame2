/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Builder;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;

/**
 *
 * @author Kamil-Tomasz
 */
@Builder
public class AddPoints implements ActionGame {
    
    private final GamePlayer playerGame;
    private final int numberOfRollCurrent;
    private final int pointsRoll;
    
    @Override
    public boolean execute(GameResultsModifier modifier, GameRules rulesGame) {
        try {
            return modifier.addPointsFor(playerGame, rulesGame.getGameFlow().pointsScoredPerRoll(numberOfRollCurrent, pointsRoll));
        } catch (PlayerHasNotBeenAddedToGameException ex) {
            Logger.getLogger(AddPoints.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
