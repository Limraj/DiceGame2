/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author Kamil-Tomasz
 */
public interface GameFlow {
    boolean isWonTurn(RollDicesResult result);
    boolean isLostTurn(RollDicesResult result);
    void scheduleIfLostTurn(RollDicesResult result, List<GameAction> schedule);
    void scheduleIfWonTurn(RollDicesResult result, List<GameAction> schedule);
    void scheduleIfNotWonAndNotLostTurn(RollDicesResult result, List<GameAction> schedule);
    RulesOfWinning rulesOfWinning();
    BigDecimal pointsScoredPerRoll(RollDicesResult result);
    
    default boolean isEndTurnForPlayer(RollDicesResult result) {
        //Jeśli przegra, albo wygra turę to koniec tury;
        return isLostTurn(result) || isWonTurn(result);
    };
}
