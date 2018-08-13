/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.Dice;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResultImpl;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import java.util.Collections;
import lombok.Getter;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;

/**
 *
 * @author Kamil-Tomasz
 */
@Builder
public class DiceGameRules implements GameRules {
    
    @Singular(value = "addDice") private final List<Dice> dices;
    @Getter private final int numberTurns;
    @Getter private final int numberRolls;
    @Getter @NonNull private final GameFlow gameFlow;
    
    public static DiceGameRulesBuilder builder(GameFlow flow) {
        return new DiceGameRulesBuilder().gameFlow(flow);
    }

    @Override
    public List<Dice> getDices() {
        return Collections.unmodifiableList(dices);
    }

    @Override
    public int maxNumberMeshesForAllDices() {
        return dices.stream().mapToInt(dice -> dice.maxNumberMeshes()).sum();
    }

    @Override
    public BigDecimal maxPointsToEndTurn(int numberRollCurrent) {
        BigDecimal accumulator = BigDecimal.ZERO;
        for (int i = numberRollCurrent; i < getNumberRolls() + 1; i++) {
            RollDicesResult result = RollDicesResultImpl.builder()
                .numberMeshes(maxNumberMeshesForAllDices())
                .numberRollCurrent(i)
                .build();
            accumulator = accumulator.add(gameFlow.pointsScoredPerRoll(result));
        }
        return accumulator;
    }
}
