/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameActionException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Builder;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.Executable;

/**
 *
 * @author Kamil-Tomasz
 */
@Builder
public class ActionsSchedulerImpl implements ActionsScheduler {
    
    private final GameResultsModifier modifier;
    private final GameRules rules;
    private final List<GameAction> schedule;

    @Override
    public void complete() {
        schedule.forEach(a -> {
            try {
                ((Executable)a).execute(modifier, rules);
            } catch (GameActionException ex) {
                Logger.getLogger(ActionsSchedulerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
