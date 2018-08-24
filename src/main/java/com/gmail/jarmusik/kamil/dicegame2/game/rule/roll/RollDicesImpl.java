/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.roll;

import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.dice.Dice;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import java.util.List;
import lombok.Builder;

/**
 *
 * @author Kamil-Tomasz
 */
@Builder
class RollDicesImpl implements RollDices {
    
    private final List<Dice> dices;
    private final int numberRollCurrent;
    private final GamePlayer gamePlayer;

    private int rollDices() {
        return dices.stream().mapToInt(dice -> dice.roll()).sum();
    }

    @Override
    public RollDicesResult make() {
        return RollDicesResultImpl.builder()
                .gamePlayer(gamePlayer)
                .numberMeshes(rollDices())
                .numberRollCurrent(numberRollCurrent)
                .build();
    }
    
}
