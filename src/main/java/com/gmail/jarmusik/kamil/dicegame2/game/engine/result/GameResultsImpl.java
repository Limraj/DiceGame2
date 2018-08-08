/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.result;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.player.DiceGamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
class GameResultsImpl implements GameResults {
    
    private final Map<GamePlayer, PlayerResult> results = new LinkedHashMap<>();
    private final Map<String, PlayerResult> resultsForNames = new LinkedHashMap<>();
    private final RulesOfWinning rulesOfWinning;
    
    GameResultsImpl(Map<GamePlayer, PlayerResultModifier> modifiers, RulesOfWinning rulesOfWinning) {
        modifiers.forEach((player, modifier) -> {
            PlayerResult result = modifier.toPlayerResult();
            results.put(player, result);
            resultsForNames.put(player.getName(), result);
        });
        this.rulesOfWinning = rulesOfWinning;
    }

    @Override
    public PlayerResult getPlayerResultFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        return getPlayerResultFor(player.getName());
    }

    @Override
    public PlayerResult getPlayerResultFor(String namePlayer) throws PlayerHasNotBeenAddedToGameException {
        GamePlayer player = new DiceGamePlayer(namePlayer);
        throwIfPlayerHasNotBeenAddedToGame(player);
        return results.get(player);
    }

    @Override
    public GamePlayer getLeader() {
        return Verdict.determineLeader(results, rulesOfWinning);
    }
    
    @Override
    public List<GamePlayer> getPeleton() {
        return Verdict.determinePeleton(results, rulesOfWinning);
    }
    
    @Override
    public void printResults() {
        System.out.println("-----------------");
        getPeleton().forEach(a -> {
            System.out.println(a + " - " + results.get(a));
        });
        System.out.println("Winner: " + getLeader());
        System.out.println("-----------------\n");
    }

    @Override
    public boolean containsGamePlayer(GamePlayer gamePlayer) {
        return this.results.containsKey(gamePlayer) && this.resultsForNames.containsKey(gamePlayer.getName());
    }
    
    private void throwIfPlayerHasNotBeenAddedToGame(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        if(!results.containsKey(player))
            throw new PlayerHasNotBeenAddedToGameException(player);
        if(!resultsForNames.containsKey(player.getName()))
            throw new PlayerHasNotBeenAddedToGameException(player);
    }

}
