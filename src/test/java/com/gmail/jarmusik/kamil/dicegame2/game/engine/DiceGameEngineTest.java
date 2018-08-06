/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.ResultsGame;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.DiceGameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.Dice;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.DiceCubeOnlyTest;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.DiceGameFlow;
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

        GameRules rules = DiceGameRules.builder(new DiceGameFlow())
                .numberOfTurns(5)
                .numberOfRolls(10)
                .addDice(new DiceCubeOnlyTest(sequenceForDice))
                .addDice(new DiceCubeOnlyTest(sequenceForDice2))
                .build();

        engine = new DiceGameEngine(players, rules);
        engine.debugMode(true);
    }
    
    @Test
    public void testNextTurn0() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        
        //When
        
        //Then
        ResultsGame results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(0), result1.getPoints());
        assertEquals(BigDecimal.valueOf(0), result2.getPoints());
        
        assertEquals(0, result1.getCurrentTurnNumber());
        assertEquals(0, result2.getCurrentTurnNumber());
        
        assertEquals(0, result1.getNumberOfWinningTurns());
        assertEquals(0, result2.getNumberOfWinningTurns());
    }
    
    @Test
    public void testNextTurn1() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        ResultsGame results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(35.14), result1.getPoints());
        assertEquals(BigDecimal.valueOf(18.57), result2.getPoints());
        
        assertEquals(1, result1.getCurrentTurnNumber());
        assertEquals(1, result2.getCurrentTurnNumber());
        
        assertEquals(0, result1.getNumberOfWinningTurns());
        assertEquals(1, result2.getNumberOfWinningTurns());
        
        assertEquals(player2, results.getLeader());
    }

    @Test
    public void testNextTurn2() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        ResultsGame results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(35.14), result1.getPoints());
        assertEquals(BigDecimal.valueOf(53.71), result2.getPoints());
        
        assertEquals(2, result1.getCurrentTurnNumber());
        assertEquals(2, result2.getCurrentTurnNumber());
        
        assertEquals(1, result1.getNumberOfWinningTurns());
        assertEquals(1, result2.getNumberOfWinningTurns());
        
        assertEquals(player1, results.getLeader());
    }
    
    @Test
    public void testNextTurn3() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        ResultsGame results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(53.71), result1.getPoints());
        assertEquals(BigDecimal.valueOf(53.71), result2.getPoints());
        
        assertEquals(3, result1.getCurrentTurnNumber());
        assertEquals(3, result2.getCurrentTurnNumber());
        
        assertEquals(2, result1.getNumberOfWinningTurns());
        assertEquals(2, result2.getNumberOfWinningTurns());
        //oboje mają tyle samo punktów;
        //Assert.assertEquals(player2, results.getLeader());
    }
    
    @Test
    public void testNextTurn4() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        ResultsGame results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(88.85), result1.getPoints());
        assertEquals(BigDecimal.valueOf(72.28), result2.getPoints());
        
        assertEquals(4, result1.getCurrentTurnNumber());
        assertEquals(4, result2.getCurrentTurnNumber());
        
        assertEquals(2, result1.getNumberOfWinningTurns());
        assertEquals(3, result2.getNumberOfWinningTurns());
        
        assertEquals(player2, results.getLeader());
    }
    
    @Test
    public void testNextTurn5() throws NumberOfTurnsHasExceededException, PlayerHasNotBeenAddedToGameException {
        //When
        engine.nextTurn();
        engine.nextTurn();
        
        //Then
        ResultsGame results = engine.getGameResults();
        PlayerResult result1 = results.getPlayerResultFor(player1);
        PlayerResult result2 = results.getPlayerResultFor(player2);
        
        assertEquals(BigDecimal.valueOf(88.85), result1.getPoints());
        assertEquals(BigDecimal.valueOf(107.42), result2.getPoints());
        
        assertEquals(5, result1.getCurrentTurnNumber());
        assertEquals(5, result2.getCurrentTurnNumber());
        
        assertEquals(3, result1.getNumberOfWinningTurns());
        assertEquals(3, result2.getNumberOfWinningTurns());
        
        assertEquals(player1, results.getLeader());
    }
}

