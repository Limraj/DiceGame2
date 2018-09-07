/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.GameEngine;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameRuntimeException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.extern.java.Log;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.AccessFlow;



/**
 *
 * @author Kamil-Tomasz
 */
@Log
public class DiceGame implements Game {
    
    private final GameEngine engine;
    
    private DiceGame(Set<GamePlayer> players, GameRules rules) {
        System.out.println("Loading game...");
        engine = GameEngine.newEngine(players, rules, (AccessFlow) rules);
    }

    public static class Builder {
        //Set - nie chce żeby gracze mogli się powtarzać;
        private final Set<GamePlayer> players;
        private final GameRules rules;
        
        public Builder(GameRules rules) {
            //LinkedHashSet - chcę zachować kolejność dodawanych graczy;
            players = new LinkedHashSet<>();
            this.rules = rules;
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

        public Builder addPlayers(Set<GamePlayer> players) {
            this.players.addAll(players);
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
        execute();
    }

    @Override
    public void printResults() {
        engine.getGameResults().printResults();
    }
    
    @Override
    public GameResults getGameResults() {
        return engine.getGameResults();
    }

    @Override
    public void debugMode(boolean debug) {
        engine.debugMode(debug);
    }

    private void execute() throws GameRuntimeException {
        engine.reset();
        while(engine.hasTurn())
            engine.nextTurn();
    }
}
