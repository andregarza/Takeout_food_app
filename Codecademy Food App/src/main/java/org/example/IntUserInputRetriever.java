package org.example;
// This interface will be implemented in TakeoutSimulator by using lamba implementations to implement a desired output

public interface IntUserInputRetriever<T> {

    T produceOutputOnIntUserInput(int selection) throws IllegalArgumentException;
}
