/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.DiceGameFlow;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;

/**
 *
 * @author Kamil-Tomasz
 */
@RunWith(Parameterized.class)
public class IsWonTurnTest {

    @Parameterized.Parameters(name = "{index}: isWonTurn({0},{1})={2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 1, 11, true}, { 2, 11, false}, { 3, 11, false}, { 4, 11, false},
            { 5, 11, false}, { 6, 11, false}, { 7, 11, false}, { 8, 11, false},
            { 9, 11, false}, { 10, 11, false}, 
            
            { 1, 7, true}, { 2, 7, false}, { 3, 7, false}, { 4, 7, false},
            { 5, 7, false}, { 6, 7, false}, { 7, 7, false}, { 8, 7, false},
            { 9, 7, false}, { 10, 7, false},
            
            { 1, 5, true}, { 5, 5, true}, { 3, 5, true}, { 4, 5, true},
            { 5, 5, true}, { 6, 5, true}, { 7, 5, true}, { 8, 5, true},
            { 9, 5, true}, { 10, 5, true},
            
            { 1, 1, false}, { 2, 2, false}, { 3, 3, false}, { 4, 4, false},
            { 5, 5, true}, { 6, 6, false}, { 7, 7, false}, { 8, 8, false},
            { 9, 9, false}, { 10, 10, false}, { 9, 11, false}, { 10, 12, false}, 
        });
    }

    private final int numberOfRollCurrent;
    private final int pointsRoll;
    private final boolean expected;
    private final GameFlow flow;

    public IsWonTurnTest(int numberOfRollCurrent, int pointsRoll, boolean expected) {
        this.numberOfRollCurrent = numberOfRollCurrent;
        this.pointsRoll = pointsRoll;
        this.expected = expected;
        this.flow = new DiceGameFlow();
    }

    @Test
    public void testIsWonTurnTurn() {
        Assert.assertEquals(expected, flow.isWonTurn(numberOfRollCurrent, pointsRoll));
    }
}
