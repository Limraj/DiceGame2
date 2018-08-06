package com.gmail.jarmusik.kamil.dicegame2.game.engine;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.NumberOfTurnsHasExceededException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.TurnLoggable;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.log.TurnLogger;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifier;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.GameResultsModifierImpl;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.ResultsGame;
import com.gmail.jarmusik.kamil.dicegame2.game.player.PlayersShiftRegister;
import com.gmail.jarmusik.kamil.dicegame2.util.IterableShift;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.GameRules;

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
    
    private int totalNumberOfTurns;
    private final GameResultsModifier gameResultsModifier;
    private final GameRules rules;
    private final IterableShift<GamePlayer> playersRegisterShift;
    private final TurnLoggable logger;
    private boolean debugMode;

    public DiceGameEngine(Set<GamePlayer> players, GameRules rules) {
        System.out.print("Load engine...");
        this.gameResultsModifier = new GameResultsModifierImpl(players, rules.getGameFlow().rulesOfWinning());
        this.rules = rules;
        this.totalNumberOfTurns = 0;
        this.playersRegisterShift = new PlayersShiftRegister(players);
        this.logger = new TurnLogger();
        this.debugMode = false;
        System.out.println(" ok");
    }

    @Override
    public GameEngine nextTurn() throws NumberOfTurnsHasExceededException {
        checkHasNextTurnAndIterate();
        ResultsGame results = gameResultsModifier.toGameResults();
        GamePlayer player = playersRegisterShift.next();
        try {
            logger.startTurnLog(player, results);
            executeFor(player);
            logger.endTurnLog(player, results);
        } catch (PlayerHasNotBeenAddedToGameException ex) {
            Logger.getLogger(DiceGameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
    private void checkHasNextTurnAndIterate() throws NumberOfTurnsHasExceededException {
        if(!hasNextTurn())
            throw new NumberOfTurnsHasExceededException();
        totalNumberOfTurns++;
    }
    
    private void executeFor(GamePlayer player) throws PlayerHasNotBeenAddedToGameException {
        gameResultsModifier.addTurnFor(player);
        
        GameFlow flowGame = rules.getGameFlow();
        int numberOfRollCurrent = 0, pointsRoll;
        
        do {
            
            numberOfRollCurrent++;
            pointsRoll = rules.rollOfDices();
            if(debugMode)
                logger.turnLog(numberOfRollCurrent, pointsRoll, flowGame);
            modifiedResults(flowGame, numberOfRollCurrent, pointsRoll, player);
            
        } while(!isEndTurn(flowGame, numberOfRollCurrent, pointsRoll));
    }

    private boolean isEndTurn(GameFlow flowGame, int numberOfRollCurrent, int pointsRoll) {
        return flowGame.isEndTurn(numberOfRollCurrent, pointsRoll)
                || numberOfRollCurrent > rules.getNumberOfRolls();
    }

    private void modifiedResults(GameFlow flowGame, int numberOfRollCurrent, int pointsRoll, GamePlayer player) {
        
        flowGame.doIfLostTurn(numberOfRollCurrent, pointsRoll, player)
                .forEach(action -> action.execute(gameResultsModifier.toGameResults(), rules));
        
        flowGame.doIfWonTurn(numberOfRollCurrent, pointsRoll, player)
                .forEach(action -> action.execute(gameResultsModifier.toGameResults(), rules));
        
        flowGame.doIfNotWonAndLostTurn(numberOfRollCurrent, pointsRoll, player)
                .forEach(action -> action.execute(gameResultsModifier.toGameResults(), rules));
        
    }

    @Override
    public void reset() {
        totalNumberOfTurns = 0;
        playersRegisterShift.reset();
        gameResultsModifier.reset();
    }

    @Override
    public int getTotalNumberOfTurn() {
        return totalNumberOfTurns;
    }

    @Override
    public boolean hasNextTurn() {
        return totalNumberOfTurns < rules.getNumberOfTurns() * playersRegisterShift.size();
    }

    @Override
    public ResultsGame getGameResults() {
        return gameResultsModifier.toGameResults();
    }

    @Override
    public void debugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
