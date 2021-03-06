/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2;

import com.gmail.jarmusik.kamil.dicegame2.game.DiceGame;
import com.gmail.jarmusik.kamil.dicegame2.game.Game;
import com.gmail.jarmusik.kamil.dicegame2.game.GameFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameActionFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.DiceGameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.DiceCube;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlowFactory;
import java.math.BigDecimal;
import java.util.Comparator;
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
        
        GameFlow flow = new GameFlow() {
            @Override
            public boolean isWonTurn(RollDicesResult result) {
                return (result.getNumberRollCurrent() == 1 && (result.getNumberMeshes() == 7 
                        || result.getNumberMeshes() == 11));
            }

            @Override
            public boolean isLostTurn(RollDicesResult result) {
                return result.getNumberRollCurrent() == 1 && (result.getNumberMeshes() == 6 
                        || result.getNumberMeshes() == 12 || result.getNumberMeshes() == 30);
            }

            @Override
            public void scheduleIfLostTurn(RollDicesResult result, List<GameAction> schedule) {
                schedule.add(GameActionFactory.addPointsMaxToEndTurn(result.getGamePlayer(), result.getNumberRollCurrent()));
            }

            @Override
            public void scheduleIfWonTurn(RollDicesResult result, List<GameAction> schedule) {
                schedule.clear();
                schedule.add(GameActionFactory.incrementWinningTurn(result.getGamePlayer()));
            }

            @Override
            public void scheduleIfNotWonAndNotLostTurn(RollDicesResult result, List<GameAction> schedule) {
                schedule.add(GameActionFactory.addPoints(result.getGamePlayer(), pointsScoredPerRoll(result)));
            }

            @Override
            public Comparator<PlayerResult> rulesOfWinning() {
                return (PlayerResult o1, PlayerResult o2) -> {
                    int winnigTurns = o2.getNumberWinningTurns() - o1.getNumberWinningTurns();
                    return winnigTurns == 0 ? o1.getPoints().compareTo(o2.getPoints()) : winnigTurns;
                };
            }

            @Override
            public BigDecimal pointsScoredPerRoll(RollDicesResult result) {
                return GameFlowFactory.createFlowDiceGame().pointsScoredPerRoll(result);   
            }
        };

        GameRules rules = DiceGameRules.builder(flow)
                .addDice(new DiceCube())
                .addDice(new DiceCube())
                .addDice(new DiceCube())
                .addDice(new DiceCube())
                .addDice(new DiceCube())
                .numberRolls(20)
                .numberTurns(100)
                .build();
        
        Game custom = new DiceGame.Builder(rules)
                .addPlayer("Kamil")
                .addPlayer("Tomek")
                .addPlayer("Iza")
                .addPlayer("Zuzia")
                .addPlayer("Bartek")
                .addPlayer("Fiona")
                .addPlayer("Ula")
                .addPlayer("Timon")
                .addPlayer("Ewa")
                .build();
        
        custom.debugMode(true);
        custom.start();
        custom.printResults();
    }
    
}
