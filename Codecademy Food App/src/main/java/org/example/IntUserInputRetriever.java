package org.example;

public interface IntUserInputRetriever<T> {

    T produceOutputOnIntUserInput(int selection) throws IllegalArgumentException;
}
