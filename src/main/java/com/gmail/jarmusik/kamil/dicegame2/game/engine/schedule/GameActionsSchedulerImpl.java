/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameActionNotExecutable;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import java.util.List;
import lombok.Builder;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.Executable;

/**
 *
 * @author Kamil-Tomasz
 */

@Builder
class GameActionsSchedulerImpl implements GameActionsScheduler {
    
    private final GameResultsModifier modifier;
    private final GameRules rules;
    private final List<GameAction> schedule;

    @Override
    public void complete() {
        schedule.forEach(action -> {
            if(!isExecutable(action))
                throw new GameActionNotExecutable(action);
            ((Executable)action).execute(modifier, rules);
        });
    }

    private static boolean isExecutable(GameAction action) {
        return action instanceof Executable;
    }
}
