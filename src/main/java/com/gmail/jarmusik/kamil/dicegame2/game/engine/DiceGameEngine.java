package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NoPlayersException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.StepLoggable;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.StepLogger;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResults;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.RollDices;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.roll.RollDicesResult;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.util.IterableShift;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.AccessFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.GameActionsScheduler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil-Tomasz
 */
class DiceGameEngine implements GameEngine {
    
    private final IterableShift<GamePlayer> playersRegisterShift;
    private final GameResultsModifier modifier;
    private final StepLoggable logger;
    private final AccessFlow accessFlow;
    private final GameRules rules;
    private int numberTurnCurrent;
    private boolean debugMode;

    DiceGameEngine(Set<GamePlayer> players, GameRules rules, AccessFlow accessFlow) {
        logger = new StepLogger();
        logger.loadingEngine();
        playersRegisterShift = new PlayersShiftRegister(players);
        this.accessFlow = accessFlow;
        this.rules = rules;
        modifier = GameResultsModifier.newModifier(players, accessFlow.getGameFlow().rulesOfWinning());
        numberTurnCurrent = 0;
        debugMode = false;
        logger.okEngine();
    }

    private GameEngine nextPlayer() {
        GamePlayer player = playersRegisterShift.next();
        modifier.incrementTurnFor(player);
        logger.startStepLog(player, modifier.snapshot());
        executeStepFor(player);
        logger.endStepLog(player, modifier.snapshot());
        return this;
    }
   
    @Override
    public GameEngine nextTurn() {
        hasPlayersOrThrownException();
        hasTurnAndIncrementNumberStepCurrentOrThrownException();
        int step = playersRegisterShift.size();
        while(step > 0) {
            nextPlayer();
            step--;
        }
        return this;
    }

    @Override
    public void reset() {
        numberTurnCurrent = 0;
        playersRegisterShift.reset();
        modifier.reset();
    }

    @Override
    public boolean hasTurn() {
        return numberTurnCurrent < rules.getNumberTurns();
    }

    @Override
    public GameResults getGameResults() {
        return modifier.snapshot();
    }

    @Override
    public void debugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
    
    private void hasTurnAndIncrementNumberStepCurrentOrThrownException() {
        if(!hasTurn())
            throw new NumberOfTurnsHasExceededException();
        numberTurnCurrent++;
    }
    
    private void hasPlayersOrThrownException() {
        if(playersRegisterShift.isEmpty())
            throw new NoPlayersException();
    }
    
    private void executeStepFor(GamePlayer player) {
        int numberRollCurrent = 0;
        List<GameAction> schedule = new ArrayList<>();
        RollDicesResult result;
        do {
            result = rollDices(player, ++numberRollCurrent);
            scheduleActions(result, schedule);
            if(debugMode)
                logger.stepLog(result, accessFlow.getGameFlow());
        } while(!isEndTurnForPlayer(result));
        completeSchedule(schedule);
    }

    private RollDicesResult rollDices(GamePlayer player, int numberRollCurrent) {
        RollDices roll = RollDices
                .builder(rules.getDices())
                .gamePlayer(player)
                .numberRollCurrent(numberRollCurrent)
                .build();
        return roll.make();
    }

    private void completeSchedule(List<GameAction> schedule) {
        GameActionsScheduler scheduler = GameActionsScheduler
                .builder(schedule)
                .modifier(modifier)
                .rules(rules)
                .build();
        scheduler.complete();
    }

    private boolean isEndTurnForPlayer(RollDicesResult result) {
        return accessFlow.getGameFlow().isEndTurnForPlayer(result)
                || result.getNumberRollCurrent() >= rules.getNumberRolls();
    }

    private void scheduleActions(RollDicesResult result, List<GameAction> schedule) {
        GameFlow gameFlow = accessFlow.getGameFlow();
        if(gameFlow.isLostTurn(result)) {
            gameFlow.scheduleIfLostTurn(result, schedule);
            return;
        }
        if(gameFlow.isWonTurn(result)) {
            gameFlow.scheduleIfWonTurn(result, schedule);
            return;
        }
        gameFlow.scheduleIfNotWonAndNotLostTurn(result, schedule);
    }
}
