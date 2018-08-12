/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import java.util.List;
import lombok.Builder;

/**
 *
 * @author Kamil-Tomasz
 */
@Builder
public class ActionsExecutorImpl implements ActionsExecutor {
    
    private final GameResultsModifier modifier;
    private final GameRules rules;
    private final List<GameAction> actions;

    @Override
    public void execute() {
        actions.forEach(a -> a.execute(modifier, rules));
    }
    
}
