/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;


/**
 *
 * @author Kamil-Tomasz
 */
class GameResultsImpl implements GameResults {
    
    private final Map<GamePlayer, PlayerResult> results = new LinkedHashMap<>();
    @Getter private final List<GamePlayer> peleton;
    @Getter private final GamePlayer leader;
    
    GameResultsImpl(Map<GamePlayer, PlayerResultModifier> modifiers, RulesOfWinning rulesOfWinning) {
        modifiers.forEach((player, modifier) -> {
            PlayerResult result = modifier.newPlayerResult();
            results.put(player, result);
        });
        this.peleton = Verdict.determinePeleton(results, rulesOfWinning);
        this.leader = Verdict.determineLeader(results, rulesOfWinning);
    }

    @Override
    public PlayerResult getPlayerResultFor(GamePlayer player) {
        if(results.containsKey(player))
            return results.get(player);
        throw new IllegalArgumentException();
    }
    
    @Override
    public void printResults() {
        System.out.println("Result:\n" + this);
    }

    @Override
    public String toString() {
        StringBuilder logPlan = new StringBuilder("\n-----------------\n");
        peleton.forEach(a -> {
            logPlan.append(a)
                    .append(" - ")
                    .append(results.get(a))
                    .append("\n");
        });
        logPlan.append("Winner: ")
                .append(leader)
                .append("\n-----------------\n");
        return logPlan.toString();
    }
}
