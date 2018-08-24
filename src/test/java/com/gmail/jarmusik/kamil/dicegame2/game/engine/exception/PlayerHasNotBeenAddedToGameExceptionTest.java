/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kamil-Tomasz
 */
public class PlayerHasNotBeenAddedToGameExceptionTest {
    
    private static GameResultsModifier modifierWithoutPlayers;
    private static GameResultsModifier modifierWithBartek;
    
    @BeforeClass
    public static void setup() {
        modifierWithoutPlayers = GameResultsModifier.newModifier(new HashSet<>(), null);
        Set<GamePlayer> players = new HashSet<>();
        players.add(new DiceGamePlayer("Bartek"));
        modifierWithBartek = GameResultsModifier.newModifier(players, null);
    }

    @Test(expected = PlayerHasNotBeenAddedToGameException.class)
    public void testAddPointsForEmptyPlayers() throws GameException {
        modifierWithoutPlayers.addPointsFor(new DiceGamePlayer("Tomek"), BigDecimal.TEN);
    }
    
    @Test(expected = PlayerHasNotBeenAddedToGameException.class)
    public void testAddTurnForEmptyPlayers() throws GameException {
        modifierWithoutPlayers.incrementTurnFor(new DiceGamePlayer("Tomek"));
    }
    
    @Test(expected = PlayerHasNotBeenAddedToGameException.class)
    public void testAddWinningTurnForEmptyPlayers() throws GameException {
        modifierWithoutPlayers.incrementWinningTurnFor(new DiceGamePlayer("Tomek"));
    }
    
    @Test(expected = PlayerHasNotBeenAddedToGameException.class)
    public void testAddPointsForNotEmptyPlayers() throws GameException {
        modifierWithBartek.addPointsFor(new DiceGamePlayer("Tomek"), BigDecimal.TEN);
    }
    
    @Test(expected = PlayerHasNotBeenAddedToGameException.class)
    public void testAddTurnForNotEmptyPlayers() throws GameException {
        modifierWithBartek.incrementTurnFor(new DiceGamePlayer("Tomek"));
    }
    
    @Test(expected = PlayerHasNotBeenAddedToGameException.class)
    public void testAddWinningTurnForNotEmptyPlayers() throws GameException {
        modifierWithBartek.incrementWinningTurnFor(new DiceGamePlayer("Tomek"));
    }

}
