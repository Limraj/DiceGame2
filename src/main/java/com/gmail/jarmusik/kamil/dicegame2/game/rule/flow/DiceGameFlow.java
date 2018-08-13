/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.action.GameActionFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.action.GameAction;

/**
 *
 * @author Kamil-Tomasz
 */
class DiceGameFlow implements GameFlow {

    @Override
    public boolean isWonTurn(RollDicesResult result) {
        //Jeżeli w pierwszym rzucie tury gracz uzyska sumę oczek 
        //z obu kości równą 7 lub 11, lub w dowolnym rzucie 
        //uzyska sumę oczek z obu kości równą 5, 
        //wygrywa turę przed czasem;
        return (result.getNumberRollCurrent() == 1 && (result.getNumberMeshes() == 7 
                || result.getNumberMeshes() == 11)) || result.getNumberMeshes() == 5;
    }

    @Override
    public boolean isLostTurn(RollDicesResult result) {
        //Jeżeli gracz w pierwszym rzucie tury uzyska sumę oczek 
        //z obu kości równą 2 lub 12, przegrywa turę przed czasem;
        return result.getNumberRollCurrent() == 1 && (result.getNumberMeshes() == 2 
                || result.getNumberMeshes() == 12);
    }

    @Override
    public BigDecimal pointsScoredPerRoll(RollDicesResult result) {
        //punkty_z_danego_rzutu = suma_oczek_z_rzutu/numer_rzutu;
        //suma_punktów_z_danej_tury = suma_punktów_z_wszystkich_rzutów_z_danej_tury;
        return BigDecimal.valueOf(result.getNumberMeshes())
                .divide(BigDecimal.valueOf(result.getNumberRollCurrent()), 2, RoundingMode.HALF_UP);
    }

    @Override
    public void doIfLostTurn(RollDicesResult result, List<GameAction> actionsToTakenFromPreviousTurns) {
        //Jeśli przegra turę dodawana jest maksymalna możliwa liczba punktów za turę;
        actionsToTakenFromPreviousTurns.clear();
        actionsToTakenFromPreviousTurns.add(GameActionFactory.addPointsMaxPerTurn(result.getGamePlayer()));
    }

    @Override
    public void doIfWonTurn(RollDicesResult result, List<GameAction> actionsToTakenFromPreviousTurns) {
        //Jeśli wygra turę dodawana jest wygrana tura;
        actionsToTakenFromPreviousTurns.clear();
        actionsToTakenFromPreviousTurns.add(GameActionFactory.incrementWinningTurn(result.getGamePlayer()));
    }

    @Override
    public void doIfNotWonAndNotLostTurn(RollDicesResult result, List<GameAction> actionsToTakenFromPreviousTurns) {
        //Jeśli nie wygra ani nie przegra tury to dodawane są punkty obiczane według zasady z punktu 4
        actionsToTakenFromPreviousTurns.add(GameActionFactory.addPoints(result));
    }

    @Override
    public RulesOfWinning rulesOfWinning() {
        //Wygrywa gracz, który zbierze mniejszą liczbę punktów;
        return () -> (PlayerResult o1, PlayerResult o2) -> {
            return o1.getPoints().compareTo(o2.getPoints());
        };
    }
}
