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
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;

/**
 *
 * @author Kamil-Tomasz
 */
class GameResultsImpl implements ResultsGame {
    
    private final Map<GamePlayer, PlayerResultModifier> results = new LinkedHashMap<>();
    private final Map<String, PlayerResultModifier> resultsForNames = new LinkedHashMap<>();
    private final RulesOfWinning rulesOfWinning;

    protected GameResultsImpl(Set<GamePlayer> players, RulesOfWinning rulesOfWinning) {
        players.forEach((player) -> {
            PlayerResultModifier modifier = new PlayerResultModifierImpl();
            results.put(player, modifier);
            resultsForNames.put(player.getName(), modifier);
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
        return results.get(player).toPlayerResult();
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
            System.out.println(a + " - " + results.get(a).toPlayerResult());
        });
        System.out.println("Winner: " + getLeader());
        System.out.println("-----------------\n");
    }

    protected Map<GamePlayer, PlayerResultModifier> getResults() {
        return results;
    }
    
    protected void throwIfPlayerHasNotBeenAddedToGame(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        if(!results.containsKey(player))
            throw new PlayerHasNotBeenAddedToGameException(player);
    }
}
