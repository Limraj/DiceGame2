/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2;

import com.gmail.jarmusik.kamil.dicegame2.game.DiceGame;
import com.gmail.jarmusik.kamil.dicegame2.game.Game;
import com.gmail.jarmusik.kamil.dicegame2.game.GameFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRulesFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.dice.Dice;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public class Start {
    
    public static void main(String[] args) {

        //Tworzymy grę, szczegóły wewnątrz statycznej metody diceGameFiveTurnsTenRollsTwoDice:
        Game diceGame = GameFactory.diceGameTwoPlayersFiveTurnsTenRollsTwoDices();
        
        //Debug mode - są wyświetlane sumy oczek uzyskanych w każdym rzucie, oraz liczba
        //punktów przypadających na rzut po przeliczeniu według zasady z punktu 4
        //Poza tym ten tryb wyświetla lidera po każdej turze i sumy punktów;
        diceGame.debugMode(true);
        diceGame.start();
        diceGame.printResults();
        
        System.out.println("CUSTOM___________________");
        
        GameFlow flow = GameFlowFactory.createFlowGameDice();
        GameRules rules = GameRulesFactory.createRulesFiveTurnsTenRollsTwoDices(flow);
        
        Game custom = new DiceGame.Builder()
                .addPlayer("Kamil")
                .addPlayer("Tomek")
                .addPlayer("Iza")
                .addPlayer("Zuzia")
                .addPlayer("Bartek")
                .addPlayer("Fiona")
                .addPlayer("Ula")
                .addPlayer("Timon")
                .addPlayer("Ewa")
                .rules(rules)
                .build();
        
        custom.debugMode(true);
        custom.start();
        custom.printResults();

    }
    
}
