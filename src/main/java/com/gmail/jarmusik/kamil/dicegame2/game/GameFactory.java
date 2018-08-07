/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game;

import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.Dice;
import java.util.List;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameFactory {
    
    public static Game diceGameTwoPlayersFiveTurnsTenRollsTwoDices() {
        
        //Najpierw tworzymy flow gry:
        GameFlow gameFlow = GameFlowFactory.createFlowGameDice();
        //Następnie zasady:
        GameRules rules = GameRulesFactory.createRulesFiveTurnsTenRollsTwoDices(gameFlow);

        //Tworzymy grę:
        return new DiceGame.Builder()
                .rules(rules)
                .addPlayer("Pierwszy")
                .addPlayer(new DiceGamePlayer("Drugi"))
                .build();
    }
    
    public static Game diceGameFiveTurnsTenRollsTwoDice(List<Dice> dices) {
        
        //Najpierw tworzymy flow gry:
        GameFlow flowGame = GameFlowFactory.createFlowGameDice();
        //Następnie zasady:
        GameRules rules = GameRulesFactory.createRulesFiveTurnsTenRolls(flowGame, dices);
        //Tworzymy grę:
        
        return new DiceGame.Builder()
                .rules(rules)
                .addPlayer("Pierwszy")
                .addPlayer(new DiceGamePlayer("Drugi"))
                .build();
    }

}
