/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.action.ActionGame;
import java.math.BigDecimal;
import java.util.List;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameFlow {
    boolean isWonTurn(int numberOfRollCurrent, int pointsRoll);
    boolean isLostTurn(int numberOfRollCurrent, int pointsRoll);
    List<ActionGame> doIfLostTurn(int numberOfRollCurrent, int pointsRoll, GamePlayer playerGame);
    List<ActionGame> doIfWonTurn(int numberOfRollCurrent, int pointsRoll, GamePlayer playerGame);
    List<ActionGame> doIfNotWonAndLostTurn(int numberOfRollCurrent, int pointsRoll, GamePlayer playerGame);
    RulesOfWinning rulesOfWinning();
    BigDecimal pointsScoredPerRoll(int numberOfRollCurrent, int pointsRoll);
    
    default boolean isEndTurn(int numberOfRollCurrent, int pointsRoll) {
        //Jeśli przegra, albo wygra turę to koniec tury;
        return isLostTurn(numberOfRollCurrent, pointsRoll) 
                || isWonTurn(numberOfRollCurrent, pointsRoll);
    };
}
