package com.gmail.timurworkspace;

public class MapOverflowException extends Exception {
    @Override
    public String getMessage() {
        return "Map is full";
    }
}
