package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NumberOfStepsHasExceededException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.TurnLoggable;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.TurnLogger;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifierImpl;
import com.gmail.jarmusik.kamil.dicegame2.game.player.PlayersShiftRegister;
import com.gmail.jarmusik.kamil.dicegame2.util.IterableShift;
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil-Tomasz
 */
public class DiceGameEngine implements GameEngine {
    
    private final IterableShift<GamePlayer> playersRegisterShift;
    private final GameResultsModifier modifier;
    private final TurnLoggable logger;
    private final GameRules rules;
    private int numberStepCurrent;
    private boolean debugMode;

    public DiceGameEngine(Set<GamePlayer> players, GameRules rules) {
        this.logger = new TurnLogger();
        logger.loadingEngine();
        this.playersRegisterShift = new PlayersShiftRegister(players);
        this.modifier = new GameResultsModifierImpl(players, rules.getGameFlow().rulesOfWinning());
        this.rules = rules;
        this.numberStepCurrent = 0;
        this.debugMode = false;
        logger.okEngine();
    }

    @Override
    public GameEngine nextPlayer() throws GameException {
        checkHasStepForNextPlayerAndIncrementNumberStepCurrent();
        GamePlayer player = playersRegisterShift.next();
        logger.startStepLog(player, modifier.newGameResults());
        modifier.incrementTurnFor(player);
        executeFor(player);
        logger.endStepLog(player, modifier.newGameResults());
        return this;
    }
    
    private void checkHasStepForNextPlayerAndIncrementNumberStepCurrent() throws NumberOfStepsHasExceededException {
        if(!hasStepForNextPlayer())
            throw new NumberOfStepsHasExceededException();
        numberStepCurrent++;
    }
    
    private void executeFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        GameFlow flowGame = rules.getGameFlow();
        int numberRollCurrent = 0, pointsRoll;
        do {
            numberRollCurrent++;
            pointsRoll = rules.rollDices();
            if(debugMode)
                logger.turnLog(numberRollCurrent, pointsRoll, flowGame);
            modifiedResults(numberRollCurrent, pointsRoll, player);
        } while(!isEndTurnForPlayer(numberRollCurrent, pointsRoll));
    }

    private boolean isEndTurnForPlayer(int numberRollCurrent, int pointsRoll) {
        GameFlow gameFlow = rules.getGameFlow();
        return gameFlow.isEndTurnForPlayer(numberRollCurrent, pointsRoll)
                || numberRollCurrent > rules.getNumberRolls();
    }

    private void modifiedResults(int numberRollCurrent, int pointsRoll, GamePlayer player) {
  
        GameFlow gameFlow = rules.getGameFlow();
        if(gameFlow.isLostTurn(numberRollCurrent, pointsRoll))
            gameFlow.doIfLostTurn(numberRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(modifier, rules));
        
        if(gameFlow.isWonTurn(numberRollCurrent, pointsRoll))
            gameFlow.doIfWonTurn(numberRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(modifier, rules));
        
        if(!gameFlow.isLostTurn(numberRollCurrent, pointsRoll) 
                && !gameFlow.isWonTurn(numberRollCurrent, pointsRoll))
            gameFlow.doIfNotWonAndLostTurn(numberRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(modifier, rules));

    }

    @Override
    public void reset() {
        numberStepCurrent = 0;
        playersRegisterShift.reset();
        modifier.reset();
    }

    @Override
    public boolean hasStepForNextPlayer() {
        return numberStepCurrent < rules.getNumberTurns() * playersRegisterShift.size();
    }

    @Override
    public GameResults getGameResults() {
        return modifier.newGameResults();
    }

    @Override
    public void debugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
