/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.Dice;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.DiceCube;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameRulesFactory {
    public static GameRules createRulesFiveTurnsTenRollsTwoDices(GameFlow flow) {
        return DiceGameRules.builder(flow)
            //Każdemu graczowi przypada 5 tur
            .numberTurns(5)
            //W każdej turze, gracz wykonuje maksymalnie 10 rzutów
            .numberRolls(10)
            //dwoma kośćmi jednocześnie
            .addDice(new DiceCube())
            .addDice(new DiceCube())
            .build();
    }
    
    public static GameRules createRulesFiveTurnsTenRolls(GameFlow flow, List<Dice> dices) {
        return DiceGameRules.builder(flow)
            //Każdemu graczowi przypada 5 tur
            .numberTurns(5)
            //W każdej turze, gracz wykonuje maksymalnie 10 rzutów
            .numberRolls(10)
            .dices(dices)
            .build();
    }
    
    public static GameRules createRulesOneTurnTenRollsTwoDices(GameFlow flow) {
        return DiceGameRules.builder(flow)
            .numberTurns(1)
            .numberRolls(10)
            .addDice(new DiceCube())
            .addDice(new DiceCube())
            .build();
    }
}
