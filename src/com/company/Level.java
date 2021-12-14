package com.company;

public class Level {

    UI ui = new UI();
    Combat combat = new Combat();
    Player player = new Player();

    boolean oldManDialogCompleted = false;
    boolean exploredCave = false;
    boolean sleep = false;
    boolean swordBought = false;
    boolean daggerBought= false;
    boolean triedToRun = false;
    boolean helpedStranger = false;
    boolean gaveWeapon = false;
    boolean blacksmithWeaponBought = false;
    boolean blacksmithDialogDone = false;
    boolean alchemistDialogDone = false;
    boolean hoodedManHelping = false;

    public void gameIntroduction(String name) {
        System.out.println("Greetings " + name + ". In this game you will explore the beautiful lands of Ostravor. " +
                "You will be met with many great challenges and choices that will decide if you are strong enough to survive these lands. " + System.lineSeparator() +
                "If you should succeed i am sure there will be great treasures waiting ahead for you. Good luck out there " + name + ".");
        String choice = ui.getUserInput("Type 1 to proceed: ");
        if (choice.equalsIgnoreCase("1")) {
            Weapons fists = new Weapons("Fists",5,1,0);
            Weapons.weapons.add(fists);
            Healing hpPot = new Healing(0, 0);
            Healing.healingPotion.add(hpPot);
            firstLevel();
        } else {
            System.out.println("Please type 1");
        }

    }

