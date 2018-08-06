/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.log;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.exception.PlayerHasNotBeenAddedToGameException;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.PlayerResult;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.result.ResultsGame;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gmail.jarmusik.kamil.dicegame2.game.player.GamePlayer;
import com.gmail.jarmusik.kamil.dicegame2.game.rule.flow.GameFlow;

/**
 *
 * @author Kamil-Tomasz
 */
public class TurnLogger implements TurnLoggable {
    
    private final static Logger logger = Logger.getLogger(TurnLogger.class.getName());

    @Override
    public void startTurnLog(GamePlayer player, ResultsGame resultsGame) throws PlayerHasNotBeenAddedToGameException {
        PlayerResult result = resultsGame.getPlayerResultFor(player);
        logger.log(Level.INFO, "Turn number: {0}, for: {1}\nResult: {2}", new Object[]{result.getCurrentTurnNumber(), player, result});
    }
    
    @Override
    public void turnLog(int numberOfRollCurrent, int pointsRoll, GameFlow master) {
        logger.log(Level.INFO, "roll[{0}]: {1}, points: {2}", new Object[]{numberOfRollCurrent, pointsRoll, master.pointsScoredPerRoll(numberOfRollCurrent, pointsRoll)});
    }
    
    @Override
    public void endTurnLog(GamePlayer player, ResultsGame resultsGame) throws PlayerHasNotBeenAddedToGameException {
        PlayerResult resultPlayerCurrent = resultsGame.getPlayerResultFor(player);
        GamePlayer playerLeader = resultsGame.getLeader();
        logger.log(Level.INFO, "Result turn: {0}", resultPlayerCurrent);
        logger.log(Level.INFO, "Leader: {0}\n", playerLeader);
    }
    
}
