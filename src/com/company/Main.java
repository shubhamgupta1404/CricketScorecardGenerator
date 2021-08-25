package com.company;

import Controller.GameEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        GameEngine game1 = GameEngine.getInstance();
        game1.startGame();
    }
}
