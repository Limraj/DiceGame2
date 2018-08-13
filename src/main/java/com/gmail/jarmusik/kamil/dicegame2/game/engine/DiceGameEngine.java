package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.GameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NumberOfStepsHasExceededException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.StepLogger;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifierImpl;
import com.gmail.jarmusik.kamil.dicegame2.game.player.PlayersShiftRegister;
import com.gmail.jarmusik.kamil.dicegame2.util.IterableShift;
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesImpl;
import java.util.ArrayList;
import java.util.List;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.action.ActionsExecutor;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.action.ActionsExecutorImpl;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDices;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.StepLoggable;

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
    private final StepLoggable logger;
    private final GameRules rules;
    private int numberStepCurrent;
    private boolean debugMode;

    public DiceGameEngine(Set<GamePlayer> players, GameRules rules) {
        this.logger = new StepLogger();
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
        modifier.incrementTurnFor(player);
        logger.startStepLog(player, modifier.newGameResults());
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
        int numberRollCurrent = 0;
        List<GameAction> actions = new ArrayList<>();
        RollDicesResult result;
        do {
            result = rollDices(player, ++numberRollCurrent);
            if(debugMode)
                logger.stepLog(result, rules.getGameFlow());
            addActions(result, actions);
        } while(!isEndTurnForPlayer(result));
        executeActions(actions);
    }

    private RollDicesResult rollDices(GamePlayer player, int numberRollCurrent) {
        RollDices roll = RollDicesImpl.builder()
                .dices(rules.getDices())
                .gamePlayer(player)
                .numberRollCurrent(numberRollCurrent)
                .build();
        return roll.make();
    }

    private void executeActions(List<GameAction> actions) {
        ActionsExecutor executor = ActionsExecutorImpl.builder()
                .actions(actions)
                .modifier(modifier)
                .rules(rules)
                .build();
        executor.execute();
    }

    private boolean isEndTurnForPlayer(RollDicesResult result) {
        return rules.getGameFlow().isEndTurnForPlayer(result)
                || result.getNumberRollCurrent() >= rules.getNumberRolls();
    }

    private void addActions(RollDicesResult result, List<GameAction> actions) {
        GameFlow gameFlow = rules.getGameFlow();
        if(gameFlow.isLostTurn(result))
            gameFlow.doIfLostTurn(result, actions);
        if(gameFlow.isWonTurn(result))
            gameFlow.doIfWonTurn(result, actions);
        if(!gameFlow.isLostTurn(result) && !gameFlow.isWonTurn(result))
            gameFlow.doIfNotWonAndNotLostTurn(result, actions);
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
