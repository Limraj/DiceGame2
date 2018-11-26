/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import static com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameActionFactory.*;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.RollDicesResult;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author Kamil-Tomasz
 */
class GameFlowImpl implements GameFlow {

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
    public void scheduleIfLostTurn(RollDicesResult result, List<GameAction> schedule) {
        //Jeśli przegra turę dodawana jest maksymalna możliwa liczba punktów za turę;
        schedule.clear();
        schedule.add(addPointsMaxPerTurn(result.getGamePlayer()));
    }

    @Override
    public void scheduleIfWonTurn(RollDicesResult result, List<GameAction> schedule) {
        //Jeśli wygra turę dodawana jest wygrana tura;
        schedule.clear();
        schedule.add(incrementWinningTurn(result.getGamePlayer()));
    }

    @Override
    public void scheduleIfNotWonAndNotLostTurn(RollDicesResult result, List<GameAction> schedule) {
        //Jeśli nie wygra ani nie przegra tury to dodawane są punkty obiczane według zasady z punktu 4
        schedule.add(addPoints(result.getGamePlayer(), pointsScoredPerRoll(result)));
    }

    @Override
    public Comparator<PlayerResult> rulesOfWinning() {
        //Wygrywa gracz, który zbierze mniejszą liczbę punktów;
        return (PlayerResult o1, PlayerResult o2) -> {
            return o1.getPoints().compareTo(o2.getPoints());
        };
    }
   
}
