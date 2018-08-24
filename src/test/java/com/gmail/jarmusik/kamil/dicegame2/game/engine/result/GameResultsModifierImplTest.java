/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameResultsModifierImplTest {
    
    private static GameResultsModifier modifier;
    private static GamePlayer bartek;
    private static GamePlayer kamil;
    
    @BeforeClass
    public static void setUpClass() {
        bartek = new DiceGamePlayer("Bartek");
        kamil = new DiceGamePlayer("Kamil");
        Set<GamePlayer> players = new HashSet<>();
        players.add(kamil);
        players.add(bartek);
        modifier = new GameResultsModifierImpl(players, () -> (a,b) -> 0);
    }

    @Test
    public void testAddPointsForBartek() throws Exception {
        System.out.println("addPointsFor");
        //given:
        BigDecimal points = BigDecimal.ONE;
        GameResults gameResultsExpected = modifier.newGameResults();
        BigDecimal pointsKamilExcepted = gameResultsExpected.getPlayerResultFor(kamil).getPoints();
        BigDecimal pointsBartekExcepted = gameResultsExpected.getPlayerResultFor(bartek).getPoints().add(points);
        
        //when:
        modifier.addPointsFor(bartek, points);
        GameResults gameResults = modifier.newGameResults();
        BigDecimal pointsKamil = gameResults.getPlayerResultFor(kamil).getPoints();
        BigDecimal pointsBartek = gameResults.getPlayerResultFor(bartek).getPoints();
        
        //then:
        assertEquals(pointsKamilExcepted, pointsKamil);
        assertEquals(pointsBartekExcepted, pointsBartek);
    }
    
    @Test
    public void testAddPointsForKamil() throws Exception {
        System.out.println("addPointsFor");
        //given:
        BigDecimal points = BigDecimal.TEN;
        GameResults gameResultsExpected = modifier.newGameResults();
        BigDecimal pointsKamilExcepted = gameResultsExpected.getPlayerResultFor(kamil).getPoints().add(points);
        BigDecimal pointsBartekExcepted = gameResultsExpected.getPlayerResultFor(bartek).getPoints();
        
        //when:
        modifier.addPointsFor(kamil, points);
        GameResults gameResults = modifier.newGameResults();
        BigDecimal pointsKamil = gameResults.getPlayerResultFor(kamil).getPoints();
        BigDecimal pointsBartek = gameResults.getPlayerResultFor(bartek).getPoints();
        
        //then:
        assertEquals(pointsKamilExcepted, pointsKamil);
        assertEquals(pointsBartekExcepted, pointsBartek);
    }

    @Test
    public void testIncrementTurnForBartek() throws Exception {
        System.out.println("incrementTurnFor");
        //given:
        GameResults gameResultsExpected = modifier.newGameResults();
        int numberTurnCurrentKamilExcepted = gameResultsExpected.getPlayerResultFor(kamil).getNumberTurnCurrent();
        int numberTurnCurrentBartekExcepted = gameResultsExpected.getPlayerResultFor(bartek).getNumberTurnCurrent() + 1;
        
        //when:
        modifier.incrementTurnFor(bartek);
        GameResults gameResults = modifier.newGameResults();
        int numberTurnCurrentKamil = gameResults.getPlayerResultFor(kamil).getNumberTurnCurrent();
        int numberTurnCurrentBartek = gameResults.getPlayerResultFor(bartek).getNumberTurnCurrent();
        
        //then:
        assertEquals(numberTurnCurrentKamilExcepted, numberTurnCurrentKamil);
        assertEquals(numberTurnCurrentBartekExcepted, numberTurnCurrentBartek);
    }
    
    @Test
    public void testIncrementTurnForKamil() throws Exception {
        System.out.println("incrementTurnFor");
        //given:
        GameResults gameResultsExpected = modifier.newGameResults();
        int numberTurnCurrentKamilExcepted = gameResultsExpected.getPlayerResultFor(kamil).getNumberTurnCurrent() + 1;
        int numberTurnCurrentBartekExcepted = gameResultsExpected.getPlayerResultFor(bartek).getNumberTurnCurrent();
        
        //when:
        modifier.incrementTurnFor(kamil);
        GameResults gameResults = modifier.newGameResults();
        int numberTurnCurrentKamil = gameResults.getPlayerResultFor(kamil).getNumberTurnCurrent();
        int numberTurnCurrentBartek = gameResults.getPlayerResultFor(bartek).getNumberTurnCurrent();
        
        //then:
        assertEquals(numberTurnCurrentKamilExcepted, numberTurnCurrentKamil);
        assertEquals(numberTurnCurrentBartekExcepted, numberTurnCurrentBartek);
    }

    @Test
    public void testIncrementWinningTurnForKamil() throws Exception {
        System.out.println("incrementWinningTurnFor");
        //given:
        GameResults gameResultsExpected = modifier.newGameResults();
        int numberWinningTurnsKamilExcepted = gameResultsExpected.getPlayerResultFor(kamil).getNumberWinningTurns() + 1;
        int numberWinningTurnsBartekExcepted = gameResultsExpected.getPlayerResultFor(bartek).getNumberWinningTurns();
        
        //when:
        modifier.incrementWinningTurnFor(kamil);
        GameResults gameResults = modifier.newGameResults();
        int numberWinningTurnsKamil = gameResults.getPlayerResultFor(kamil).getNumberWinningTurns();
        int numberWinningTurnsBartek = gameResults.getPlayerResultFor(bartek).getNumberWinningTurns();
        
        //then:
        assertEquals(numberWinningTurnsKamilExcepted, numberWinningTurnsKamil);
        assertEquals(numberWinningTurnsBartekExcepted, numberWinningTurnsBartek);
    }
    
    @Test
    public void testIncrementWinningTurnForBartek() throws Exception {
        System.out.println("incrementWinningTurnFor");
        //given:
        GameResults gameResultsExpected = modifier.newGameResults();
        int numberWinningTurnsKamilExcepted = gameResultsExpected.getPlayerResultFor(kamil).getNumberWinningTurns();
        int numberWinningTurnsBartekExcepted = gameResultsExpected.getPlayerResultFor(bartek).getNumberWinningTurns() + 1;
        
        //when:
        modifier.incrementWinningTurnFor(bartek);
        GameResults gameResults = modifier.newGameResults();
        int numberWinningTurnsKamil = gameResults.getPlayerResultFor(kamil).getNumberWinningTurns();
        int numberWinningTurnsBartek = gameResults.getPlayerResultFor(bartek).getNumberWinningTurns();
        
        //then:
        assertEquals(numberWinningTurnsKamilExcepted, numberWinningTurnsKamil);
        assertEquals(numberWinningTurnsBartekExcepted, numberWinningTurnsBartek);
    }

    @Test
    public void testReset() throws GameException {
        System.out.println("reset");
        //given:
        modifier.reset();
        modifier.incrementWinningTurnFor(bartek);
        modifier.addPointsFor(bartek, BigDecimal.ONE);
        modifier.incrementTurnFor(bartek);
        modifier.incrementWinningTurnFor(kamil);
        modifier.addPointsFor(kamil, BigDecimal.TEN);
        modifier.incrementTurnFor(kamil);
        //then:
        GameResults gameResultsExpected = modifier.newGameResults();
        assertFalse(gameResultsExpected.getPlayerResultFor(bartek).getPoints().equals(BigDecimal.ZERO));
        assertFalse(gameResultsExpected.getPlayerResultFor(bartek).getNumberTurnCurrent() == 0);
        assertFalse(gameResultsExpected.getPlayerResultFor(bartek).getNumberWinningTurns() == 0);
        assertFalse(gameResultsExpected.getPlayerResultFor(kamil).getPoints().equals(BigDecimal.ZERO));
        assertFalse(gameResultsExpected.getPlayerResultFor(kamil).getNumberTurnCurrent() == 0);
        assertFalse(gameResultsExpected.getPlayerResultFor(kamil).getNumberWinningTurns() == 0);
        //when:
        modifier.reset();
        //then:
        GameResults gameResults = modifier.newGameResults();
        assertTrue(gameResults.getPlayerResultFor(bartek).getPoints().equals(BigDecimal.ZERO));
        assertTrue(gameResults.getPlayerResultFor(bartek).getNumberTurnCurrent() == 0);
        assertTrue(gameResults.getPlayerResultFor(bartek).getNumberWinningTurns() == 0);
        assertTrue(gameResults.getPlayerResultFor(kamil).getPoints().equals(BigDecimal.ZERO));
        assertTrue(gameResults.getPlayerResultFor(kamil).getNumberTurnCurrent() == 0);
        assertTrue(gameResults.getPlayerResultFor(kamil).getNumberWinningTurns() == 0);
    }

}
