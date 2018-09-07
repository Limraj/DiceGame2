/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.GameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.AccessFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import java.util.HashSet;
import org.junit.Test;

/**
 *
 * @author Kamil-Tomasz
 */
public class NoPlayersExceptionTest {
    
    @Test(expected = NoPlayersException.class)
    public void testWithoutPlayers() throws GameRuntimeException {
        GameFlow flow = GameFlowFactory.createFlowDiceGame();
        GameRules rules = GameRulesFactory.createRulesOneTurnTenRollsTwoDices(flow);
        GameEngine engine = GameEngine.newEngine(new HashSet<>(), rules, (AccessFlow) rules);
        engine.nextTurn();
    }
}
