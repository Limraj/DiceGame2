/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.util.ShiftRegister;
import java.util.Set;

/**
 *
 * @author Kamil-Tomasz
 */
class PlayersShiftRegister extends ShiftRegister<GamePlayer> {

    PlayersShiftRegister(Set<GamePlayer> players) {
        super(players);
    }
}
