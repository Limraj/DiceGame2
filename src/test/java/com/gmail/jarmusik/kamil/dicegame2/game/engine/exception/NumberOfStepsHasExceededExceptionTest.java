/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.DiceGameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.GameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kamil-Tomasz
 */
public class NumberOfStepsHasExceededExceptionTest {

    @Test(expected = NumberOfStepsHasExceededException.class)
    public void testSomeMethod() throws GameException {
        GameFlow flow = GameFlowFactory.createFlowGameDice();
        GameRules rules = GameRulesFactory.createRulesOneTurnTenRollsTwoDices(flow);
        GameEngine engine = new DiceGameEngine(new HashSet<>(), rules);
        engine.nextPlayer();
        engine.nextPlayer();
        engine.nextPlayer();
    }
    
}
