package com.company;

import java.util.ArrayList;

public class Player {
    String name;
    int health = 30;
    int gold = 10;
    public static ArrayList<Player> players = new ArrayList<>();
    boolean noGoldLeft = false;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public boolean checkGold(int gold, int cost) {
        if(gold < cost ) {
            noGoldLeft = true;
            return noGoldLeft;
        }
        return noGoldLeft;
    }
}
