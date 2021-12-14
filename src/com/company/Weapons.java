package com.company;

import java.util.ArrayList;

public class Weapons {

    String type;
    int cost;
    int topDamage;
    int lowDamage;
    public static ArrayList<Weapons> weapons = new ArrayList<>();

    public Weapons(String type, int cost, int topDamage, int lowDamage) {
        this.type = type;
        this.cost = cost;
        this.topDamage = topDamage;
        this.lowDamage = lowDamage;
    }

    @Override
    public String toString() {
        return "type: " + type +
                ", cost: " + cost +
                ", damage range: " + lowDamage +"-"+ topDamage;
    }
}
