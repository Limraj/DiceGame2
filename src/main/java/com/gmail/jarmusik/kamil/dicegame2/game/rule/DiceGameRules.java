/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.Dice;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import lombok.Getter;

/**
 *
 * @author Kamil-Tomasz
 */
@Builder
class DiceGameRules implements GameRules {
    
    @Singular(value = "addDice") private final List<Dice> dices;
    @Getter private final int numberTurns;
    @Getter private final int numberRolls;
    @Getter @NonNull private final GameFlow gameFlow;
    
    public static DiceGameRulesBuilder builder(GameFlow gameFlow) {
        return new DiceGameRulesBuilder().gameFlow(gameFlow);
    }

    @Override
    public int rollDices() {
        return dices.stream().mapToInt((dice) -> dice.roll()).sum();
    }

    @Override
    public int numberDices() {
        return dices.size();
    }

    @Override
    public int maxNumberMeshesForAllDices() {
        return dices.stream().mapToInt(a -> a.maxMeshes()).sum();
    }

    @Override
    public BigDecimal maxPointsToEndTurn(int numberOfRollCurrent) {
        BigDecimal accumulator = BigDecimal.ZERO;
        int maxNumberMeshesForAllDices = maxNumberMeshesForAllDices();
        for (int i = numberOfRollCurrent; i < getNumberRolls() + 1; i++)
            accumulator = accumulator.add(gameFlow.pointsScoredPerRoll(i, maxNumberMeshesForAllDices));
        return accumulator;
    }
}
