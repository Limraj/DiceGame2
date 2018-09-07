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
public class GameRuntimeException extends RuntimeException {

    public GameRuntimeException() {
    }

    public GameRuntimeException(String message) {
        super(message);
    }

    public GameRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
