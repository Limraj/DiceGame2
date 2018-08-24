/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.util.List;


/**
 *
 * @author Kamil-Tomasz
 */
public interface GameResults {
    PlayerResult getPlayerResultFor(GamePlayer player);
    GamePlayer getLeader();
    List<GamePlayer> getPeleton();
    void printResults();
}
