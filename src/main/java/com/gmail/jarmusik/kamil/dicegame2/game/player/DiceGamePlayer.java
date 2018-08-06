/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.player;

import java.util.Objects;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGamePlayer implements GamePlayer {
    
    String name;

    public DiceGamePlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiceGamePlayer other = (DiceGamePlayer) obj;
        
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "name=" + name + '}';
    }
}
