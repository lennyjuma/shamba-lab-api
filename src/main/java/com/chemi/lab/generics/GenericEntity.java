package com.chemi.lab.generics;

public interface GenericEntity <T> {

    // update current instance with provided data
    void update(T source);

    String getId();

    // based on current data create new instance with new id
    T createNewInstance();
}
