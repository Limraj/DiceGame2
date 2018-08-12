/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import java.math.BigDecimal;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kamil-Tomasz
 */
public class PlayerResultModifierImplTest {

    private static PlayerResultModifier modifier;
    
    @BeforeClass
    public static void setUpClass() {
        modifier = new PlayerResultModifierImpl();
    }
    
    @Test
    public void testAddPoints() {
        System.out.println("addPoints");
        //given:
        BigDecimal pointsToAdded = BigDecimal.TEN;
        PlayerResult playerResult = modifier.newPlayerResult();
        BigDecimal pointsExpected = playerResult.getPoints().add(pointsToAdded);

        //when:
        BigDecimal points = modifier.addPoints(pointsToAdded);
        
        //then:
        assertEquals(pointsExpected, points);
 
    }

    @Test
    public void testIncrementAndGetNumberTurnCurrent() {
        System.out.println("incrementAndGetNumberTurnCurrent");
        //given:
        PlayerResult playerResult = modifier.newPlayerResult();
        int numberTurnCurrentExpected = playerResult.getNumberTurnCurrent() + 1;

        //when:
        int numberTurnCurrent = modifier.incrementAndGetNumberTurnCurrent();
        
        //then:
        assertEquals(numberTurnCurrentExpected, numberTurnCurrent);
    }

    @Test
    public void testReset() {
        System.out.println("reset");
        //given:
        modifier.reset();
        modifier.incrementAndGetNumberWinningTurns();
        modifier.addPoints(BigDecimal.ONE);
        modifier.incrementAndGetNumberTurnCurrent();
        //then:
        PlayerResult playerResultExpected = modifier.newPlayerResult();
        assertFalse(playerResultExpected.getPoints().equals(BigDecimal.ZERO));
        assertFalse(playerResultExpected.getNumberTurnCurrent() == 0);
        assertFalse(playerResultExpected.getNumberWinningTurns() == 0);
        //when:
        modifier.reset();
        //then:
        PlayerResult playerResult = modifier.newPlayerResult();
        assertTrue(playerResult.getPoints().equals(BigDecimal.ZERO));
        assertTrue(playerResult.getNumberTurnCurrent() == 0);
        assertTrue(playerResult.getNumberWinningTurns() == 0);

    }

    @Test
    public void testIncrementAndGetNumberWinningTurns() {
        System.out.println("incrementAndGetNumberWinningTurns");
        //given:
        PlayerResult playerResult = modifier.newPlayerResult();
        int numberWinningTurnsExpected = playerResult.getNumberWinningTurns() + 1;

        //when:
        int numberWinningTurns = modifier.incrementAndGetNumberWinningTurns();
        
        //then:
        assertEquals(numberWinningTurnsExpected, numberWinningTurns);
    }
    
}
