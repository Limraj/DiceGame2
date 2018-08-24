/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameActionException extends GameException {

    public GameActionException(String message) {
        super(message);
    }

    public GameActionException(String message, Exception ex) {
        super(message, ex);
    }

    public GameActionException() {
        super();
    }
    
}
