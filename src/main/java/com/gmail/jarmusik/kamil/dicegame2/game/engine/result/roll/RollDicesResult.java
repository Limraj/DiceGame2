/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll;

import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
public interface RollDicesResult {
    int getNumberRollCurrent();
    int getNumberMeshes();
    GamePlayer getGamePlayer();
}
