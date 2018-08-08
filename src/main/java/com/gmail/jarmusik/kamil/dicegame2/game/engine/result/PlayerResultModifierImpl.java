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
class PlayerResultModifierImpl implements PlayerResultModifier {

    private int numberWinningTurns;
    private BigDecimal points;
    private int numberTurnCurrent;

    public PlayerResultModifierImpl() {
        numberWinningTurns = 0;
        points = BigDecimal.ZERO;
        numberTurnCurrent = 0;
    }
    
    public PlayerResultModifierImpl(PlayerResult playerResult) {
        numberWinningTurns = playerResult.getNumberWinningTurns();
        points = playerResult.getPoints();
        numberTurnCurrent = playerResult.getNumberTurnCurrent();
    }

    @Override
    public boolean addPoints(BigDecimal points) {
        this.points = this.points.add(points);
        return true;
    }
    
    @Override
    public int incrementAndGetNumberTurnCurrent() {
        return ++numberTurnCurrent;
    }

    @Override
    public PlayerResult toPlayerResult() {
        return new PlayerResultImpl(numberTurnCurrent, points, numberWinningTurns);
    }

    @Override
    public boolean reset() {
        this.numberTurnCurrent = 0;
        this.numberWinningTurns = 0;
        this.points = BigDecimal.ZERO;
        return true;
    }

    @Override
    public int incrementAndGetNumberWinningTurns() {
        return ++numberWinningTurns;
    }
}
