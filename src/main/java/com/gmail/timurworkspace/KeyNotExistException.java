package com.gmail.timurworkspace;

public class KeyNotExistException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Key not exist";
    }
}
