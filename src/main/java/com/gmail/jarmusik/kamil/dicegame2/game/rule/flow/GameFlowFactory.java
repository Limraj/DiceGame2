/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.flow;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameFlowFactory {
    
    public static GameFlow createFlowGameDice() {
        return new DiceGameFlow();
    }
    
}
