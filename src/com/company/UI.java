package com.company;

import java.util.Scanner;

public class UI {

    public String getUserInput(String msg){
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }

    public void menu() {
        int i = 0;
        while (i < 1) {
            String input = getUserInput("Type 1 to start game, 2 to quit: ");
            if (input.equalsIgnoreCase("1")) {
                startGame();
            } else if (input.equalsIgnoreCase("2")) {
                i = 1;
            } else {
                System.out.println("Please type either 1 or 2");
            }
        }
    }

    public void startGame() {
        String name = getUserInput("Type your characters name: ");
        Player.players.add(new Player(name));
        Level lvl = new Level();
        lvl.gameIntroduction(name);
    }

    public void endgame(){
        System.exit(1);
    }
}
