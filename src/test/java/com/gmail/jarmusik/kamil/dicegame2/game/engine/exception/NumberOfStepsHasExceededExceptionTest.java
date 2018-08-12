/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.DiceGameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.GameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author Kamil-Tomasz
 */
public class NumberOfStepsHasExceededExceptionTest {

    @Test(expected = NumberOfStepsHasExceededException.class)
    public void testWithoutPlayers() throws GameException {
        GameFlow flow = GameFlowFactory.createFlowGameDice();
        GameRules rules = GameRulesFactory.createRulesOneTurnTenRollsTwoDices(flow);
        GameEngine engine = new DiceGameEngine(new HashSet<>(), rules);
        engine.nextPlayer();
    }
    
    @Test(expected = NumberOfStepsHasExceededException.class)
    public void testWithPlayers() throws GameException {
        GameFlow flow = GameFlowFactory.createFlowGameDice();
        GameRules rules = GameRulesFactory.createRulesOneTurnTenRollsTwoDices(flow);
        Set<GamePlayer> players = new HashSet<>();
        players.add(new DiceGamePlayer("Marek"));
        players.add(new DiceGamePlayer("Albert"));
        GameEngine engine = new DiceGameEngine(players, rules);
        engine.nextPlayer();
        engine.nextPlayer();
        engine.nextPlayer();
    }
    
}
