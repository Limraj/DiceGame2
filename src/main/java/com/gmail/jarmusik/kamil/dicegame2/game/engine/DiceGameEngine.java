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
import java.util.logging.Logger;
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
    
    private int totalNumberOfTurns;
    private final GameResultsModifier gameResultsModifier;
    private final GameRules rules;
    private final IterableShift<GamePlayer> playersRegisterShift;
    private final TurnLoggable logger;
    private boolean debugMode;

    public DiceGameEngine(Set<GamePlayer> players, GameRules rules) {
        System.out.print("Load engine...");
        this.gameResultsModifier = new GameResultsModifierImpl(players);
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
        GameResults results = gameResultsModifier.toGameResults(rules.getGameFlow().rulesOfWinning());
        GamePlayer player = playersRegisterShift.next();
        try {
            gameResultsModifier.addTurnFor(player);
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
        
        if(flowGame.isLostTurn(numberOfRollCurrent, pointsRoll))
            flowGame.doIfLostTurn(numberOfRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(gameResultsModifier, rules));
        
        if(flowGame.isWonTurn(numberOfRollCurrent, pointsRoll))
            flowGame.doIfWonTurn(numberOfRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(gameResultsModifier, rules));
        
        if(!flowGame.isLostTurn(numberOfRollCurrent, pointsRoll) 
                && !flowGame.isWonTurn(numberOfRollCurrent, pointsRoll))
            flowGame.doIfNotWonAndLostTurn(numberOfRollCurrent, pointsRoll, player)
                    .forEach(action -> action.execute(gameResultsModifier, rules));
        
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
    public GameResults getGameResults() {
        return gameResultsModifier.toGameResults(rules.getGameFlow().rulesOfWinning());
    }

    @Override
    public void debugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
