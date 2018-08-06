/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2;

import com.gmail.jarmusik.kamil.dicegame2.game.DiceGame;
import com.gmail.jarmusik.kamil.dicegame2.game.Game;
import com.gmail.jarmusik.kamil.dicegame2.game.GameFactory;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.DiceGameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.DiceGameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;

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
          
    }
    
}
