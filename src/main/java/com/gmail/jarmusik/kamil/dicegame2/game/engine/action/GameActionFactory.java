/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.action;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameActionFactory {
    
    public static GameAction addPoints(RollDicesResult result) {
        return new AddPoints(result);
    }
    
    public static GameAction addPointsMaxPerTurn(GamePlayer player) {
        return new AddPointsMaxPerTurn(player);
    }
    
    public static GameAction addPointsMaxToEndTurn(GamePlayer player, int numberRollCurrent) {
        return new AddPointsMaxToEndTurn(player, numberRollCurrent);
    }
    
    public static GameAction incrementWinningTurn(GamePlayer player) {
        return new IncrementWinningTurn(player);
    }
}
