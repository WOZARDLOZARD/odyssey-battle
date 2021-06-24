package OdysseyBattle;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Manager manager = new Manager();
        Shop shop = new Shop();
        Scanner scanner;
        String input, enemyState, abilityState;
        String[] abilityIds = new String[]{"Q", "W", "E"};
        Player player;
        Enemy enemy;
        Kayn kayn;
        int random, i, previous = 0;

        System.out.println(new Text("<red>ODYSSEY BATTLE <white>| <cyan>WozardLozard\n\n<white>Our story begins in the year 2080, in the galaxy known simply as \"Odyssey\". You find yourself on a strange alien planet, your ship having crashed down a few hours earlier and stuck on the other side of the planet. In the distance, you hear foreign and frightening sounds of unknown alien creatures. You know that you must somehow get to your ship to repair it and go home... But how?\n\n<yellow>You realize that you are wearing a backpack. <white>Press any key to open it."));

        scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println(new Text("<cyan>You find three capsules in the backpack, each of a different shape and emitting a different glowing hue:\n\n<yellow>1. A golden capsule with waves of wind dancing around it.\n<green>2. A green capsule with what looks like... metal teeth?\n<red>3. A red capsule that looks... rock solid?\n\n<white>Which capsule do you open? Type the number corresponding to the capsule below!"));

        scanner = new Scanner(System.in);
        input = scanner.nextLine();

        while (!isInteger(input) || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 3) {
            error();

            scanner = new Scanner(System.in);
            input = scanner.nextLine();
        }

        switch (input) {
            case "1":
                player = new Player(2200, 345, 20, 50, 20, 10, 80, 40, 100, 1, "<yellow>Yasuo<white>");
                System.out.println(new Text("You open the golden capsule. A strong wind erupts from it, knocking you back. When you get up again, you see a stranger with a sword and mustache.\n\n<yellow>\"Who are you?\" You ask.\n\"Why, how dare you not know my name? I am Yasuo, the greatest master of sword and wind there ever will be.\" Says the stranger.\n\n<!> You have chosen Yasuo as your Champion!"));
                break;
            case "2":
                player = new Player(2050, 325, 0, 40, 15, 20, 125, 60, 100, 2, "<green>Jinx<white>");
                System.out.println(new Text("You open the green capsule. A cloud of noxious-smelling green gas wafts out, causing you to turn away and shut your eyes. When you look back at where the capsule had been, you see a stranger with a... rocket launcher?!\n\n<green>\"Who are you?\" You ask.\n\"Why, how dare you not know of my record of blowing stuff up? I am Jinx.\" Says the stranger.\n\n<!> You have chosen Jinx as your Champion!"));
                break;
            case "3":
                player = new Player(2500, 335, 0, 60, 50, 15, 45, 45, 100, 3, "<red>Malphite<white>");
                System.out.println(new Text("You open the red capsule. A spire of rock shoots out, and you leap out of the way. When you look back at where the capsule had been, you see a... moving rock?!\n\n<red> \"Who are you? What are you?\" You ask.\nThe rock turns slowly to face you. You realize it has a face. \"I... Malphite!\" The rock cries and claps its fists (yes, fists) together menacingly.\n\n<!> You have chosen Malphite as your Champion!"));
                break;
            default:
                player = new Player(2200, 345, 20, 50, 20, 10, 80, 40, 100, 1, "<yellow>Yasuo<white>");
                System.out.println(new Text("You open the golden capsule. A strong wind erupts from it, knocking you back. When you get up again, you see a stranger with a sword and mustache.\n\n<yellow>\"Who are you?\" You ask.\n\"Why, how dare you not know my name? I am Yasuo, the greatest master of sword and wind there ever will be.\" Says the stranger.\n\n<!> You have chosen Yasuo as your Champion!"));
                break;
        }

        System.out.println(new Text("<white>With a powerful Champion on your side, what could possibly go wrong now? You are suddenly very confident that you might be able to make it off this planet alive.\n\n<!> Fight through a series of monsters, as well as The Final Villain, with your Champion. Battles are turn-based, and the order of combat is random. However, fret not; your Champion has powerful Attacks, Abilities, and can even be buffed with Crests and Items."));

        abilityState = "";

        for (i = 0; i < player.getAbilities().length; i++) {
            if (player.getAbilities()[i].getType() == 1) {
                if (!player.getAbilities()[i].isAoe()) {
                    if (!player.getAbilities()[i].isImmobilizing()) {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    } else {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    }
                } else {
                    if (!player.getAbilities()[i].isImmobilizing()) {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    } else {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    }
                }
            } else if (player.getAbilities()[i].getType() == 2) {
                if (!player.getAbilities()[i].isAoe()) {
                    if (!player.getAbilities()[i].isImmobilizing()) {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    } else {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    }
                } else {
                    if (!player.getAbilities()[i].isImmobilizing()) {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    } else {
                        abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown";
                    }
                }
            }

            if (i < player.getAbilities().length - 1) abilityState += "\n";
        }
        System.out.println(new Text("YOUR CHAMPION (" + player.getName() + ") STATS\n\nResources: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana<white>, <yellow>" + player.getGold() + " Gold<white>\nDamage: <red>" + player.getAttackDamage() + " Attack Damage<white>, <blue>" + player.getAbilityPower() + " OdysseyBattle.Ability Power<white>\nResistances: <yellow>" + player.getArmor() + " Armor<white>, <cyan>" + player.getMagicResist() + " Magic Resist<white>\nMiscellaneous: <purple>" + player.getPenetration() + "% Penetration<white>, <red>" + player.getCriticalChance() + "% Critical Strike Chance<white>, " + player.getSpeed() + " Move Speed\n\nAbilities\n" + abilityState));

        System.out.println(new Text("\n<white>And that's it! See if you can make it alive! Remember, you can always type <cyan>\"instructions\"<white> anytime during the battle for reminders on how the game works and <cyan>\"exit\"<white> to exit the game.\nLast but not least: <yellow>Good luck!\n\n<white>Press any key to begin the battle."));

        scanner = new Scanner(System.in);
        scanner.nextLine();

        while (manager.getWave() >= 0 && manager.getWave() < 14) {
            random = pickEnemy(manager);

            while (random == 3 && player.getBuffs()[0] != null && player.getBuffs()[0].getType() == 1 || random == 3 && player.getBuffs()[1] != null && player.getBuffs()[1].getType() == 1 || random == 4 && player.getBuffs()[0] != null && player.getBuffs()[0].getType() == 2 || random == 4 && player.getBuffs()[1] != null && player.getBuffs()[1].getType() == 2 || random == previous) {
                random = pickEnemy(manager);
            }

            switch (random) {
                case 1:
                    if (manager.getWave() < 4) {
                        enemy = new Enemy(new int[]{randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(300 + manager.getWave() * 8, 320 + manager.getWave() * 8)}, 0, 0, 0, 5, 25, 0, 1, 6, 1, "<red>Raptors<white>", 28);
                    } else {
                        enemy = new Enemy(new int[]{randomNumber(300 + manager.getWave() * 8, 350 + manager.getWave() * 8), randomNumber(300 + manager.getWave() * 8, 350 + manager.getWave() * 8), randomNumber(300 + manager.getWave() * 8, 350 + manager.getWave() * 8), randomNumber(300 + manager.getWave() * 8, 350 + manager.getWave() * 8), randomNumber(300 + manager.getWave() * 8, 350 + manager.getWave() * 8), randomNumber(350 + manager.getWave() * 8, 400 + manager.getWave() * 8)}, 0, 5, 5, 8, 30, 0, 1, 6, 1, "<red>Raptors<white>", 32);
                    }
                    break;
                case 2:
                    if (manager.getWave() < 4) {
                        enemy = new Enemy(new int[]{randomNumber(450 + manager.getWave() * 8, 520 + manager.getWave() * 8), randomNumber(450 + manager.getWave() * 8, 520 + manager.getWave() * 8), randomNumber(520 + manager.getWave() * 8, 580 + manager.getWave() * 8)}, 0, 10, 10, 0, 0, 30, 2, 3, 2, "<yellow>Murkwolves<white>", 58);
                    } else {
                        enemy = new Enemy(new int[]{randomNumber(500 + manager.getWave() * 8, 550 + manager.getWave() * 8), randomNumber(500 + manager.getWave() * 8, 550 + manager.getWave() * 8), randomNumber(550 + manager.getWave() * 8, 600 + manager.getWave() * 8)}, 0, 15, 15, 5, 0, 35, 2, 3, 2, "<yellow>Murkwolves<white>", 65);
                    }
                    break;
                case 3:
                    if (manager.getWave() < 6) {
                        enemy = new Enemy(new int[]{randomNumber(1200 + manager.getWave() * 8, 1500 + manager.getWave() * 8)}, 0, 30, 20, 10, 85, 0, 1, 1, 3, "<red>Red Brambleback<white>", 170);
                    } else {
                        enemy = new Enemy(new int[]{randomNumber(1400 + manager.getWave() * 8, 1700 + manager.getWave() * 8)}, 0, 35, 25, 15, 90, 0, 1, 1, 3, "<red>Red Brambleback<white>", 175);
                    }
                    break;
                case 4:
                    if (manager.getWave() < 6) {
                        enemy = new Enemy(new int[]{randomNumber(1200 + manager.getWave() * 8, 1500 + manager.getWave() * 8)}, 0, 20, 30, 10, 0, 85, 2, 1, 4, "<blue>Blue Sentinel<white>", 170);
                    } else {
                        enemy = new Enemy(new int[]{randomNumber(1400 + manager.getWave() * 8, 1700 + manager.getWave() * 8)}, 0, 25, 35, 15, 0, 90, 2, 1, 4, "<blue>Blue Sentinel<white>", 175);
                    }
                    break;
                case 5:
                    enemy = new Enemy(new int[]{randomNumber(1500 + manager.getWave() * 8, 1800 + manager.getWave() * 8)}, 0, 10, 20, 15, 0, 100, 2, 1, 5, "<red>Infernal Drake<white>", 180);
                    break;
                case 6:
                    enemy = new Enemy(new int[]{randomNumber(1500 + manager.getWave() * 8, 1800 + manager.getWave() * 8)}, 0, 50, 50, 10, 60, 0, 1, 1, 6, "<yellow>Mountain Drake<white>", 180);
                    break;
                case 7:
                    enemy = new Enemy(new int[]{randomNumber(1500 + manager.getWave() * 8, 1800 + manager.getWave() * 8)}, 0, 25, 25, 10, 80, 0, 1, 1, 7, "<green>Ocean Drake<white>", 180);
                    break;
                case 8:
                    enemy = new Enemy(new int[]{randomNumber(1700 + manager.getWave() * 8, 1900 + manager.getWave() * 8)}, 0, 40, 40, 0,90, 0, 1, 1, 8, "<purple>Rift Herald<white>", 0);
                    break;
                case 9:
                    enemy = new Enemy(new int[]{randomNumber(1700 + manager.getWave() * 8, 1900 + manager.getWave() * 8)}, 0, 30, 30, 15, 100, 0, 1, 1, 9, "<cyan>Elder Dragon<white>", 0);
                    break;
                case 10:
                    enemy = new Enemy(new int[]{randomNumber(1900 + manager.getWave() * 8, 2000 + manager.getWave() * 8)}, 0, 35, 35, 15, 0, 90, 2, 1, 10, "<purple>Baron Nashor<white>", 0);
                    break;
                default:
                    enemy = new Enemy(new int[]{randomNumber(1900 + manager.getWave() * 8, 2000 + manager.getWave() * 8)}, 0, 35, 35, 15, 0, 90, 2, 1, 10, "<purple>Baron Nashor<white>", 0);
                    break;
            }

            previous = random;

            random = randomNumber(1, 2);

            enemyState = "";

            if (enemy.getType() == 1) {
                enemyState = "Damage: <red>Physical (" + enemy.getAttackDamage() + " AD)<white>\n";
            } else if (enemy.getType() == 2){
                enemyState = "Damage: <blue>Magical (" + enemy.getAbilityPower() + " AP)<white>\n";
            }

            enemyState += "Resistances: <yellow>" + enemy.getArmor() + " Armor<white>, <cyan>" + enemy.getMagicResist() + " Magic resist<white>\n\n";
            enemyState += "HP:\n";

            for (i = 0; i < enemy.getTotal(); i++) {
                if (enemy.getHp()[i] > 0) {
                    enemyState += (i + 1) + ". <green>" + enemy.getHp()[i] + " HP<white>";
                    if (i < enemy.getTotal() - 1) enemyState += "\n";
                }
            }

            if (random == 1) {
                System.out.println(new Text("WAVE " + (manager.getWave() + 1) + "\n\nYou encountered " + enemy.getName() + "<white>!\n\nYOUR QUICK STATS\nResources: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana<white>, <yellow>" + player.getGold() + " Gold<white>\nDamage: <red>" + player.getAttackDamage() + " Attack Damage<white>, <blue>" + player.getAbilityPower() + " OdysseyBattle.Ability Power<white>\n\nENEMY QUICK STATS\n" + enemyState + "\n\n<white>It is currently <red>the enemy's <white>turn."));

                checkBuffs(player);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
                }

                enemy.attack(player);

                if (player.getHp() <= 0) {
                    System.out.println(new Text("<red>You died... Game over!"));
                    manager.setWave(-1);
                    break;
                }

                prompt(enemy, shop, player, manager, 2);
            } else if (random == 2) {
                System.out.println(new Text("WAVE " + (manager.getWave() + 1) + "\n\nYou encountered " + enemy.getName() + "<white>!\n\nYOUR QUICK STATS\nResources: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana<white>, <yellow>" + player.getGold() + " Gold<white>\nDamage: <red>" + player.getAttackDamage() + " Attack Damage<white>, <blue>" + player.getAbilityPower() + " OdysseyBattle.Ability Power<white>\n\nENEMY QUICK STATS\n" + enemyState + "\n\n<white>It is currently <green>your <white>turn."));

                checkBuffs(player);

                prompt(enemy, shop, player, manager, 1);
            }
        }

        if (manager.getWave() == 14) {
            System.out.println(new Text("You emerge from the chaos of the fighting with your Champion, worn but relieved. You can see your ship in the distance, and you can almost envision yourself taking off from this cursed planet already.\n\n<red>Suddenly, you see a flash ahead. An orb of light is steadily growing, emitting bolts of lighting in all directions. You are rooted to the spot in curiosity (and likely fear) as you watch a giant holographic scythe emerge from the light.<white>\n\"Not again...\" You think to yourself. You can almost feel your Champion thinking the same way.\n\n<!> Congrats, anonymous player, for reaching this point in the game. However, one more challenge stands in your path: the infamous Final Villain, known by many across the galaxy as OdysseyBattle.Kayn. Anything and everything that OdysseyBattle.Kayn slashes and reaps with his deadly blade stands no chance.\nAre you up to this tall task? Let's find out! Press any key to start the Final Battle."));

            scanner = new Scanner(System.in);
            scanner.nextLine();

            kayn = new Kayn(new int[]{1950}, 300, 40, 30, 25, 80, 65, 40, 200, 1, 11, "<red>OdysseyBattle.Kayn<white>", 0);

            enemyState = "Resources: <green>" + kayn.getHp()[0] + "/" + kayn.getMaxHp() + " HP<white>, <blue>" + kayn.getMana() + " Mana<white>\nDamage: <red>" + kayn.getAttackDamage() + " Attack Damage<white>, <blue>" + kayn.getAbilityPower() + " OdysseyBattle.Ability Power<white>\nResistances: <yellow>" + kayn.getArmor() + " Armor<white>, <cyan>" + kayn.getMagicResist() + " Magic resist<white>";

            System.out.println(new Text("YOUR QUICK STATS\nResources: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana<white>, <yellow>" + player.getGold() + " Gold<white>\nDamage: <red>" + player.getAttackDamage() + " Attack Damage<white>, <blue>" + player.getAbilityPower() + " OdysseyBattle.Ability Power<white>\n\nENEMY QUICK STATS\n" + enemyState + "\n"));

            random = randomNumber(1, 2);

            if (random == 1) {
                System.out.println(new Text("<white>It is currently <red>the enemy's <white>turn."));

                checkBuffs(player);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
                }

                kayn.choose(player);

                if (player.getHp() <= 0) {
                    System.out.println(new Text("<red>You died... Game over!"));
                    manager.setWave(-1);
                    return;
                }

                promptKayn(kayn, shop, player, manager, 2);
            } else {
                checkBuffs(player);

                promptKayn(kayn, shop, player, manager, 1);
            }

            System.out.println(new Text("You watch in shock as OdysseyBattle.Kayn and his scythe disintegrate into pixels of light. Somehow, you managed to defeat the all-powerful Final Villain with the help of your Champion. You can hardly believe it as you step onto your spaceship and prepare for takeoff.\n\n<red>On the other side of Odyssey, the true OdysseyBattle.Kayn in his demonic palace laughs at your foolishness, picks up his scythe, and heads towards his own spaceship.\n\n<yellow>CONGRATULATIONS! <green>YOU WIN...?"));
        }

        System.out.println(new Text("Play again? Please enter <green>Yes<white> or <red>No<white>."));

        scanner = new Scanner(System.in);
        input = scanner.nextLine();

        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
            error();

            scanner = new Scanner(System.in);
            input = scanner.nextLine();
        }

        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            System.out.println(new Text("<green>Restarting..."));
            run();
        } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            System.out.println(new Text("Thanks for playing! Exiting..."));
            System.exit(0);
        }
    }

    public static void prompt(Enemy enemy, Shop shop, Player player, Manager manager, int detailed) {
        Scanner scanner;
        String input, shopState, enemyState, abilityState, buffState;
        String[] split, abilityIds = new String[]{"Q", "W", "E"};
        int i;

        switch (detailed) {
            case 0:
                enemyState = "";
                for (i = 0; i < enemy.getTotal(); i++) {
                    if (enemy.getHp()[i] > 0) {
                        enemyState += (i + 1) + ". <green>" + enemy.getHp()[i] + " HP<white>";
                        if (i < enemy.getTotal() - 1) enemyState += "\n";
                    }
                }

                System.out.println(new Text("Please enter your move below!\n\n<white>YOU: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana\n\n<white>ENEMIES (" + enemy.getName() + "<white>):\n" + enemyState));

                break;
            case 1:
                System.out.println(new Text("What do you do? Please enter your move below!"));
                break;
            case 2:
                System.out.println(new Text("Please enter your move below!\n\n<white>YOU: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana"));
                break;
            case 3:
                System.out.println(new Text("It is still <green>your<white> turn. Please enter your move below!"));
                break;
        }

        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        split = input.split(" ");

        while (!split[0].equalsIgnoreCase("attack") && !split[0].equalsIgnoreCase("cast") && !split[0].equalsIgnoreCase("dodge") && !split[0].equalsIgnoreCase("inventory") && !split[0].equalsIgnoreCase("shop") && !split[0].equalsIgnoreCase("buy") && !split[0].equalsIgnoreCase("sell") && !split[0].equalsIgnoreCase("stats") && !split[0].equalsIgnoreCase("instructions") && !split[0].equalsIgnoreCase("exit")) {
            error();

            scanner = new Scanner(System.in);
            input = scanner.nextLine();
            split = input.split(" ");
        }

        if (split[0].equalsIgnoreCase("attack") || split[0].equalsIgnoreCase("buy") || split[0].equalsIgnoreCase("sell")) {
            while (split.length <= 1 || !isInteger(split[1]) || Integer.parseInt(split[1]) <= 0) {
                error();

                scanner = new Scanner(System.in);
                input = scanner.nextLine();
                split = input.split(" ");
            }

            if (split[0].equalsIgnoreCase("attack")) {
                while (Integer.parseInt(split[1]) > enemy.getTotal()) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            } else if (split[0].equalsIgnoreCase("buy")) {
                while (Integer.parseInt(split[1]) > shop.getLength()) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            } else if (split[0].equalsIgnoreCase("sell")) {
                while (Integer.parseInt(split[1]) > 6 || player.getInventory()[Integer.parseInt(split[1])] == null) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            }
        } else if (split[0].equalsIgnoreCase("cast")) {
            while (split.length <= 1 || !split[1].equalsIgnoreCase("q") && !split[1].equalsIgnoreCase("w") && !split[1].equalsIgnoreCase("e")) {
                error();

                scanner = new Scanner(System.in);
                input = scanner.nextLine();
                split = input.split(" ");
            }

            if (split[1].equalsIgnoreCase("q") && !player.getAbilities()[0].isAoe() || split[1].equalsIgnoreCase("w") && !player.getAbilities()[1].isAoe() || split[1].equalsIgnoreCase("e") && !player.getAbilities()[2].isAoe()) {
                while (split.length <= 2 || Integer.parseInt(split[2]) > enemy.getTotal()) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            }
        }

        if (player.isDodging()) player.setDodging(false);

        if (split[0].equalsIgnoreCase("attack")) {
            if (enemy.getHp()[Integer.parseInt(split[1]) - 1] <= 0) {
                System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                prompt(enemy, shop, player, manager, 4);
                return;
            }

            decrementCooldowns(player);
            player.attack(enemy, Integer.parseInt(split[1]) - 1);
        } else if (split[0].equalsIgnoreCase("cast")) {
            if (split[1].equalsIgnoreCase("q")) {
                if (!player.getAbilities()[0].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                        prompt(enemy, shop, player, manager, 4);
                        return;
                    }

                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[0].getMana()) {
                            player.cast(enemy, "q", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[0].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[0].getMana() * 0.8)) {
                            player.cast(enemy, "q", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[0].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                } else {
                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[0].getMana()) {
                            player.cast(enemy, "q", 0);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[0].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[0].getMana() * 0.8)) {
                            player.cast(enemy, "q", 0);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[0].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                }

                decrementCooldowns(player);
            } else if (split[1].equalsIgnoreCase("w")) {
                if (!player.getAbilities()[1].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                        prompt(enemy, shop, player, manager, 4);
                        return;
                    }

                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[1].getMana()) {
                            player.cast(enemy, "w", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[1].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[1].getMana() * 0.8)) {
                            player.cast(enemy, "w", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[1].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                } else {
                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[1].getMana()) {
                            player.cast(enemy, "w", 0);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[1].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[1].getMana() * 0.8)) {
                            player.cast(enemy, "w", 0);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[1].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                }

                decrementCooldowns(player);
            } else if (split[1].equalsIgnoreCase("e")) {
                if (!player.getAbilities()[2].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                        prompt(enemy, shop, player, manager, 4);
                        return;
                    }

                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[2].getMana()) {
                            player.cast(enemy, "e", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[2].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[2].getMana() * 0.8)) {
                            player.cast(enemy, "e", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[2].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                } else {
                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[2].getMana()) {
                            player.cast(enemy, "e", 0);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[2].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[2].getMana() * 0.8)) {
                            player.cast(enemy, "e", 0);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[2].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            prompt(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                }

                decrementCooldowns(player);
            }
        } else if (split[0].equalsIgnoreCase("dodge")) {
            player.dodge();
            decrementCooldowns(player);
        } else if (split[0].equalsIgnoreCase("shop")) {
            shopState = "";

            for (i = 0; i < shop.getLength(); i++) {
                if (shop.getItems()[i].isPurchased()) {
                    shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| Recommended: " + shop.getItems()[i].getRecommended() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <yellow>" + shop.getItems()[i].getCost() + " Gold<white> | <green>Owned<white>";
                } else {
                    if (shop.getItems()[i].isPurchasable()) {
                        shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| Recommended: " + shop.getItems()[i].getRecommended() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <yellow>" + shop.getItems()[i].getCost() + " Gold<white> | <red>Not owned<white> | Purchasable";
                    } else {
                        shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <red>Not owned<white> | Not purchasable";
                    }
                }
                if (i < shop.getLength() - 1) shopState += "\n";
            }

            System.out.println(new Text("ITEM SHOP\n<yellow>Your gold: " + player.getGold() + "<white>\n\n" + shopState));
            prompt(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("inventory")) {
            shopState = "";

            for (i = 0; i < player.getInventory().length; i++) {
                if (player.getInventory()[i] != null) {
                    if (player.getInventory()[i].isPurchasable()) {
                        shopState += (i + 1) + ". <yellow>" + player.getInventory()[i].getName() + " <white>| Recommended: " + player.getInventory()[i].getRecommended() + " <white>| " + player.getInventory()[i].getStats() + " <white>| <yellow>" + player.getInventory()[i].getCost() + " Gold<white>";
                    } else {
                        shopState += (i + 1) + ". <yellow>" + player.getInventory()[i].getName() + " <white>| " + player.getInventory()[i].getStats() + " <white>";
                    }
                    shopState += "\n";
                }
            }

            if (shopState.equals("")) shopState = "No items";

            System.out.println(new Text("YOUR INVENTORY\n<yellow>" + player.getGold() + " Gold<white>\n" + shopState));
            prompt(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("buy")) {
            if (player.getGold() < shop.getItems()[Integer.parseInt(split[1]) - 1].getCost()) {
                System.out.println(new Text("<red>You cannot buy this item because you do not have enough <yellow>Gold<red>!<white> Please choose a different move."));
                prompt(enemy, shop, player, manager, 4);
                return;
            } else if (shop.getItems()[Integer.parseInt(split[1]) - 1].isPurchased()) {
                System.out.println(new Text("<red>You cannot buy this item because you already own it!<white> Please choose a different move."));
                prompt(enemy, shop, player, manager, 4);
                return;
            } else if (!shop.getItems()[Integer.parseInt(split[1]) - 1].isPurchasable()) {
                System.out.println(new Text("<red>You cannot buy this item because it is not purchasable!<white> Please choose a different move."));
                prompt(enemy, shop, player, manager, 4);
                return;
            }

            shop.buy(player, Integer.parseInt(split[1]));
            prompt(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("sell")) {
            for (i = 0; i < player.getInventory().length; i++) {
                if (player.getInventory()[i] != null && player.getInventory()[i].getId() == Integer.parseInt(split[1])) {
                    shop.sell(player, Integer.parseInt(split[1]));
                    prompt(enemy, shop, player, manager, 3);
                    return;
                }
            }

            System.out.println(new Text("<red>You cannot sell this item because you do not own it!<white> Please choose a different move."));
            prompt(enemy, shop, player, manager, 4);
            return;
        } else if (split[0].equalsIgnoreCase("stats")) {
            if (split.length == 1 || !split[1].equalsIgnoreCase("enemy")) {
                abilityState = "";

                if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                    for (i = 0; i < player.getAbilities().length; i++) {
                        if (player.getAbilities()[i].getType() == 1) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        } else if (player.getAbilities()[i].getType() == 2) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        }

                        if (i < player.getAbilities().length - 1) abilityState += "\n";
                    }
                } else {
                    for (i = 0; i < player.getAbilities().length; i++) {
                        if (player.getAbilities()[i].getType() == 1) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        } else if (player.getAbilities()[i].getType() == 2) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        }

                        if (i < player.getAbilities().length - 1) abilityState += "\n";
                    }
                }

                if (player.getBuffs()[0] != null && player.getBuffs()[1] != null) {
                    buffState = "<red>Crest of Cinders<white>, <blue>Crest of Insight<white>";
                } else if (player.getBuffs()[0] != null) {
                    buffState = "<red>Crest of Cinders<white>";
                } else if (player.getBuffs()[1] != null) {
                    buffState = "<blue>Crest of Insight<white>";
                } else {
                    buffState = "No Crests";
                }

                System.out.println(new Text("YOUR CHAMPION (" + player.getName() + ") STATS\n\nResources: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana<white>, <yellow>" + player.getGold() + " Gold<white>\nDamage: <red>" + player.getAttackDamage() + " Attack Damage<white>, <blue>" + player.getAbilityPower() + " OdysseyBattle.Ability Power<white>\nResistances: <yellow>" + player.getArmor() + " Armor<white>, <cyan>" + player.getMagicResist() + " Magic Resist<white>\nMiscellaneous: <purple>" + player.getPenetration() + "% Penetration<white>, <red>" + player.getCriticalChance() + "% Critical Strike Chance<white>, " + player.getSpeed() + " Move Speed\n\nCrests: " + buffState + "\n\nAbilities\n" + abilityState + "\n"));
            } else if (split.length > 1 && split[1].equalsIgnoreCase("enemy")) {
                enemyState = "";
                for (i = 0; i < enemy.getTotal(); i++) {
                    if (enemy.getHp()[i] > 0) {
                        enemyState += (i + 1) + ". <green>" + enemy.getHp()[i] + " HP<white>";
                        if (i < enemy.getTotal() - 1) enemyState += "\n";
                    }
                }

                System.out.println(new Text("ENEMY (" + enemy.getName() + ") STATS\n\nDamage: <red>" + enemy.getAttackDamage() + " Attack Damage<white>, <blue>" + enemy.getAbilityPower() + " OdysseyBattle.Ability Power<white>\nResistances: <yellow>" + enemy.getArmor() + " Armor<white>, <cyan>" + enemy.getMagicResist() + " Magic Resist<white>\nMiscellaneous: <purple>" + enemy.getPenetration() + "% Penetration<white>\n<green>HP<white>:\n" + enemyState + "\n"));
            }

            prompt(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("instructions")) {
            System.out.println(new Text("INSTRUCTIONS\n\n" +
                    "<!> HOW TO PLAY\nEvery turn, you can perform one of the following actions:\n1. <red>Attack<white>: Type <cyan>\"attack [enemy index]\"<white> to deal damage to the specified enemy.\n2. <blue>Cast an ability<white>: Type <cyan>\"cast [Q/W/E] [optional enemy index]\"<white> to cast one of your abilities on the specified enemy, or all alive enemies if the ability has an area of effect.\n3. <yellow>Dodge<white>: Type <cyan>\"dodge\"<white> to go into a dodge position, increasing your dodge rate and healing you if you successfully dodge.\n4. OdysseyBattle.Shop: Type <cyan>\"shop\"<white> to view the item shop.\n5. Inventory: Type <cyan>\"inventory\"<white> to view your current item inventory.\n6. <green>Buy<white>: Type <cyan>\"buy [item index]\"<white> to buy the specified item from the shop.\n7. <green>Sell<white>: Type <cyan>\"sell [item index]\"<white> to sell the specified item in your inventory.\n8. <cyan>Stats<white>: Type <cyan>\"stats\"<white> to view detailed stats about your current Champion. Type <cyan>\"stats enemy\"<white> to view detailed stats about the current enemy.\n\n" +
                    "<!> ABILITIES\nYour Champion is equipped with powerful Abilities that can be cast in battle. However, be mindful of your abilities' <blue>Mana costs<white> and cooldowns. Each ability has a different <blue>Mana cost<white> and cooldown. To check, use the <cyan>\"stats\"<white> command.\n\n" +
                    "<!> DAMAGE\nYour Champion will deal and receive three types of damage: <red>physical<white>, <blue>magical<white>, and true. <red>Physical damage<white> scales with your <red>Attack Damage (AD)<white>. <blue>Magical damage<white> scales with your <blue>OdysseyBattle.Ability Power (AP)<white>. True damage is absolute and has no scaling.\n\n" +
                    "<!> RESISTANCES AND PENETRATION\nYour Champion and enemies both have resistances that reduce the amount of incoming damage. <yellow>Armor<white> reduces the amount of incoming <red>physical damage<white>. <cyan>Magic resist<white> reduces the amount of incoming <blue>magical damage<white>. <purple>Penetration<white> ignores a portion of both <yellow>Armor<white> and <cyan>Magic resist<white>.\n\n" +
                    "<!> IMMOBILIZATION\nSome of your Champion's abilities can <purple>Immobilize<white> the enemy, causing them to be unable to attack for the next turn.\n\n" +
                    "<!> RESOURCES\nYour Champion has a series of resources that you need to carefully manage.\n<green>HP<white>: The amount of health points your Champion has left. If this value drops to 0, it is game over!\n<green>Max HP<white>: This is the maximum amount of health points your Champion can have. <green>Healing<white> is limited by this value.\n<blue>Mana<white>: This is one of the resources required to cast abilities.\n<yellow>Gold<white>: This is the currency required to buy Items.\n<green>HP<white>, <blue>Mana<white>, and <yellow>Gold<white> will be gained upon slaying an enemy.\n\n" +
                    "<!> SHOP AND ITEMS\nYou can purchase powerful Items from the shop. Items will give your Champion additional statistics to improve their damage, max HP, resistances, and dodge rate. <yellow>Gold<white> is required to purchase Items, and 70% of the <yellow>Gold<white> spent will be refunded upon selling an item. In addition, each item is unique; you cannot purchase more than one copy of each item.\n\n" +
                    "<!> CRESTS\nCrests are an additional buff for your Champion. Each Crest lasts for 3 waves.\n<red>Crest of Cinders<white>: You will deal <yellow>10%<white> of the target's current HP as true damage upon attacking. It can only be obtained by defeating the <red>Red Brambleback<white>.\n<blue>Crest of Insight<white>: You will use <yellow>10%<white> less <blue>Mana<white> upon casting an ability. You will also gain <yellow>5%<white> additional <blue>Mana<white> upon slaying an enemy. It can only be obtained from the <blue>Blue Sentinel<white>."));

            prompt(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("exit")) {
            System.out.println(new Text("Thanks for playing! Exiting..."));
            System.exit(0);
        }

        if (enemy.getCount() > 0) {
            System.out.println(new Text("It is now <red>the enemy's <white>turn."));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
            }

            if (!enemy.isImmobilized()) {
                enemy.attack(player);

                if (player.getHp() <= 0) {
                    System.out.println(new Text("<red>You died... Game over!"));
                    manager.setWave(-1);
                    return;
                }
            } else {
                System.out.println(new Text("Because the enemy is currently <purple>Immobilized<white>, they are unable to attack you this turn."));
                enemy.setImmobilized(false);
            }

            prompt(enemy, shop, player, manager, 0);
        } else {
            System.out.println(new Text("You have successfully cleared this wave!"));
            manager.incrementWave();

            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
            }
        }
    }

    public static void promptKayn(Kayn enemy, Shop shop, Player player, Manager manager, int detailed) {
        Scanner scanner;
        String input, shopState, enemyState, abilityState, buffState;
        String[] split, abilityIds = new String[]{"Q", "W", "E"};
        int i;

        switch (detailed) {
            case 0:
                System.out.println(new Text("Please enter your move below!\n\n<white>YOU: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana\n\n<red>KAYN<white>: <green>" + enemy.getHp()[0] + "/" + enemy.getMaxHp() + " HP<white>, <blue>" + enemy.getMana() + " Mana<white>"));
                break;
            case 1:
                System.out.println(new Text("What do you do? Please enter your move below!"));
                break;
            case 2:
                System.out.println(new Text("Please enter your move below!\n\n<white>YOU: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana"));
                break;
            case 3:
                System.out.println(new Text("It is still <green>your<white> turn. Please enter your move below!"));
                break;
        }

        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        split = input.split(" ");

        while (!split[0].equalsIgnoreCase("attack") && !split[0].equalsIgnoreCase("cast") && !split[0].equalsIgnoreCase("dodge") && !split[0].equalsIgnoreCase("inventory") && !split[0].equalsIgnoreCase("shop") && !split[0].equalsIgnoreCase("buy") && !split[0].equalsIgnoreCase("sell") && !split[0].equalsIgnoreCase("stats") && !split[0].equalsIgnoreCase("instructions") && !split[0].equalsIgnoreCase("exit")) {
            error();

            scanner = new Scanner(System.in);
            input = scanner.nextLine();
            split = input.split(" ");
        }

        if (split[0].equalsIgnoreCase("attack") || split[0].equalsIgnoreCase("buy") || split[0].equalsIgnoreCase("sell")) {
            while (split.length <= 1 || !isInteger(split[1]) || Integer.parseInt(split[1]) <= 0) {
                error();

                scanner = new Scanner(System.in);
                input = scanner.nextLine();
                split = input.split(" ");
            }

            if (split[0].equalsIgnoreCase("attack")) {
                while (Integer.parseInt(split[1]) > enemy.getTotal()) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            } else if (split[0].equalsIgnoreCase("buy")) {
                while (Integer.parseInt(split[1]) > shop.getLength()) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            } else if (split[0].equalsIgnoreCase("sell")) {
                while (Integer.parseInt(split[1]) > 6 || player.getInventory()[Integer.parseInt(split[1])] == null) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            }
        } else if (split[0].equalsIgnoreCase("cast")) {
            while (split.length <= 1 || !split[1].equalsIgnoreCase("q") && !split[1].equalsIgnoreCase("w") && !split[1].equalsIgnoreCase("e")) {
                error();

                scanner = new Scanner(System.in);
                input = scanner.nextLine();
                split = input.split(" ");
            }

            if (split[1].equalsIgnoreCase("q") && !player.getAbilities()[0].isAoe() || split[1].equalsIgnoreCase("w") && !player.getAbilities()[1].isAoe() || split[1].equalsIgnoreCase("e") && !player.getAbilities()[2].isAoe()) {
                while (split.length <= 2 || Integer.parseInt(split[2]) > enemy.getTotal()) {
                    error();

                    scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    split = input.split(" ");
                }
            }
        }

        if (player.isDodging()) player.setDodging(false);

        if (split[0].equalsIgnoreCase("attack")) {
            if (enemy.getHp()[Integer.parseInt(split[1]) - 1] <= 0) {
                System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                promptKayn(enemy, shop, player, manager, 4);
                return;
            }

            decrementCooldowns(player);
            player.attack(enemy, Integer.parseInt(split[1]) - 1);
        } else if (split[0].equalsIgnoreCase("cast")) {
            if (split[1].equalsIgnoreCase("q")) {
                if (!player.getAbilities()[0].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                        promptKayn(enemy, shop, player, manager, 4);
                        return;
                    }

                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[0].getMana()) {
                            player.cast(enemy, "q", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[0].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[0].getMana() * 0.8)) {
                            player.cast(enemy, "q", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[0].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                } else {
                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[0].getMana()) {
                            player.cast(enemy, "q", 0);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[0].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[0].getMana() * 0.8)) {
                            player.cast(enemy, "q", 0);
                        } else {
                            if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[0].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                }

                decrementCooldowns(player);
            } else if (split[1].equalsIgnoreCase("w")) {
                if (!player.getAbilities()[1].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                        promptKayn(enemy, shop, player, manager, 4);
                        return;
                    }

                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[1].getMana()) {
                            player.cast(enemy, "w", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[1].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[1].getMana() * 0.8)) {
                            player.cast(enemy, "w", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[1].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                } else {
                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[1].getMana()) {
                            player.cast(enemy, "w", 0);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[1].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[1].getMana() * 0.8)) {
                            player.cast(enemy, "w", 0);
                        } else {
                            if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[1].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                }

                decrementCooldowns(player);
            } else if (split[1].equalsIgnoreCase("e")) {
                if (!player.getAbilities()[2].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please choose a different move."));
                        promptKayn(enemy, shop, player, manager, 4);
                        return;
                    }

                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[2].getMana()) {
                            player.cast(enemy, "e", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[2].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[2].getMana() * 0.8)) {
                            player.cast(enemy, "e", Integer.parseInt(split[2]) - 1);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[2].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                } else {
                    if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[2].getMana()) {
                            player.cast(enemy, "e", 0);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < player.getAbilities()[2].getMana()) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    } else {
                        if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= (int) (player.getAbilities()[2].getMana() * 0.8)) {
                            player.cast(enemy, "e", 0);
                        } else {
                            if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please choose a different move."));
                            } else if (player.getMana() < (int) (player.getAbilities()[2].getMana() * 0.8)) {
                                System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please choose a different move."));
                            }

                            promptKayn(enemy, shop, player, manager, 4);
                            return;
                        }
                    }
                }

                decrementCooldowns(player);
            }
        } else if (split[0].equalsIgnoreCase("dodge")) {
            player.dodge();
            decrementCooldowns(player);
        } else if (split[0].equalsIgnoreCase("shop")) {
            shopState = "";

            for (i = 0; i < shop.getLength(); i++) {
                if (shop.getItems()[i].isPurchased()) {
                    shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| Recommended: " + shop.getItems()[i].getRecommended() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <yellow>" + shop.getItems()[i].getCost() + " Gold<white> | <green>Owned<white>";
                } else {
                    if (shop.getItems()[i].isPurchasable()) {
                        shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| Recommended: " + shop.getItems()[i].getRecommended() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <yellow>" + shop.getItems()[i].getCost() + " Gold<white> | <red>Not owned<white> | Purchasable";
                    } else {
                        shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <red>Not owned<white> | Not purchasable";
                    }
                }
                if (i < shop.getLength() - 1) shopState += "\n";
            }

            System.out.println(new Text("ITEM SHOP\n<yellow>Your gold: " + player.getGold() + "<white>\n\n" + shopState));
            promptKayn(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("inventory")) {
            shopState = "";

            for (i = 0; i < player.getInventory().length; i++) {
                if (player.getInventory()[i] != null) {
                    if (player.getInventory()[i].isPurchasable()) {
                        shopState += (i + 1) + ". <yellow>" + player.getInventory()[i].getName() + " <white>| Recommended: " + player.getInventory()[i].getRecommended() + " <white>| " + player.getInventory()[i].getStats() + " <white>| <yellow>" + player.getInventory()[i].getCost() + " Gold<white>";
                    } else {
                        shopState += (i + 1) + ". <yellow>" + player.getInventory()[i].getName() + " <white>| " + player.getInventory()[i].getStats() + " <white>";
                    }
                    shopState += "\n";
                }
            }

            if (shopState.equals("")) shopState = "No items";

            System.out.println(new Text("YOUR INVENTORY\n<yellow>" + player.getGold() + " Gold<white>\n" + shopState));
            promptKayn(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("buy")) {
            if (player.getGold() < shop.getItems()[Integer.parseInt(split[1]) - 1].getCost()) {
                System.out.println(new Text("<red>You cannot buy this item because you do not have enough <yellow>Gold<red>!<white> Please choose a different move."));
                promptKayn(enemy, shop, player, manager, 4);
                return;
            } else if (shop.getItems()[Integer.parseInt(split[1]) - 1].isPurchased()) {
                System.out.println(new Text("<red>You cannot buy this item because you already own it!<white> Please choose a different move."));
                promptKayn(enemy, shop, player, manager, 4);
                return;
            } else if (!shop.getItems()[Integer.parseInt(split[1]) - 1].isPurchasable()) {
                System.out.println(new Text("<red>You cannot buy this item because it is not purchasable!<white> Please choose a different move."));
                promptKayn(enemy, shop, player, manager, 4);
                return;
            }

            shop.buy(player, Integer.parseInt(split[1]));
            promptKayn(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("sell")) {
            for (i = 0; i < player.getInventory().length; i++) {
                if (player.getInventory()[i] != null && player.getInventory()[i].getId() == Integer.parseInt(split[1])) {
                    shop.sell(player, Integer.parseInt(split[1]));
                    promptKayn(enemy, shop, player, manager, 3);
                    return;
                }
            }

            System.out.println(new Text("<red>You cannot sell this item because you do not own it!<white> Please choose a different move."));
            promptKayn(enemy, shop, player, manager, 4);
            return;
        } else if (split[0].equalsIgnoreCase("stats")) {
            if (split.length == 1 || !split[1].equalsIgnoreCase("enemy")) {
                abilityState = "";

                if (player.getBuffs()[1] == null || player.getBuffs()[1].getType() != 2) {
                    for (i = 0; i < player.getAbilities().length; i++) {
                        if (player.getAbilities()[i].getType() == 1) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        } else if (player.getAbilities()[i].getType() == 2) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        }

                        if (i < player.getAbilities().length - 1) abilityState += "\n";
                    }
                } else {
                    for (i = 0; i < player.getAbilities().length; i++) {
                        if (player.getAbilities()[i].getType() == 1) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        } else if (player.getAbilities()[i].getType() == 2) {
                            if (!player.getAbilities()[i].isAoe()) {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            } else {
                                if (!player.getAbilities()[i].isImmobilizing()) {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                } else {
                                    abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + (int) (player.getAbilities()[i].getMana() * 0.8) + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| <purple>Immobilizing<white> | " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                                }
                            }
                        }

                        if (i < player.getAbilities().length - 1) abilityState += "\n";
                    }
                }

                if (player.getBuffs()[0] != null && player.getBuffs()[1] != null) {
                    buffState = "<red>Crest of Cinders<white>, <blue>Crest of Insight<white>";
                } else if (player.getBuffs()[0] != null) {
                    buffState = "<red>Crest of Cinders<white>";
                } else if (player.getBuffs()[1] != null) {
                    buffState = "<blue>Crest of Insight<white>";
                } else {
                    buffState = "No Crests";
                }

                System.out.println(new Text("YOUR CHAMPION (" + player.getName() + ") STATS\n\nResources: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana<white>, <yellow>" + player.getGold() + " Gold<white>\nDamage: <red>" + player.getAttackDamage() + " Attack Damage<white>, <blue>" + player.getAbilityPower() + " OdysseyBattle.Ability Power<white>\nResistances: <yellow>" + player.getArmor() + " Armor<white>, <cyan>" + player.getMagicResist() + " Magic Resist<white>\nMiscellaneous: <purple>" + player.getPenetration() + "% Penetration<white>, <red>" + player.getCriticalChance() + "% Critical Strike Chance<white>, " + player.getSpeed() + " Move Speed\n\nCrests: " + buffState + "\n\nAbilities\n" + abilityState + "\n"));
            } else if (split.length > 1 && split[1].equalsIgnoreCase("enemy")) {
                enemyState = "Resources: <green>" + enemy.getHp()[0] + "/" + enemy.getMaxHp() + " HP<white>, <blue>" + enemy.getMana() + " Mana<white>\nDamage: <red>" + enemy.getAttackDamage() + " Attack Damage<white>, <blue>" + enemy.getAbilityPower() + " OdysseyBattle.Ability Power<white>\nResistances: <yellow>" + enemy.getArmor() + " Armor<white>, <cyan>" + enemy.getMagicResist() + " Magic resist<white>\nMiscellaneous: <purple>" + enemy.getPenetration() + "%<white>, <red>" + enemy.getCriticalChance() + "% Critical Strike Chance<white>, " + enemy.getSpeed() + " Move Speed";

                abilityState = "";
                for (i = 0; i < enemy.getAbilities().length; i++) {
                    if (i != 2) {
                        if (enemy.getAbilities()[i].getType() == 1) {
                            abilityState += abilityIds[i] + ". " + enemy.getAbilities()[i].getName() + " | <red>" + enemy.getAbilities()[i].getDamage(enemy.getAttackDamage()) + " physical damage <white>| <blue>" + enemy.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + enemy.getAbilities()[i].getVamp() + "% vamp <white>| " + (enemy.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + enemy.getAbilities()[i].getCurrentCooldown() + " turns left";
                        } else if (enemy.getAbilities()[i].getType() == 2) {
                            abilityState += abilityIds[i] + ". " + enemy.getAbilities()[i].getName() + " | <blue>" + enemy.getAbilities()[i].getDamage(enemy.getAttackDamage()) + " magical damage <white>| <blue>" + enemy.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + enemy.getAbilities()[i].getVamp() + "% vamp <white>| " + (enemy.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + enemy.getAbilities()[i].getCurrentCooldown() + " turns left";
                        }
                    } else {
                        abilityState += abilityIds[i] + ". " + enemy.getAbilities()[i].getName() + " | <green>30% current missing HP healing <white>| <blue>" + enemy.getAbilities()[i].getMana() + " Mana <white>| " + (enemy.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + enemy.getAbilities()[i].getCurrentCooldown() + " turns left";
                    }

                    if (i < enemy.getAbilities().length - 1) abilityState += "\n";
                }

                System.out.println(new Text("ENEMY (" + enemy.getName() + ") STATS\n\n" + enemyState + "\n\nAbilities\n" + abilityState));
            }

            promptKayn(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("instructions")) {
            System.out.println(new Text("INSTRUCTIONS\n\n" +
                    "<!> HOW TO PLAY\nEvery turn, you can perform one of the following actions:\n1. <red>Attack<white>: Type <cyan>\"attack [enemy index]\"<white> to deal damage to the specified enemy.\n2. <blue>Cast an ability<white>: Type <cyan>\"cast [Q/W/E] [optional enemy index]\"<white> to cast one of your abilities on the specified enemy, or all alive enemies if the ability has an area of effect.\n3. <yellow>Dodge<white>: Type <cyan>\"dodge\"<white> to go into a dodge position, increasing your dodge rate and healing you if you successfully dodge.\n4. OdysseyBattle.Shop: Type <cyan>\"shop\"<white> to view the item shop.\n5. Inventory: Type <cyan>\"inventory\"<white> to view your current item inventory.\n6. <green>Buy<white>: Type <cyan>\"buy [item index]\"<white> to buy the specified item from the shop.\n7. <green>Sell<white>: Type <cyan>\"sell [item index]\"<white> to sell the specified item in your inventory.\n8. <cyan>Stats<white>: Type <cyan>\"stats\"<white> to view detailed stats about your current Champion. Type <cyan>\"stats enemy\"<white> to view detailed stats about the current enemy.\n\n" +
                    "<!> ABILITIES\nYour Champion is equipped with powerful Abilities that can be cast in battle. However, be mindful of your abilities' <blue>Mana costs<white> and cooldowns. Each ability has a different <blue>Mana cost<white> and cooldown. To check, use the <cyan>\"stats\"<white> command.\n\n" +
                    "<!> DAMAGE\nYour Champion will deal and receive three types of damage: <red>physical<white>, <blue>magical<white>, and true. <red>Physical damage<white> scales with your <red>Attack Damage (AD)<white>. <blue>Magical damage<white> scales with your <blue>OdysseyBattle.Ability Power (AP)<white>. True damage is absolute and has no scaling.\n\n" +
                    "<!> RESISTANCES AND PENETRATION\nYour Champion and enemies both have resistances that reduce the amount of incoming damage. <yellow>Armor<white> reduces the amount of incoming <red>physical damage<white>. <cyan>Magic resist<white> reduces the amount of incoming <blue>magical damage<white>. <purple>Penetration<white> ignores a portion of both <yellow>Armor<white> and <cyan>Magic resist<white>.\n\n" +
                    "<!> IMMOBILIZATION\nSome of your Champion's abilities can <purple>Immobilize<white> the enemy, causing them to be unable to attack for the next turn.\n\n" +
                    "<!> RESOURCES\nYour Champion has a series of resources that you need to carefully manage.\n<green>HP<white>: The amount of health points your Champion has left. If this value drops to 0, it is game over!\n<green>Max HP<white>: This is the maximum amount of health points your Champion can have. <green>Healing<white> is limited by this value.\n<blue>Mana<white>: This is one of the resources required to cast abilities.\n<yellow>Gold<white>: This is the currency required to buy Items.\n<green>HP<white>, <blue>Mana<white>, and <yellow>Gold<white> will be gained upon slaying an enemy.\n\n" +
                    "<!> SHOP AND ITEMS\nYou can purchase powerful Items from the shop. Items will give your Champion additional statistics to improve their damage, max HP, resistances, and dodge rate. <yellow>Gold<white> is required to purchase Items, and 70% of the <yellow>Gold<white> spent will be refunded upon selling an item. In addition, each item is unique; you cannot purchase more than one copy of each item.\n\n" +
                    "<!> CRESTS\nCrests are an additional buff for your Champion. Each Crest lasts for 3 waves.\n<red>Crest of Cinders<white>: You will deal <yellow>10%<white> of the target's current HP as true damage upon attacking. It can only be obtained by defeating the <red>Red Brambleback<white>.\n<blue>Crest of Insight<white>: You will use <yellow>10%<white> less <blue>Mana<white> upon casting an ability. You will also gain <yellow>5%<white> additional <blue>Mana<white> upon slaying an enemy. It can only be obtained from the <blue>Blue Sentinel<white>."));

            promptKayn(enemy, shop, player, manager, 3);
            return;
        } else if (split[0].equalsIgnoreCase("exit")) {
            System.out.println(new Text("Thanks for playing! Exiting..."));
            System.exit(0);
        }

        if (enemy.getCount() > 0) {
            System.out.println(new Text("It is now <red>the enemy's <white>turn."));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
            }

            if (!enemy.isImmobilized()) {
                enemy.choose(player);

                if (player.getHp() <= 0) {
                    System.out.println(new Text("<red>You died... Game over!"));
                    manager.setWave(-1);
                    return;
                }

                decrementCooldowns(enemy);
            } else {
                System.out.println(new Text("Because the enemy is currently <purple>Immobilized<white>, they are unable to attack you this turn."));
                enemy.setImmobilized(false);
            }

            promptKayn(enemy, shop, player, manager, 0);
        } else {
            System.out.println(new Text("You have successfully cleared this wave!"));
            manager.incrementWave();

            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
            }
        }
    }

    public static void decrementCooldowns(Player player) {
        int i;

        for (i = 0; i < player.getAbilities().length; i++) {
            if (player.getAbilities()[i].getCurrentCooldown() > 0) {
                player.getAbilities()[i].setCurrentCooldown(1, 1);
            }
        }
    }
    public static void checkBuffs(Player player) {
        int i;

        for (i = 0; i < player.getBuffs().length; i++) {
            if (player.getBuffs()[i] != null) {
                player.getBuffs()[i].decrementWaves();

                if (player.getBuffs()[i].getWavesLeft() < 0) {
                    if (player.getBuffs()[i].getType() == 1) {
                        System.out.println(new Text("Your <red>Crest of Cinders<white> has expired!"));
                    } else if (player.getBuffs()[i].getType() == 2) {
                        System.out.println(new Text("Your <blue>Crest of Insight<white> has expired!"));
                    }

                    player.getBuffs()[i] = null;
                }
            }
        }
    }
    public static void decrementCooldowns(Kayn enemy) {
        int i;

        for (i = 0; i < enemy.getAbilities().length; i++) {
            if (enemy.getAbilities()[i].getCurrentCooldown() > 0) {
                enemy.getAbilities()[i].setCurrentCooldown(1, 1);
            }
        }
    }

    public static void error() {
        System.out.println(new Text("<red>Invalid input! <white>Please try again."));
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int pickEnemy(Manager manager) {
        int random;

        if (manager.getWave() == 0 || manager.getWave() == 2) {
            random = 1;
        } else if (manager.getWave() == 1 || manager.getWave() == 3) {
            random = 2;
        } else if (manager.getWave() >= 4 && manager.getWave() < 6) {
            random = randomNumber(3, 4);
        } else if (manager.getWave() == 6) {
            random = 5;
        } else if (manager.getWave() == 7) {
            random = 6;
        } else if (manager.getWave() == 8) {
            random = 7;
        } else if (manager.getWave() >= 9 && manager.getWave() < 11) {
            random = randomNumber(3, 4);
        } else if (manager.getWave() == 11) {
            random = 8;
        } else if (manager.getWave() == 12) {
            random = 9;
        } else if (manager.getWave() == 13) {
            random = 10;
        } else {
            random = randomNumber(1, 10);
        }

        return random;
    }

    public static int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}