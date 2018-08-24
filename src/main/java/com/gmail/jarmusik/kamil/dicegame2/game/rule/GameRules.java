/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.Dice;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameRules {
    int getNumberTurns();
    int getNumberRolls();
    List<Dice> getDices();
    
    int maxNumberMeshesForAllDices();
    BigDecimal maxPointsToEndTurn(int numberRollCurrent);
}
