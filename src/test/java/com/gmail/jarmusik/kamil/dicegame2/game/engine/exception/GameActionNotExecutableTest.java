/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.engine.exception;

import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.action.GameAction;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import com.gmail.jarmusik.kamil.dicegame2.game.engine.schedule.GameActionsScheduler;

/**
 *
 * @author Kamil-Tomasz
 */
public class GameActionNotExecutableTest {

    @Test(expected = GameActionNotExecutable.class)
    public void testSomeMethod() {
        //given:
        List<GameAction> schedule = new ArrayList<>();
        schedule.add(new GameAction() {});
        GameActionsScheduler scheduler = GameActionsScheduler.builder(schedule).build();
        //when:
        scheduler.complete();
    }
    
}