    public void firstLevel() {
        System.out.println("You find yourself standing in the middle of the road on a beautiful sunny day. " +
                "Ahead of you theres an old man standing on the side of the road hunched over. " +
                "To your right you see a dark mysterious cave.");
        String msg = "Type 1 to talk to the old man " + System.lineSeparator() + ((exploredCave) ? "" : "Type 2 to explore the cave " + System.lineSeparator()) + "Type 3 to keep walking down the road: ";
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1")) {
            oldManDialog();
        } else if (choice.equalsIgnoreCase("2") && !exploredCave) {
            caveDialog();
        } else if (choice.equalsIgnoreCase("3")) {
            secondLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
            firstLevel();
        }
    }

    public void oldManDialog() {
        System.out.println("Greetings adventurer! Ostravor is a dangerous land and it is always good to be prepared for the worst. " +
                "That cave over there has some dangerous enemies but if you want i can sell you some items you can use to protect yourself.");
        Weapons sword = new Weapons("Sword",5,3,2);
        Weapons dagger = new Weapons("Dagger",3,2,1);
        System.out.println(((swordBought) ? "" : "Item 1: "+ sword + System.lineSeparator()) + ((daggerBought) ? "" : "Item 2: " + dagger));
        oldManDialogCompleted = true;
        String choice = ItemsBoughtCheck();
        if (choice.equalsIgnoreCase("1") && !swordBought) {
            Weapons.weapons.add(sword);
            player.setGold(player.getGold() - 5);
            System.out.println("You have purchased a sword. You have " + player.getGold() + " gold left.");
            swordBought = true;
            firstLevel();
        } else if (choice.equalsIgnoreCase("2") && !daggerBought) {
            Weapons.weapons.add(dagger);
            player.setGold(player.getGold() - 3);
            System.out.println("You have purchased a dagger. You have " + player.getGold() + " gold left.");
            daggerBought = true;
            firstLevel();
        } else if (choice.equalsIgnoreCase("3")) {
            firstLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
            oldManDialog();
        }
    }

    public String ItemsBoughtCheck() {
        String msgNoItemsBought = "Type 1 to buy the sword 2 to buy the dagger or 3 to leave: ";
        String msgSwordBought = "Type 2 to buy the dagger or 3 to leave: ";
        String msgDaggerBought = "Type 1 to buy the sword or 3 to leave: ";
        String msgBothItemsBought = "There are no items left type 3 to leave: ";
        if(swordBought && !daggerBought) {
            String choice = ui.getUserInput(msgSwordBought);
            return choice;
        } else if (daggerBought && !swordBought) {
            String choice = ui.getUserInput(msgDaggerBought);
            return choice;
        } else if (swordBought && daggerBought) {
            String choice = ui.getUserInput(msgBothItemsBought);
            return choice;
        } else {
            String choice = ui.getUserInput(msgNoItemsBought);
            return choice;
        }
    }

    public void caveDialog() {
        System.out.println("You walk into the dark cold cave. You can barely see anything except for a golden glow further into the cave. ");
        if(oldManDialogCompleted){
            System.out.println("You remember that the old man told you about the dangers in the cave and prepare yourself. ");
        }
        System.out.println("Suddenly a giant spider appears infront of you. You draw your weapon and get ready to fight.");
        Enemy giantSpider = new Enemy("Giant spider", 2,10,"Spider",1);
        Enemy.enemy.add(giantSpider);
        int i = 0;
        for(Weapons item: Weapons.weapons){
            System.out.println("Weapon " + i + ": " + item);
            i++;
        }
        String choice = ui.getUserInput("Type the number of the weapon to use: ");
        int intChoice = Integer.parseInt(choice);
        System.out.println("You choose: " + Weapons.weapons.get(intChoice));
        combat.combat(Enemy.enemy.get(Enemy.enemy.indexOf(giantSpider)), Weapons.weapons.get(intChoice), Player.players.get(0), Healing.healingPotion.get(Healing.healingPotion.size()-1));
        System.out.println("Behind the spider you find 10 gold and a healing potion!");
        player.setGold(player.getGold() + 10);
        Healing hpPot = new Healing(10, 0);
        Healing.healingPotion.add(hpPot);
        exploredCave = true;
        firstLevel();
    }

    public void secondLevel() {
        System.out.println("You're starting to feel tired and you wanna go lay down somewhere and take " +
                "a nap. On your right you see an open field with some soft looking grass and " +
                "on your left you see a formation of rocks that might give some cover if it starts to rain.");
        String msg = ((sleep) ? "Type 3 to keep going: " : "Type 1 to sleep in the grass. " + System.lineSeparator() + "Type 2 to sleep in the rock formation. " + System.lineSeparator() + "Type 3 to keep going even though you're tired: ");
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1") && !sleep) {
            grassDialog();
        } else if (choice.equalsIgnoreCase("2") && !sleep) {
            System.out.println("You sleep safely in the rock formation. You wake up refreshed and ready to keep going.");
            sleep = true;
            thirdLevel();
        } else if (choice.equalsIgnoreCase("3")) {
            combat.exhausted = true;
            System.out.println("You're exhausted and you might not do as much damage the next time you fight.");
            thirdLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
            secondLevel();
        }
    }

    public void grassDialog() {
        System.out.println("You walk over to the grass and lie down to sleep for a couple of hours. " +
                "All of a sudden you wake up and find yourself surrounded by three goblins.");
        sleep = true;
        String msg = "Type 1 to fight. " + System.lineSeparator() + ((triedToRun) ? "" :  "Type 2 to try to run. " + System.lineSeparator()) + "Type 3 to pay them 10 gold so you can leave: ";
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1")) {
            Enemy goblin = new Enemy("Green goblin",1,5,"Goblin",3);
            Enemy.enemy.add(goblin);
            int i = 0;
            for(Weapons item: Weapons.weapons){
                System.out.println("Weapon " + i + ": " + item);
                i++;
            }
            String weaponChoice = ui.getUserInput("Type the number of the weapon to use: ");
            int intChoice = Integer.parseInt(weaponChoice);
            System.out.println("You choose: " + Weapons.weapons.get(intChoice));
            combat.combat(Enemy.enemy.get(Enemy.enemy.indexOf(goblin)), Weapons.weapons.get(intChoice), Player.players.get(0), Healing.healingPotion.get(Healing.healingPotion.size()-1));
            System.out.println("After defeating the goblins you find 5 gold, a healing potion and a dagger!");
            player.setGold(player.getGold() + 5);
            Healing hpPot = new Healing(10, 0);
            Healing.healingPotion.add(hpPot);
            Weapons goblinDagger = new Weapons("Dagger",3,2,2);
            Weapons.weapons.add(goblinDagger);
            thirdLevel();
        } else if (choice.equalsIgnoreCase("2")) {
            tryToRun();
        } else if (choice.equalsIgnoreCase("3")) {
            System.out.println("You pay the goblins 10 gold");
            player.setGold(player.getGold() - 10);
            thirdLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
            thirdLevel();
        }
    }

    public void tryToRun() {
        int rng = (int) Math.floor(Math.random() * (4 - 1)) + 1;
        if(rng == 1) {
            System.out.println("You have successfully escaped!");
            secondLevel();
        } else if(rng == 2 || rng == 3) {
            System.out.println("You did not manage to escape! You must now fight or pay the goblins.");
            triedToRun = true;
            grassDialog();
        } else if(rng == 4) {
            System.out.println("The goblins killed you as you tried to escape. Better luck next time.");
            ui.menu();
        }
    }

    public void thirdLevel() {
        System.out.println("You keep walking down the road and suddenly a dark shady character appears " +
                "on the road ahead of you. Hes wearing a hooded cape and you cannot make out " + System.lineSeparator() +
                "his face. He asks if you can help him with a problem and points towards the woods.");
        String msg = "Type 1 to help the man with his problem " + System.lineSeparator() + "Type 2 to decline to help: ";
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1")) {
            hoodedManDialog();
        } else if (choice.equalsIgnoreCase("2")) {
            System.out.println("You keep walking down the road with helping the strange man.");
            fourthLevel();
        } else {
            System.out.println("Pleaser type either 1 or 2");
            thirdLevel();
        }
    }

    public void hoodedManDialog() {
        System.out.println("You follow him into the woods. Its a long walk and you try asking " +
                "how much longer but get no response. You start to see smoke out in " +
                "the distance and as you get closer you notice its from a fire in the middle of a goblin " + System.lineSeparator() +
                "camp. The hooded man explains that goblins attacked him while he was asleep " +
                "and he had to give up his belongings and weapons in order to escape.");
        String msg = "Type 1 to fight the goblins " + System.lineSeparator() + ((gaveWeapon) ? "" : "Type 2 to give extra weapon to the hooded man to help fight " + System.lineSeparator()) + "Type 3 to decline to help: ";
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1")) {
            int amountOfGoblins = 5;
            if (gaveWeapon) {
                amountOfGoblins = 3;
            }
            Enemy goblin = new Enemy("Green goblin",1,5,"Goblin",amountOfGoblins);
            Enemy.enemy.add(goblin);
            int i = 0;
            for(Weapons item: Weapons.weapons){
                System.out.println("Weapon " + i + ": " + item);
                i++;
            }
            String weaponChoice = ui.getUserInput("Type the number of the weapon to use: ");
            int intChoice = Integer.parseInt(weaponChoice);
            System.out.println("You choose: " + Weapons.weapons.get(intChoice));
            combat.combat(Enemy.enemy.get(Enemy.enemy.indexOf(goblin)), Weapons.weapons.get(intChoice), Player.players.get(0), Healing.healingPotion.get(Healing.healingPotion.size()-1));
            System.out.println("After defeating the goblins you find 10 gold and two healing potions!");
            player.setGold(player.getGold() + 10);
            Healing hpPot = new Healing(10, 0);
            Healing.healingPotion.add(hpPot);
            Healing.healingPotion.add(hpPot);
            System.out.println("After helping the hooded man you proceed down the road.");
            helpedStranger = true;
            fourthLevel();
        } else if (choice.equalsIgnoreCase("2") && Weapons.weapons.size() > 1) {
            for(int i = 1; i < Weapons.weapons.size(); i++){
                System.out.println("Weapon " + i + ": " + Weapons.weapons.get(i));
            }
            String weaponChoice = ui.getUserInput("Type the number of the weapon to give: ");
            int intChoice = Integer.parseInt(weaponChoice);
            System.out.println("You choose: " + Weapons.weapons.get(intChoice));
            Weapons.weapons.remove(intChoice);
            gaveWeapon = true;
            System.out.println("The hooded man helps you fight and takes out two goblins.");
            hoodedManDialog();
        } else if(choice.equalsIgnoreCase("3")) {
            fourthLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
            hoodedManDialog();
        }
    }

    public void fourthLevel() {
        System.out.println("You arrive at a small town. As you are walking through the town you come upon a blacksmith and an alchemist.");
        String msg = ((blacksmithDialogDone) ? "" : "Type 1 to talk to the blacksmith" + System.lineSeparator()) + ((alchemistDialogDone) ? "" : "Type 2 to talk to the alchemist" + System.lineSeparator()) + "Type 3 to leave the small town: ";
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1") && !blacksmithDialogDone) {
            System.out.println("You walk over to the blacksmith.");
            blacksmithDialog();
        } else if (choice.equalsIgnoreCase("2") && !alchemistDialogDone) {
            System.out.println("You walk over to the alchemist.");
            alchemistDialog();
        } else if (choice.equalsIgnoreCase("3")) {
            System.out.println("You leave the small town and proceed in you adventure.");
            fifthLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
        }
    }

    public void blacksmithDialog() {
        System.out.println("Welcome to the blacksmith!");
        String msg = ((blacksmithWeaponBought) ? "" : "Type 1 to buy the axe for 15 gold" + System.lineSeparator()) + "Type 2 to accept quest for you to obtain iridium ore which can be used to craft powerful weapons" + System.lineSeparator() + "Type 3 to leave: ";
        Weapons axe = new Weapons("Axe",15,5,3);
        if ((!blacksmithWeaponBought)) {
            System.out.println("Item 1: " + axe);
        }
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1") && !blacksmithWeaponBought) {
            Weapons.weapons.add(axe);
            player.setGold(player.getGold() - 15);
            System.out.println("You have purchased an axe. You have " + player.getGold() + " gold left.");
            blacksmithWeaponBought = true;
            fourthLevel();
        } else if (choice.equalsIgnoreCase("2")) {
            System.out.println("The iridium ore is found inside an ogre cave. But be careful the ogre king is known to be powerful and dangerous enemy!");
            iridiumOreQuest();
        } else if (choice.equalsIgnoreCase("3")) {
            fourthLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
        }
    }

    public void alchemistDialog() {
        System.out.println("Welcome to the alchemist!");
        String msg = "Type 1 to purchase a healing potion" + System.lineSeparator() + "Type 2 accept quest for you to obtain the blood of a basilisk" + System.lineSeparator() + "Type 3 to leave: ";
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1")) {
            Healing hpPot = new Healing(10, 5);
            Healing.healingPotion.add(hpPot);
            if (!player.checkGold(player.getGold(), 5)) {
                player.setGold(player.getGold() - 5);
                System.out.println("You have purchased one healing potion! You have " + player.getGold() + " gold left!");
            } else {
                System.out.println("You don't have enough gold!");
            }
            fourthLevel();
        } else if (choice.equalsIgnoreCase("2")) {
            System.out.println("The alchemist hands you a vial for the blood and tells that basilisks can be found just outside of town.");
            basiliskBloodQuest();
        } else if (choice.equalsIgnoreCase("3")) {
            fourthLevel();
        } else {
            System.out.println("Please type either 1, 2 or 3");
        }
    }

    public void iridiumOreQuest() {
        System.out.println("The iridium is found inside an ogre cave just outside of town. You must fight two ogres and the ogre king");
        Enemy ogre = new Enemy("Ogre",1,10,"Ogre",2);
        Enemy.enemy.add(ogre);
        int i = 0;
        for(Weapons item: Weapons.weapons){
            System.out.println("Weapon " + i + ": " + item);
            i++;
        }
        String weaponChoice = ui.getUserInput("Type the number of the weapon to use: ");
        int intChoice = Integer.parseInt(weaponChoice);
        System.out.println("You choose: " + Weapons.weapons.get(intChoice));
        combat.combat(Enemy.enemy.get(Enemy.enemy.indexOf(ogre)), Weapons.weapons.get(intChoice), Player.players.get(0), Healing.healingPotion.get(Healing.healingPotion.size()-1));
        System.out.println("And now you must fight the ogre king!");
        Enemy ogreKing = new Enemy("Ogre King",4,20,"Ogre",1);
        Enemy.enemy.add(ogreKing);
        combat.combat(Enemy.enemy.get(Enemy.enemy.indexOf(ogreKing)), Weapons.weapons.get(intChoice), Player.players.get(0), Healing.healingPotion.get(Healing.healingPotion.size()-1));
        System.out.println("After defeating the ogre king you receive one healing potion and the iridium ore. You bring it back to the blacksmith and he crafts a new weapon!");
        Weapons iridiumSword = new Weapons("Sword",0,8,6);
        System.out.println("New weapon: "+ iridiumSword);
        Weapons.weapons.add(iridiumSword);
        Healing hpPot = new Healing(10, 0);
        Healing.healingPotion.add(hpPot);
        blacksmithDialogDone = true;
        fourthLevel();
    }

    public void basiliskBloodQuest() {
        System.out.println("You travel to the woods just outside of town and find a basilisk.");
        Enemy basilisk = new Enemy("Basilisk",3,15,"Basilisk",1);
        Enemy.enemy.add(basilisk);
        int i = 0;
        for(Weapons item: Weapons.weapons){
            System.out.println("Weapon " + i + ": " + item);
            i++;
        }
        String weaponChoice = ui.getUserInput("Type the number of the weapon to use: ");
        int intChoice = Integer.parseInt(weaponChoice);
        System.out.println("You choose: " + Weapons.weapons.get(intChoice));
        combat.combat(Enemy.enemy.get(Enemy.enemy.indexOf(basilisk)), Weapons.weapons.get(intChoice), Player.players.get(0), Healing.healingPotion.get(Healing.healingPotion.size()-1));
        System.out.println("After defeating the basilisk you fill the vial with it's blood. You bring it back to alchemist and he crafts you a powerful potion that heals you for 30 health!");
        Healing hpPot = new Healing(30, 0);
        Healing.healingPotion.add(hpPot);
        alchemistDialogDone = true;
        fourthLevel();
    }

    public void fifthLevel() {
        System.out.println("After leaving town you run in the hooded man again. He has another quest for you. " +
                "Up in the mountains is a dragons lair. This dragon is guarding a giant treasure and " +
                "defeating the dragon would make you very wealthy. ");
        String msg = "Type 1 to fight the dragon. " + System.lineSeparator() + ((helpedStranger) ? "Type 2 ask the hooded man to help you: " : "");
        String choice = ui.getUserInput(msg);
        if (choice.equalsIgnoreCase("1")) {
            int dragonHealth = 100;
            if (hoodedManHelping) {
                dragonHealth = 60;
            }
            Enemy dragon = new Enemy("Dragon",5,dragonHealth,"Dragon",1);
            Enemy.enemy.add(dragon);
            int i = 0;
            for(Weapons item: Weapons.weapons){
                System.out.println("Weapon " + i + ": " + item);
                i++;
            }
            String weaponChoice = ui.getUserInput("Type the number of the weapon to use: ");
            int intChoice = Integer.parseInt(weaponChoice);
            System.out.println("You choose: " + Weapons.weapons.get(intChoice));
            combat.combat(Enemy.enemy.get(Enemy.enemy.indexOf(dragon)), Weapons.weapons.get(intChoice), Player.players.get(0), Healing.healingPotion.get(Healing.healingPotion.size()-1));
            System.out.println("You have defeated the dragon and you are now the richest person in the entire kingdom! The game is over you won!");
            ui.endgame();
        } else if (choice.equalsIgnoreCase("2") && helpedStranger) {
            helpedStranger = false;
            hoodedManHelping = true;
            System.out.println("The hooded man helps you fight the dragon and does 40 damage!");
            fifthLevel();
        } else {
            System.out.println("Please type either 1 or 2");
        }
    }
}