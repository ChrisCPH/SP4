package com.company;

import java.util.ArrayList;

public class Healing {

    int value;
    int cost;
    public static ArrayList<Healing> healingPotion = new ArrayList<>();

    public Healing(int value, int cost) {
        this.value = value;
        this.cost = cost;
    }
}
