/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.Executable;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameActionNotExecutable extends GameActionException {
    public GameActionNotExecutable(GameAction action) {
        super(String.format("action: {0} is not instance of {1}", new Object[]{action, Executable.class.getName()}));
    }
}
