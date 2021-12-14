package com.company;

public class Combat {

    UI ui = new UI();

    boolean exhausted = false;

    public void combat(Enemy enemy, Weapons weapons, Player player, Healing healing) {
        int range = (weapons.topDamage - weapons.lowDamage) + 1;
        System.out.println("You are fighting "+ enemy.amount + " " + enemy.name + " with " + enemy.health + " health");
        enemy.health = enemy.health * enemy.amount;
        enemy.damage = enemy.damage * enemy.amount;
        while (enemy.health > 0 && player.health > 0) {
            int rng = (int) Math.floor(Math.random() * range) + weapons.lowDamage;
            if(exhausted) {
                rng = rng - 1;
            }
            String msg = "Type 1 to attack 2 to use healing potion: ";
            String choice = ui.getUserInput(msg);
            if (choice.equalsIgnoreCase("1")) {
                enemy.health = enemy.health - rng;
                if (enemy.health <= 0) {
                    System.out.println("You did " + rng + " damage! The " + enemy.amount + " " + enemy.name + " is dead!");
                    break;
                }
                System.out.println("You did " + rng + " damage! " + enemy.name + " has " + enemy.health + " health left.");
                player.health = player.health - enemy.damage;
                if (player.health <= 0) {
                    System.out.println("You took " + enemy.damage + " damage! You have been defeated by " + enemy.amount + " " + enemy.name);
                    System.out.println("The game is over try again if you want.");
                    ui.endgame();
                }
                System.out.println("You took " + enemy.damage + " damage! You have " + player.health + " health left.");
            } else if (choice.equalsIgnoreCase("2")) {
                if(Healing.healingPotion.size() == 1) {
                    System.out.println("You are out of healing potions!");
                } else {
                    Healing.healingPotion.remove(Healing.healingPotion.size() - 1);
                    player.health = player.health + healing.value;
                    System.out.println("You healed for " + healing.value + "!");
                }
            } else {
                    System.out.println("Please type either 1 or 2");
            }
        }
    }
}
