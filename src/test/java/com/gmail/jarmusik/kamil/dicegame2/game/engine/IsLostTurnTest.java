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
public class IsLostTurnTest {

    @Parameterized.Parameters(name = "{index}: isLostTurn({0},{1})={2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 1, 12, true}, { 2, 12, false}, { 3, 12, false}, { 4, 12, false},
            { 5, 12, false}, { 6, 12, false}, { 7, 12, false}, { 8, 12, false},
            { 9, 12, false}, { 10, 12, false}, 
            
            { 1, 2, true}, { 2, 2, false}, { 3, 2, false}, { 4, 2, false},
            { 5, 2, false}, { 6, 2, false}, { 7, 2, false}, { 8, 2, false},
            { 9, 2, false}, { 10, 2, false},
            
            { 1, 1, false}, { 2, 2, false}, { 3, 3, false}, { 4, 4, false},
            { 5, 5, false}, { 6, 6, false}, { 7, 7, false}, { 8, 8, false},
            { 9, 9, false}, { 10, 10, false}, { 9, 11, false}, { 10, 12, false}, 
        });
    }

    private final int numberOfRollCurrent;
    private final int pointsRoll;
    private final boolean expected;
    private final GameFlow flow;

    public IsLostTurnTest(int numberOfRollCurrent, int pointsRoll, boolean expected) {
        this.numberOfRollCurrent = numberOfRollCurrent;
        this.pointsRoll = pointsRoll;
        this.expected = expected;
        this.flow = new DiceGameFlow();
                
    }

    @Test
    public void testIsLostTurn() {
        Assert.assertEquals(expected, flow.isLostTurn(numberOfRollCurrent, pointsRoll));
    }
}
