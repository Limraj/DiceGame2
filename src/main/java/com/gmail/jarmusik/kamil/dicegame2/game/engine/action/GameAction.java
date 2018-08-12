/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.action;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameAction {
    boolean execute(GameResultsModifier modifier, final GameRules rules);
}
