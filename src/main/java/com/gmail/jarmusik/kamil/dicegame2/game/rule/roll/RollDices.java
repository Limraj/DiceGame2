/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.roll;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.Dice;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public interface RollDices {
    RollDicesResult make();

    public static RollDicesImpl.RollDicesImplBuilder builder(List<Dice> dices) {
        return new RollDicesImpl.RollDicesImplBuilder().dices(dices);
    }
}
