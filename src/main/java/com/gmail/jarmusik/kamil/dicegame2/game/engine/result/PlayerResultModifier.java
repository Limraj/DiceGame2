/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import java.math.BigDecimal;

/**
 *
 * @author Kamil-Tomasz
 */
interface PlayerResultModifier {
    boolean addPoints(BigDecimal points);
    boolean reset();
    int incrementAndGetNumberWinningTurns();
    int incrementAndGetNumberTurnCurrent();
    PlayerResult toPlayerResult();
}
