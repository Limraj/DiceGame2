/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameActionException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameActionFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;

/**
 *
 * @author Kamil-Tomasz
 */
public class ActionsSchedulerImplTest {

    private static GameRules rules;
    private static GameResultsModifier modifier;
    private static GamePlayer player;
    private static BigDecimal pointsExpected;
    
    @BeforeClass
    public static void setup() {
        player = new DiceGamePlayer("Bartek");
        GameFlow flow = GameFlowFactory.createFlowDiceGame();
        rules = GameRulesFactory.createRulesFiveTurnsTenRollsTwoDices(flow);
        Set<GamePlayer> players = new HashSet<>();
        players.add(player);
        modifier = GameResultsModifier.newModifier(players, flow.rulesOfWinning());
        BigDecimal maxToEndTurn = rules.maxPointsToEndTurn(3);
        BigDecimal maxPerTurn = rules.maxPointsToEndTurn(1);
        BigDecimal startPoints = modifier.snapshot().getPlayerResultFor(player).getPoints();
        pointsExpected = startPoints.add(maxPerTurn).add(maxToEndTurn);
    }
    
    @Before 
    public void reset() {
        modifier.reset();
    }

    @Test
    public void testComplete() {
        System.out.println("testComplete");
        //given:
        List<GameAction> schedule = new ArrayList<>();
        schedule.add(GameActionFactory.addPointsMaxToEndTurn(player, 3));
        schedule.add(GameActionFactory.addPointsMaxPerTurn(player));
        
        GameActionsScheduler subject = GameActionsScheduler
                .builder(schedule)
                .modifier(modifier)
                .rules(rules).build();
        //when:
        subject.complete();
        BigDecimal result = modifier.snapshot().getPlayerResultFor(player).getPoints();
        
        //then:
        assertFalse(result == null);
        assertFalse(BigDecimal.ZERO.equals(result));
        assertEquals(pointsExpected, result);
    }
    
    @Test(expected = GameActionException.class)
    public void testCompleteWithArithmeticException() {
        System.out.println("testCompleteWithException");
        //given:
        List<GameAction> schedule = new ArrayList<>();
        schedule.add(GameActionFactory.addPointsMaxToEndTurn(player, 3));
        //GameActionException induced ArithmeticException (RuntimeException)
        schedule.add(GameActionFactory.addPointsMaxToEndTurn(player, 0));
        //
        schedule.add(GameActionFactory.addPointsMaxPerTurn(player));
        
        GameActionsScheduler subject = GameActionsScheduler
                .builder(schedule)
                .modifier(modifier)
                .rules(rules).build();
        //when:
        subject.complete();
    }
    
    @Test(expected = GameActionException.class)
    public void testCompleteWithPlayerHasNotBeenAddedToGameException() {
        System.out.println("testCompleteWithException");
        //given:
        List<GameAction> schedule = new ArrayList<>();
        //GameActionException induced PlayerHasNotBeenAddedToGameException (Exception)
        GamePlayer player2 = new DiceGamePlayer("Julia");
        schedule.add(GameActionFactory.incrementWinningTurn(player2));
        //
        schedule.add(GameActionFactory.addPointsMaxPerTurn(player));
        
        GameActionsScheduler subject = GameActionsScheduler
                .builder(schedule)
                .modifier(modifier)
                .rules(rules).build();
        //when:
        subject.complete();
    }
}
