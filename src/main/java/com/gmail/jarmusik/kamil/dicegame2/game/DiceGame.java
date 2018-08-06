/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.DiceGameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.GameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.ResultsGame;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.DiceGameRules;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedHashSet;
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGame implements Game {
    
    private final GameEngine engine;
    
    private DiceGame(Set<GamePlayer> players, GameRules rules) {
        System.out.println("Load game...");
        engine = new DiceGameEngine(players, rules);
    }

    public static class Builder {
        //Set - nie chce żeby gracze mogli się powtarzać;
        private final Set<GamePlayer> players;
        private GameRules rules;
        
        public Builder() {
            //LinkedHashSet - chcę zachować kolejność dodawanych graczy;
            players = new LinkedHashSet<>();
            //rules = DiceGameRules.newRules();
        }

        public Builder addPlayer(GamePlayer player) {
            players.add(player);
            return this;
        }

        public Builder addPlayer(String namePlayer) {
            GamePlayer player = new DiceGamePlayer(namePlayer);
            players.add(player);
            return this;
        }

        public Builder addPlayers(LinkedHashSet<GamePlayer> players) {
            this.players.addAll(players);
            return this;
        }

        public Builder rules(GameRules rules) {
            this.rules = rules;
            return this;
        }

        public Game build() {
            if(players.isEmpty())
                throw new IllegalStateException("No player has been added.");
            return new DiceGame(players, rules);
        }
    }

    @Override
    public void start() {
        System.out.println("Start game!");
        try {
            execute();
        } catch (NumberOfTurnsHasExceededException ex) {
            Logger.getLogger(DiceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void execute() throws NumberOfTurnsHasExceededException {
        engine.reset();
        while(engine.hasNextTurn())
            engine.nextTurn();
    }

    @Override
    public void printResults() {
        engine.getGameResults().printResults();
    }
    
    @Override
    public ResultsGame getGameResults() {
        return engine.getGameResults();
    }

    @Override
    public void debugMode(boolean debug) {
        engine.debugMode(debug);
    }
}
