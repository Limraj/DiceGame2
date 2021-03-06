/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public interface GameActionsScheduler {
    void complete();
    public static GameActionsSchedulerImpl.GameActionsSchedulerImplBuilder builder(List<GameAction> schedule) {
        return new GameActionsSchedulerImpl.GameActionsSchedulerImplBuilder().schedule(schedule);
    }
}
