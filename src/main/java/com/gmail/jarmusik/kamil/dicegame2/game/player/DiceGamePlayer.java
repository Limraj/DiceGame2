/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.player;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@ToString
@EqualsAndHashCode
public class DiceGamePlayer implements GamePlayer {
    
    private final String name;

    public DiceGamePlayer(String name) {
        this.name = name;
    }

}
