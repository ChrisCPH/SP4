package com.company;

import java.util.ArrayList;

public class Enemy {

    String name;
    int damage;
    int health;
    String type;
    int amount;
    public static ArrayList<Enemy> enemy = new ArrayList<>();

    public Enemy(String name, int damage, int health, String type, int amount) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.type = type;
        this.amount = amount;
    }
}
