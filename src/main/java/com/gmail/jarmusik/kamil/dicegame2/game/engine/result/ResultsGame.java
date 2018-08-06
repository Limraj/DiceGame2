/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import java.util.List;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
public interface ResultsGame {
    PlayerResult getPlayerResultFor(String namePlayer) throws PlayerHasNotBeenAddedToGameException;
    PlayerResult getPlayerResultFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException;
    GamePlayer getLeader();
    List<GamePlayer> getPeleton();
    void printResults();
}
