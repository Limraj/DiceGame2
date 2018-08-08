/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule;

import java.math.BigDecimal;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameRules {
    int getNumberTurns();
    int getNumberRolls();
    GameFlow getGameFlow();
    
    int numberDices();
    int maxNumberMeshesForAllDices();
    int rollDices();
    BigDecimal maxPointsToEndTurn(int numberOfRollCurrent);
}
