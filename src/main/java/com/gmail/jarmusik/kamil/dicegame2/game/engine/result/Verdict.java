/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import com.gmail.jarmusik.kamil.dicegame2.util.SortMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
public class Verdict {
    
    static GamePlayer determineLeader(Map<GamePlayer, PlayerResultModifier> modifiers, RulesOfWinning rulesOfWinning) {
        return determinePeleton(modifiers, rulesOfWinning).get(0);
    }
    
    static List<GamePlayer> determinePeleton(Map<GamePlayer, PlayerResultModifier> modifiers, RulesOfWinning rulesOfWinning) {
        List<GamePlayer> sorted = SortMap.sortByValueToKeyList(toMapPlayerResult(modifiers), rulesOfWinning.getRules());
        //System.out.println("Sorted: " + sorted);
        return Collections.unmodifiableList(sorted);
    }
    
    private static Map<GamePlayer, PlayerResult> toMapPlayerResult(Map<GamePlayer, PlayerResultModifier> modifiers) {
        return modifiers.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
            return entry.getValue().toPlayerResult();
        }));
    } 
}
