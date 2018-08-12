/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.Dice;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.DiceCubeOnlyTest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;

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
        
        Integer[] sequenceForDice = {1,2,3,4,5,6,5,6,3,4,1,2,6,3,6,5,4,3,2,1};
        Integer[] sequenceForDice2 ={1,2,6,3,6,5,4,3,2,1,1,2,3,4,5,6,5,6,3,4};
        List<Dice> dices = new ArrayList<>();
        dices.add(new DiceCubeOnlyTest(sequenceForDice));
        dices.add(new DiceCubeOnlyTest(sequenceForDice2));
        
        GameFlow flow = GameFlowFactory.createFlowGameDice();
        GameRules rules = GameRulesFactory.createRulesFiveTurnsTenRolls(flow, dices);
        engine = new DiceGameEngine(players, rules);
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
        assertEquals(BigDecimal.valueOf(18.57), result2.getPoints());
        
        assertEquals(1, result1.getNumberTurnCurrent());
        assertEquals(1, result2.getNumberTurnCurrent());
        
        assertEquals(0, result1.getNumberWinningTurns());
        assertEquals(1, result2.getNumberWinningTurns());
        
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
        assertEquals(BigDecimal.valueOf(53.71), result2.getPoints());
        
        assertEquals(2, result1.getNumberTurnCurrent());
        assertEquals(2, result2.getNumberTurnCurrent());
        
        assertEquals(1, result1.getNumberWinningTurns());
        assertEquals(1, result2.getNumberWinningTurns());
        
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
        
        assertEquals(BigDecimal.valueOf(53.71), result1.getPoints());
        assertEquals(BigDecimal.valueOf(53.71), result2.getPoints());
        
        assertEquals(3, result1.getNumberTurnCurrent());
        assertEquals(3, result2.getNumberTurnCurrent());
        
        assertEquals(2, result1.getNumberWinningTurns());
        assertEquals(2, result2.getNumberWinningTurns());
        //oboje mają tyle samo punktów;
        //Assert.assertEquals(player2, results.getLeader());
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
        
        assertEquals(BigDecimal.valueOf(88.85), result1.getPoints());
        assertEquals(BigDecimal.valueOf(72.28), result2.getPoints());
        
        assertEquals(4, result1.getNumberTurnCurrent());
        assertEquals(4, result2.getNumberTurnCurrent());
        
        assertEquals(2, result1.getNumberWinningTurns());
        assertEquals(3, result2.getNumberWinningTurns());
        
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
        
        assertEquals(BigDecimal.valueOf(88.85), result1.getPoints());
        assertEquals(BigDecimal.valueOf(107.42), result2.getPoints());
        
        assertEquals(5, result1.getNumberTurnCurrent());
        assertEquals(5, result2.getNumberTurnCurrent());
        
        assertEquals(3, result1.getNumberWinningTurns());
        assertEquals(3, result2.getNumberWinningTurns());
        
        assertEquals(player1, results.getLeader());
    }
}

