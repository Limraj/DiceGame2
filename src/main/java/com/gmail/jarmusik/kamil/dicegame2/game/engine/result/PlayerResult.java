package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil-Tomasz
 */
public interface PlayerResult {
    int getNumberTurnCurrent();
    int getNumberWinningTurns();
    boolean isEmpty();
    BigDecimal getPoints();
}
