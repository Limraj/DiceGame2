/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.action.ActionGame;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.action.AddPoints;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.action.AddPointsMaxToEndTurn;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.action.AddWinningTurn;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
class DiceGameFlow implements GameFlow {

    @Override
    public boolean isWonTurn(int numberRollCurrent, int pointsRoll) {
        //Jeżeli w pierwszym rzucie tury gracz uzyska sumę oczek 
        //z obu kości równą 7 lub 11, lub w dowolnym rzucie 
        //uzyska sumę oczek z obu kości równą 5, 
        //wygrywa turę przed czasem;
        return (numberRollCurrent == 1 && (pointsRoll == 7 || pointsRoll == 11)) || pointsRoll == 5;
    }

    @Override
    public boolean isLostTurn(int numberRollCurrent, int pointsRoll) {
        //Jeżeli gracz w pierwszym rzucie tury uzyska sumę oczek 
        //z obu kości równą 2 lub 12, przegrywa turę przed czasem;
        return numberRollCurrent == 1 && (pointsRoll == 2 || pointsRoll == 12);
    }

    @Override
    public BigDecimal pointsScoredPerRoll(int numberRollCurrent, int pointsRoll) {
        //punkty_z_danego_rzutu = suma_oczek_z_rzutu/numer_rzutu;
        //suma_punktów_z_danej_tury = suma_punktów_z_wszystkich_rzutów_z_danej_tury;
        return BigDecimal.valueOf(pointsRoll).divide(BigDecimal.valueOf(numberRollCurrent), 2, RoundingMode.HALF_UP);
    }

    @Override
    public List<ActionGame> doIfLostTurn(int numberRollCurrent, int pointsRoll, GamePlayer playerGame) {
        //Jeśli przegra turę dodawana jest maksymalna możliwa liczba punktów do końca tury;
        List<ActionGame> actions = new ArrayList<>();
        actions.add(new AddPointsMaxToEndTurn(playerGame, numberRollCurrent));
        return actions;
    }

    @Override
    public List<ActionGame> doIfWonTurn(int numberRollCurrent, int pointsRoll, GamePlayer playerGame) {
        //Jeśli wygra turę dodawana jest wygrana tura;
        List<ActionGame> actions = new ArrayList<>();
        actions.add(new AddWinningTurn(playerGame));
        return actions;
    }

    @Override
    public List<ActionGame> doIfNotWonAndLostTurn(int numberRollCurrent, int pointsRoll, GamePlayer playerGame) {
        //Jeśli nie wygra ani nie przegra tury to dodawane są punkty obiczane według zasady z punktu 4
        List<ActionGame> actions = new ArrayList<>();
        actions.add(AddPoints.builder()
                .numberRollCurrent(numberRollCurrent)
                .pointsRoll(pointsRoll)
                .playerGame(playerGame)
                .build());
        return actions;
    }

    @Override
    public RulesOfWinning rulesOfWinning() {
        //Wygrywa gracz, który zbierze mniejszą liczbę punktów;
        return () -> (PlayerResult o1, PlayerResult o2) -> {
            return o1.getPoints().compareTo(o2.getPoints());
        };
    }
}
