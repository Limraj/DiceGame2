/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameEngine {
    boolean hasStepForNextPlayer();
    void reset();
    GameEngine nextPlayer() throws GameException;
    GameResults getGameResults();
    void debugMode(boolean debug);
}
