package com.gmail.timurworkspace;

public class KeyNotExistException extends Exception{

    @Override
    public String getMessage() {
        return "Key not exist";
    }
}