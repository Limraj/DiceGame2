/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import java.math.BigDecimal;
import lombok.Getter;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
class PlayerResultImpl implements PlayerResult {
    
    private final BigDecimal points;
    private final int numberWinningTurns;
    private final int numberTurnCurrent;
    private final boolean empty;
    
    public PlayerResultImpl(int numberTurnCurrent, BigDecimal points, int numberWinningTurns) {
        this.numberTurnCurrent = numberTurnCurrent;
        this.points = points;
        this.numberWinningTurns = numberWinningTurns;
        this.empty = false;
    }
    
    private PlayerResultImpl() {
        this.numberTurnCurrent = 0;
        this.points = BigDecimal.ZERO;
        this.numberWinningTurns = 0;
        this.empty = true;
    }
    
    @Override
    public boolean isEmpty() {
        return empty;
    }
}
