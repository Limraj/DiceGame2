/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.rule.roll;

import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@Builder
@ToString
class RollDicesResultImpl implements RollDicesResult {
    private final int numberRollCurrent;
    private final int numberMeshes;
    private final GamePlayer gamePlayer;
}
