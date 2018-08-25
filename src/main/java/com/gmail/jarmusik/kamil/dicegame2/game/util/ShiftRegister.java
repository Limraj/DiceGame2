/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.game.util;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Kamil-Tomasz
 * @param <T>
 */
public class ShiftRegister<T> implements IterableShift<T> {
    
    private final Collection<T> objects;
    private Iterator<T> objectsIterator;
    
    public ShiftRegister(Collection<T> objects) {
        this.objects = objects;
        this.objectsIterator = objects.iterator();
    }

    @Override
    public final T next() {
        if(!objectsIterator.hasNext())
            objectsIterator = objects.iterator();
        return objectsIterator.next();
    }

    @Override
    public final int size() {
        return objects.size();
    }

    @Override
    public final void reset() {
        objectsIterator = objects.iterator();
    }
}
