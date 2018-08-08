package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.TurnLoggable;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.TurnLogger;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifierImpl;
import com.gmail.jarmusik.kamil.dicegame2.game.player.PlayersShiftRegister;
import com.gmail.jarmusik.kamil.dicegame2.util.IterableShift;
import java.util.logging.Level;
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.RulesOfWinning;
import lombok.extern.java.Log;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil-Tomasz
 */
@Log
public class DiceGameEngine implements GameEngine {
    
    private final IterableShift<GamePlayer> playersRegisterShift;
    private final GameResultsModifier modifier;
    private final TurnLoggable logger;
    private final GameRules rules;
    private int totalNumberTurns;
    private boolean debugMode;

    public DiceGameEngine(Set<GamePlayer> players, GameRules rules) {
        log.log(Level.INFO, "Load engine...");
        this.playersRegisterShift = new PlayersShiftRegister(players);
        this.modifier = new GameResultsModifierImpl(players);
        this.logger = new TurnLogger();
        this.rules = rules;
        this.totalNumberTurns = 0;
        this.debugMode = false;
        log.log(Level.INFO, " ok");
    }

    @Override
    public GameEngine nextTurn() throws NumberOfTurnsHasExceededException {
        checkHasNextTurnAndIterate();
        GamePlayer player = playersRegisterShift.next();
        RulesOfWinning rulesOfWinning = rules.getGameFlow().rulesOfWinning();
        try {
            GameResults results = modifier.toGameResults(rulesOfWinning);
            logger.startTurnLog(player, results);
            modifier.addTurnFor(player);
            executeFor(player);
            results = modifier.toGameResults(rulesOfWinning);
            logger.endTurnLog(player, results);
        } catch (PlayerHasNotBeenAddedToGameException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
    private void checkHasNextTurnAndIterate() throws NumberOfTurnsHasExceededException {
        if(!hasNextTurn())
            throw new NumberOfTurnsHasExceededException();
        totalNumberTurns++;
    }
    
    private void executeFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        GameFlow flowGame = rules.getGameFlow();
        int numberRollCurrent = 0, pointsRoll;
        do {
            numberRollCurrent++;
            pointsRoll = rules.rollDices();
            if(debugMode)
                logger.turnLog(numberRollCurrent, pointsRoll, flowGame);
            modifiedResults(flowGame, numberRollCurrent, pointsRoll, player);
        } while(!isEndTurn(flowGame, numberRollCurrent, pointsRoll));
    }

    private boolean isEndTurn(GameFlow flowGame, int numberRollCurrent, int pointsRoll) {
        return flowGame.isEndTurn(numberRollCurrent, pointsRoll)
                || numberRollCurrent > rules.getNumberRolls();
    }

    private void modifiedResults(GameFlow flowGame, int numberRollCurrent, int pointsRoll, GamePlayer player) {
        System.out.println("TEST: " + modifier.toGameResults(flowGame.rulesOfWinning()));
        if(flowGame.isLostTurn(numberRollCurrent, pointsRoll))
            flowGame.doIfLostTurn(numberRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(modifier, rules));
        
        if(flowGame.isWonTurn(numberRollCurrent, pointsRoll))
            flowGame.doIfWonTurn(numberRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(modifier, rules));
        
        if(!flowGame.isLostTurn(numberRollCurrent, pointsRoll) 
                && !flowGame.isWonTurn(numberRollCurrent, pointsRoll))
            flowGame.doIfNotWonAndLostTurn(numberRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(modifier, rules));
        System.out.println("TEST2: " + modifier.toGameResults(flowGame.rulesOfWinning()));
    }

    @Override
    public void reset() {
        totalNumberTurns = 0;
        playersRegisterShift.reset();
        modifier.reset();
    }

    @Override
    public boolean hasNextTurn() {
        return totalNumberTurns < rules.getNumberTurns() * playersRegisterShift.size();
    }

    @Override
    public GameResults getGameResults() {
        return modifier.toGameResults(rules.getGameFlow().rulesOfWinning());
    }

    @Override
    public void debugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
