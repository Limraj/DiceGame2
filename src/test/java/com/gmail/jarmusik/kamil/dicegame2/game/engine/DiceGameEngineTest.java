/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.AccessFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.Dice;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.DiceCubeOnlyTest;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGameEngineTest {

    private static DiceGameEngine engine;
    private static GamePlayer player1;
    private static GamePlayer player2;
    
    @BeforeClass
    public static void setup() {
        
        player1 = new DiceGamePlayer("Pierwszy");
        player2 = new DiceGamePlayer("Drugi");
        Set<GamePlayer> players = new LinkedHashSet<>();
        players.add(player1);
        players.add(player2);
        Integer[] sequenceForDice = {1,2,2,6,5,6,5,6,3,2,1,2,6,1,5,5,4,3,2,6};
        Integer[] sequenceForDice2 ={1,2,6,3,6,2,4,3,3,6,7,2,3,4,5,3,4,5,4,6};
        List<Dice> dices = new ArrayList<>();
        dices.add(new DiceCubeOnlyTest(sequenceForDice));
        dices.add(new DiceCubeOnlyTest(sequenceForDice2));
        
        GameFlow flow = GameFlowFactory.createFlowDiceGame();
        GameRules rules = GameRulesFactory.createRulesFiveTurnsTenRolls(flow, dices);
        engine = new DiceGameEngine(players, rules, (AccessFlow) rules);
        engine.debugMode(true);
    }
    
    @Test
    public void testNextTurn0() throws GameException {
        
        //When
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.ZERO, result1.getPoints());
        assertEquals(BigDecimal.ZERO, result2.getPoints());
        
        assertEquals(0, result1.getNumberTurnCurrent());
        assertEquals(0, result2.getNumberTurnCurrent());
        
        assertEquals(0, result1.getNumberWinningTurns());
        assertEquals(0, result2.getNumberWinningTurns());
    }
    
    @Test
    public void testNextTurn1() throws GameException {
        
        //When
        engine.nextPlayer();
        engine.nextPlayer();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(35.14), result1.getPoints());
        assertEquals(BigDecimal.valueOf(20.58), result2.getPoints());
        
        assertEquals(1, result1.getNumberTurnCurrent());
        assertEquals(1, result2.getNumberTurnCurrent());
        
        assertEquals(0, result1.getNumberWinningTurns());
        assertEquals(0, result2.getNumberWinningTurns());
        
        assertEquals(player2, results.getLeader());
    }

    @Test
    public void testNextTurn2() throws GameException {
        //When
        engine.nextPlayer();
        engine.nextPlayer();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(35.14), result1.getPoints());
        assertEquals(BigDecimal.valueOf(45.03), result2.getPoints());
        
        assertEquals(2, result1.getNumberTurnCurrent());
        assertEquals(2, result2.getNumberTurnCurrent());
        
        assertEquals(1, result1.getNumberWinningTurns());
        assertEquals(0, result2.getNumberWinningTurns());
        
        assertEquals(player1, results.getLeader());
    }
    
    @Test
    public void testNextTurn3() throws GameException {
        //When
        engine.nextPlayer();
        engine.nextPlayer();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(35.14), result1.getPoints());
        assertEquals(BigDecimal.valueOf(45.03), result2.getPoints());
        
        assertEquals(3, result1.getNumberTurnCurrent());
        assertEquals(3, result2.getNumberTurnCurrent());
        
        assertEquals(2, result1.getNumberWinningTurns());
        assertEquals(1, result2.getNumberWinningTurns());

        assertEquals(player1, results.getLeader());
    }
    
    @Test
    public void testNextTurn4() throws GameException {
        //When
        engine.nextPlayer();
        engine.nextPlayer();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(59.59), result1.getPoints());
        assertEquals(BigDecimal.valueOf(45.03), result2.getPoints());
        
        assertEquals(4, result1.getNumberTurnCurrent());
        assertEquals(4, result2.getNumberTurnCurrent());
        
        assertEquals(2, result1.getNumberWinningTurns());
        assertEquals(2, result2.getNumberWinningTurns());
        
        assertEquals(player2, results.getLeader());
    }
    
    @Test
    public void testNextTurn5() throws GameException {
        //When
        engine.nextPlayer();
        engine.nextPlayer();
        
        //Then
        GameResults results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(59.59), result1.getPoints());
        assertEquals(BigDecimal.valueOf(69.48), result2.getPoints());
        
        assertEquals(5, result1.getNumberTurnCurrent());
        assertEquals(5, result2.getNumberTurnCurrent());
        
        assertEquals(3, result1.getNumberWinningTurns());
        assertEquals(2, result2.getNumberWinningTurns());
        
        assertEquals(player1, results.getLeader());
    }
}

