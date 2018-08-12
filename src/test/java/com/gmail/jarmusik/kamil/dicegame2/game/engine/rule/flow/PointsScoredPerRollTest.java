/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.rule.flow;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResultImpl;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;

/**
 *
 * @author Kamil-Tomasz
 */
@RunWith(Parameterized.class)
public class PointsScoredPerRollTest {
    
    @Parameterized.Parameters(name = "{index}: isWonTurn({0},{1})={2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 3, 7, "2.33"}, { 5, 11, "2.20"}, { 8, 5, "0.63"}, { 7, 9, "1.29"},
        });
    }

    private final int numberRollCurrent;
    private final int meshesFromRoll;
    private final BigDecimal expected;
    private final GameFlow flow;

    public PointsScoredPerRollTest(int numberRollCurrent, int meshesFromRoll, String expected) {
        this.numberRollCurrent = numberRollCurrent;
        this.meshesFromRoll = meshesFromRoll;
        this.expected = new BigDecimal(expected);
        this.flow = GameFlowFactory.createFlowGameDice();
    }

    @Test
    public void testPointsScoredPerRoll() {
        RollDicesResult result = RollDicesResultImpl.builder()
                .numberMeshes(meshesFromRoll)
                .numberRollCurrent(numberRollCurrent)
                .build();
        Assert.assertEquals(expected, flow.pointsScoredPerRoll(result));
    }
}
