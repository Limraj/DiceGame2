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
class PlayerResultImpl implements PlayerResult {
    
    private final BigDecimal points;
    private final int numberOfWinningTurns;
    private final int currentTurnNumber;
    private final boolean empty;
    public static PlayerResultImpl INSTANCE_EMPTY = new PlayerResultImpl();

    public PlayerResultImpl(int currentTurnNumber, BigDecimal points, int numberOfWinningTurns) {
        this.currentTurnNumber = currentTurnNumber;
        this.points = points;
        this.numberOfWinningTurns = numberOfWinningTurns;
        this.empty = false;
    }
    
    private PlayerResultImpl() {
        this.currentTurnNumber = 0;
        this.points = BigDecimal.ZERO;
        this.numberOfWinningTurns = 0;
        this.empty = true;
    }

    @Override
    public BigDecimal getPoints() {
        return points;
    }
    
    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public int getNumberOfWinningTurns() {
        return numberOfWinningTurns;
    }

    @Override
    public int getCurrentTurnNumber() {
        return currentTurnNumber;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "points=" + points + ", numberOfWinningTurns=" + numberOfWinningTurns + '}';
    }
}
