/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.AccessFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import java.util.Set;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameEngine {
    boolean hasStep();
    void reset();
    GameEngine nextPlayer() throws GameException;
    GameResults getGameResults();
    void debugMode(boolean debug);
    static GameEngine newEngine(Set<GamePlayer> players, GameRules rules, AccessFlow accessFlow) {
        return new DiceGameEngine(players, rules, accessFlow);
    }
}
