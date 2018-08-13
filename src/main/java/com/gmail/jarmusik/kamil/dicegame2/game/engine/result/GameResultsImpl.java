/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.util.logging.Level;
import lombok.extern.java.Log;

/**
 *
 * @author Kamil-Tomasz
 */
@Log
class GameResultsImpl implements GameResults {
    
    private final Map<GamePlayer, PlayerResult> results = new LinkedHashMap<>();
    private final RulesOfWinning rulesOfWinning;
    
    GameResultsImpl(Map<GamePlayer, PlayerResultModifier> modifiers, RulesOfWinning rulesOfWinning) {
        modifiers.forEach((player, modifier) -> {
            PlayerResult result = modifier.newPlayerResult();
            results.put(player, result);
        });
        this.rulesOfWinning = rulesOfWinning;
    }

    @Override
    public PlayerResult getPlayerResultFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        throwIfPlayerHasNotBeenAddedToGame(player);
        return results.get(player);
    }

    @Override
    public GamePlayer getLeader() {
        return Verdict.determineLeader(results, rulesOfWinning);
    }
    
    @Override
    public List<GamePlayer> getPeleton() {
        return Verdict.determinePeleton(results, rulesOfWinning);
    }
    
    @Override
    public void printResults() {
        System.out.println("Result:\n" + this);
    }

    @Override
    public String toString() {
        StringBuilder logPlan = new StringBuilder("\n-----------------\n");
        getPeleton().forEach(a -> {
            logPlan.append(a)
                    .append(" - ")
                    .append(results.get(a))
                    .append("\n");
        });
        logPlan.append("Winner: ")
                .append(getLeader())
                .append("\n-----------------\n");
        return logPlan.toString();
    }

    private void throwIfPlayerHasNotBeenAddedToGame(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        if(!results.containsKey(player))
            throw new PlayerHasNotBeenAddedToGameException(player);
    }

}
