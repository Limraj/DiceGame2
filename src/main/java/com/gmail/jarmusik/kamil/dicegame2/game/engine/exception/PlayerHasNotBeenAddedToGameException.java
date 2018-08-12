/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
public class PlayerHasNotBeenAddedToGameException extends GameException {
    
    public PlayerHasNotBeenAddedToGameException(GamePlayer player) {
        super("The data entered does not match any player added to the game. The Player entered:" + player);
    }
    
    public PlayerHasNotBeenAddedToGameException(String playerName) {
        super("The data entered does not match any player added to the game. The Player entered:" + playerName);
    }

}
