/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kamil-Tomasz
 */
public class VerdictTest {
    
    private static GamePlayer bartek;
    private static GamePlayer kamil;
    private static GamePlayer tomek;
    private static Comparator<PlayerResult> fewestPointsWins;
    
    @BeforeClass
    public static void setUpClass() {
        bartek = new DiceGamePlayer("Bartek");
        kamil = new DiceGamePlayer("Kamil");
        tomek = new DiceGamePlayer("Tomek");
        fewestPointsWins = (PlayerResult o1, PlayerResult o2) -> {
            return o1.getPoints().compareTo(o2.getPoints());
        };
    }

    @Test
    public void testDetermineLeader() {
        System.out.println("determineLeader");
        //given:
        Map<GamePlayer, BigDecimal> resultsPoints = new HashMap<>();
        resultsPoints.put(tomek, BigDecimal.ONE);
        resultsPoints.put(bartek, BigDecimal.TEN.add(BigDecimal.TEN));
        resultsPoints.put(kamil, BigDecimal.TEN);
        Map<GamePlayer, PlayerResult> results = generateResults(resultsPoints);
        //when:
        GamePlayer leader = Verdict.determineLeader(results, fewestPointsWins);
        //then:
        assertEquals(tomek, leader);
    }

    @Test
    public void testDeterminePeleton() {
        System.out.println("determinePeleton");
        //given:
        Map<GamePlayer, BigDecimal> resultsPoints = new HashMap<>();
        resultsPoints.put(tomek, BigDecimal.TEN);
        resultsPoints.put(bartek, BigDecimal.TEN.add(BigDecimal.TEN));
        resultsPoints.put(kamil, BigDecimal.ONE);
        Map<GamePlayer, PlayerResult> results = generateResults(resultsPoints);
        //when:
        List<GamePlayer> peleton = Verdict.determinePeleton(results, fewestPointsWins);
        //then:
        List<GamePlayer> peletonExpected = new ArrayList<>();
        peletonExpected.add(kamil);
        peletonExpected.add(tomek);
        peletonExpected.add(bartek);
        assertEquals(peletonExpected, peleton);
    }
    
    private static Map<GamePlayer, PlayerResult> generateResults(Map<GamePlayer, BigDecimal> resultsPoints) {
        Map<GamePlayer, PlayerResult> results  = new HashMap<>();
        resultsPoints.entrySet().forEach((entry) -> {
            PlayerResultModifier modifier = new PlayerResultModifierImpl();
            modifier.addPoints(entry.getValue());
            modifier.incrementAndGetNumberTurnCurrent();
            modifier.incrementAndGetNumberWinningTurns();
            results.put(entry.getKey(), modifier.newPlayerResult());
        });
        return results;
    }
    
}
