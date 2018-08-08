/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;

/**
 *
 * @author Kamil-Tomasz
 */
public interface Game {
    void start();
    void printResults();
    void debugMode(boolean debug);
    GameResults getGameResults();
}
