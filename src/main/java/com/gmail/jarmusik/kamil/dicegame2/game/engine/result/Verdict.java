/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.util.SortMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Kamil-Tomasz
 */
public class Verdict {
    
    static GamePlayer determineLeader(Map<GamePlayer, PlayerResult> results, Comparator<PlayerResult> rulesOfWinning) {
        return determinePeleton(results, rulesOfWinning).get(0);
    }
    
    static List<GamePlayer> determinePeleton(Map<GamePlayer, PlayerResult> results, Comparator<PlayerResult> rulesOfWinning) {
        List<GamePlayer> sorted = SortMap.sortByValueToKeyList(results, rulesOfWinning);
        return Collections.unmodifiableList(sorted);
    }
}
