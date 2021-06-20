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
        String input;
        Player player;
        Enemy enemy;
        Kayn kayn;
        int random, i, previous = 0;

        System.out.println(new Text("<red>ODYSSEY BATTLE <white>| <cyan>WozardLozard\n\n<white>The year is 2080 in the galaxy known simply as \"Odyssey\". You find yourself on a strange alien planet, your ship having crashed down a few hours earlier and stuck on the other side of the planet. In the distance, you hear foreign and frightening sounds of unknown creatures. You know that you must somehow get to your ship to repair it and go home... But how?\n\n<yellow>You realize that you are wearing a backpack. <white>Press any key to open it."));

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
                player = new Player(2200, 345, 20, 50, 20, 10, 80, 40, 100, 1, "Yasuo");
                System.out.println(new Text("You open the golden capsule. A strong wind erupts from it, knocking you back. When you get up again, you see a stranger with a sword and mustache.\n\n<yellow>\"Who are you?\" You ask.\n\"Why, how dare you not know my name? I am Yasuo, the greatest master of sword and wind there ever will be.\" Says the stranger.\n\n<!> You have chosen Yasuo as your Champion!"));
                break;
            case "2":
                player = new Player(1950, 325, 0, 40, 25, 20, 100, 50, 100, 2, "Jinx");
                System.out.println(new Text("You open the green capsule. A cloud of noxious-smelling green gas wafts out, causing you to turn away and shut your eyes. When you look back at where the capsule had been, you see a stranger with a... rocket launcher?!\n\n<green>\"Who are you?\" You ask.\n\"Why, how dare you not know of my record of blowing stuff up? I am Jinx.\" Says the stranger.\n\n<!> You have chosen Jinx as your Champion!"));
                break;
            case "3":
                player = new Player(2500, 335, 0, 60, 50, 15, 45, 45, 100, 3, "Malphite");
                System.out.println(new Text("You open the red capsule. A spire of rock shoots out, and you leap out of the way. When you look back at where the capsule had been, you see a... moving rock?!\n\n<red> \"Who are you? What are you?\" You ask.\nThe rock turns slowly to face you. You realize it has a face. \"I... Malphite!\" The rock cries and claps its fists (yes, fists) together menacingly.\n\n<!> You have chosen Malphite as your Champion!"));
                break;
            default:
                player = new Player(2200, 345, 20, 50, 20, 10, 80, 40, 100, 1, "Yasuo");
                System.out.println(new Text("You open the golden capsule. A strong wind erupts from it, knocking you back. When you get up again, you see a stranger with a sword and mustache.\n\n<yellow>\"Who are you?\" You ask.\n\"Why, how dare you not know my name? I am Yasuo, the greatest master of sword and wind there ever will be.\" Says the stranger.\n\n<!> You have chosen Yasuo as your Champion!"));
                break;
        }

        System.out.println(new Text("<white>With a powerful Champion on your side, what could possibly go wrong now? You are suddenly very confident that you might be able to make it off this planet alive.\n\n<!> Fight through a series of monsters, as well as The Final Villain, with your Champion. Battles are turn-based, and the order of combat is random. However, fret not; your Champion has powerful Attacks, Abilities, and can even be buffed with Crests and Items."));
        System.out.println(new Text("\n<white>And that's it! See if you can make it alive! Remember, you can always type <cyan>\"instructions\"<white> anytime during the battle for reminders on how the game works.\nLast but not least: <yellow>Good luck!\n\n<white>Press any key to begin the battle."));

        scanner = new Scanner(System.in);
        scanner.nextLine();

        while (manager.getWave() < 9) {
            random = pickEnemy(manager);

            while (random == 3 && player.getBuffs()[0] != null && player.getBuffs()[0].getType() == 1 || random == 3 && player.getBuffs()[1] != null && player.getBuffs()[1].getType() == 1 || random == 4 && player.getBuffs()[0] != null && player.getBuffs()[0].getType() == 2 || random == 4 && player.getBuffs()[1] != null && player.getBuffs()[1].getType() == 2 || random == previous) {
                random = pickEnemy(manager);
            }

            switch (random) {
                case 1:
                    enemy = new Enemy(new int[]{randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(250 + manager.getWave() * 8, 300 + manager.getWave() * 8), randomNumber(300 + manager.getWave() * 8, 320 + manager.getWave() * 8)}, 0, 0, 0, 5, 25, 0, 1, 6, 1, "Raptors", 28);
                    break;
                case 2:
                    enemy = new Enemy(new int[]{randomNumber(450 + manager.getWave() * 8, 520 + manager.getWave() * 8), randomNumber(450 + manager.getWave() * 8, 520 + manager.getWave() * 8), randomNumber(520 + manager.getWave() * 8, 580 + manager.getWave() * 8)}, 0, 10, 10, 0, 0, 30, 2, 3, 2, "Murkwolves", 56);
                    break;
                case 3:
                    enemy = new Enemy(new int[]{randomNumber(1200 + manager.getWave() * 8, 1500 + manager.getWave() * 8)}, 0, 30, 20, 10, 85, 0, 1, 1, 3, "Red Brambleback", 160);
                    break;
                case 4:
                    enemy = new Enemy(new int[]{randomNumber(1200 + manager.getWave() * 8, 1500 + manager.getWave() * 8)}, 0, 20, 30, 10, 0, 85, 2, 1, 4, "Blue Sentinel", 160);
                    break;
                case 5:
                    enemy = new Enemy(new int[]{randomNumber(1500 + manager.getWave() * 8, 1800 + manager.getWave() * 8)}, 0, 10, 20, 15, 100, 0, 1, 1, 5, "Infernal Drake", 167);
                    break;
                case 6:
                    enemy = new Enemy(new int[]{randomNumber(1500 + manager.getWave() * 8, 1800 + manager.getWave() * 8)}, 0, 50, 50, 10, 60, 0, 1, 1, 6, "Mountain Drake", 167);
                    break;
                case 7:
                    enemy = new Enemy(new int[]{randomNumber(1500 + manager.getWave() * 8, 1800 + manager.getWave() * 8)}, 0, 25, 25, 10, 80, 0, 1, 1, 7, "Ocean Drake", 167);
                    break;
                default:
                    enemy = new Enemy(new int[]{randomNumber(450 + manager.getWave() * 8, 520 + manager.getWave() * 8), randomNumber(450 + manager.getWave() * 8, 520 + manager.getWave() * 8), randomNumber(520 + manager.getWave() * 8, 580 + manager.getWave() * 8)}, 0, 10, 10, 0, 0, 30, 2, 3, 2, "Murkwolves", 56);
                    break;
            }

            previous = random;

            System.out.println(new Text("WAVE " + (manager.getWave() + 1) + "\n\nYou encountered <red>" + enemy.getName() + "<white>!"));

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
            
            random = randomNumber(1, 2);

            if (random == 1) {
                System.out.println(new Text("\nIt is currently <red>the enemy's <white>turn."));
                
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
                }

                enemy.attack(player);

                if (player.getHp() <= 0) {
                    System.out.println(new Text("<red>You died... Game over!"));
                    manager.setWave(100);
                    break;
                }

                prompt(enemy, shop, player, manager);
            } else if (random == 2) {
                prompt(enemy, shop, player, manager);
            }
        }

        if (manager.getWave() == 9) {
            System.out.println(new Text("You emerge from the chaos of the fighting with your Champion, worn but relieved. You can see your ship in the distance, and you can almost envision yourself taking off from this cursed planet already.\n\n<red>Suddenly, you see a flash ahead. An orb of light is steadily growing, emitting bolts of lighting in all directions. You are rooted to the spot in curiosity (and likely fear) as you watch a giant holographic scythe emerge from the light.<white>\n\"Not again...\" You think to yourself. You can almost feel your Champion thinking the same way.\n\n<!> Congrats, anonymous player, for reaching this point in the game. However, one more challenge stands in your path: the infamous Final Villain, known by many across the galaxy as Kayn. Anything and everything that Kayn slashes and reaps with his deadly blade stands no chance.\nAre you up to this tall task? Let's find out! Press any key to start the Final Battle."));

            scanner = new Scanner(System.in);
            scanner.nextLine();

            kayn = new Kayn(new int[]{1900}, 300, 40, 30, 25, 60, 50, 3, 8, "Kayn", 0);
        } else {
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
    }
    
    public static void prompt(Enemy enemy, Shop shop, Player player, Manager manager) {
        Scanner scanner;
        String input, shopState, enemyState, abilityState, buffState;
        String[] split, abilityIds = new String[]{"Q", "W", "E"};
        int i;

        enemyState = "";
        for (i = 0; i < enemy.getTotal(); i++) {
            if (enemy.getHp()[i] > 0) {
                enemyState += (i + 1) + ". <green>" + enemy.getHp()[i] + " HP<white>";
                if (i < enemy.getTotal() - 1) enemyState += "\n";
            }
        }
        System.out.println(new Text("<yellow>What do you do? Please enter your move below!\n\nYOU: <green>" + player.getHp() + "/" + player.getMaxHp() + " HP<white>, <blue>" + player.getMana() + " Mana\n\n<red>ENEMIES (" + enemy.getName() + ")<white>:\n" + enemyState));

        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        split = input.split(" ");

        while (!split[0].equalsIgnoreCase("attack") && !split[0].equalsIgnoreCase("cast") && !split[0].equalsIgnoreCase("dodge") && !split[0].equalsIgnoreCase("inventory") && !split[0].equalsIgnoreCase("shop") && !split[0].equalsIgnoreCase("buy") && !split[0].equalsIgnoreCase("sell") && !split[0].equalsIgnoreCase("stats") && !split[0].equalsIgnoreCase("instructions")) {
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
            while (!split[1].equalsIgnoreCase("q") && !split[1].equalsIgnoreCase("w") && !split[1].equalsIgnoreCase("e")) {
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
                System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please try again."));
                prompt(enemy, shop, player, manager);
                return;
            }

            decrementCooldowns(player);
            player.attack(enemy, Integer.parseInt(split[1]) - 1);
        } else if (split[0].equalsIgnoreCase("cast")) {
            if (split[1].equalsIgnoreCase("q")) {
                if (!player.getAbilities()[0].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please try again."));
                        prompt(enemy, shop, player, manager);
                        return;
                    }

                    if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[0].getMana()) {
                        player.cast(enemy, "q", Integer.parseInt(split[2]) - 1);
                    } else {
                        if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please try again."));
                        } else if (player.getMana() < player.getAbilities()[0].getMana()) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please try again."));
                        }

                        prompt(enemy, shop, player, manager);
                        return;
                    }
                } else {
                    if (player.getAbilities()[0].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[0].getMana()) {
                        player.cast(enemy, "q", 0);
                    } else {
                        if (player.getAbilities()[0].getCurrentCooldown() > 0) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please try again."));
                        } else if (player.getMana() < player.getAbilities()[0].getMana()) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please try again."));
                        }

                        prompt(enemy, shop, player, manager);
                        return;
                    }

                }

                decrementCooldowns(player);
            } else if (split[1].equalsIgnoreCase("w")) {
                if (!player.getAbilities()[1].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please try again."));
                        prompt(enemy, shop, player, manager);
                        return;
                    }

                    if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[1].getMana()) {
                        player.cast(enemy, "w", Integer.parseInt(split[2]) - 1);
                    } else {
                        if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please try again."));
                        } else if (player.getMana() < player.getAbilities()[1].getMana()) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please try again."));
                        }

                        prompt(enemy, shop, player, manager);
                        return;
                    }
                } else {
                    if (player.getAbilities()[1].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[1].getMana()) {
                        player.cast(enemy, "w", 0);
                    } else {
                        if (player.getAbilities()[1].getCurrentCooldown() > 0) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please try again."));
                        } else if (player.getMana() < player.getAbilities()[1].getMana()) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please try again."));
                        }

                        prompt(enemy, shop, player, manager);
                        return;
                    }
                }

                decrementCooldowns(player);
            } else if (split[1].equalsIgnoreCase("e")) {
                if (!player.getAbilities()[2].isAoe()) {
                    if (enemy.getHp()[Integer.parseInt(split[2]) - 1] <= 0) {
                        System.out.println(new Text("<red>The enemy cannot be attacked! <white>Please try again."));
                        prompt(enemy, shop, player, manager);
                        return;
                    }

                    if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[2].getMana()) {
                        player.cast(enemy, "e", Integer.parseInt(split[2]) - 1);
                    } else {
                        if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please try again."));
                        } else if (player.getMana() < player.getAbilities()[2].getMana()) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please try again."));
                        }

                        prompt(enemy, shop, player, manager);
                        return;
                    }
                } else {
                    if (player.getAbilities()[2].getCurrentCooldown() == 0 && player.getMana() >= player.getAbilities()[2].getMana()) {
                        player.cast(enemy, "e", 0);
                    } else {
                        if (player.getAbilities()[2].getCurrentCooldown() > 0) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because it is on cooldown! <white>Please try again."));
                        } else if (player.getMana() < player.getAbilities()[2].getMana()) {
                            System.out.println(new Text("<red>You cannot cast this ability right now because you do not have enough mana! <white>Please try again."));
                        }

                        prompt(enemy, shop, player, manager);
                        return;
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
                    shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| Recommended: " + shop.getItems()[i].getRecommended() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <yellow>" + shop.getItems()[i].getCost() + " Gold<white> | <green>Purchased<white>";
                } else {
                    shopState += (i + 1) + ". <yellow>" + shop.getItems()[i].getName() + " <white>| Recommended: " + shop.getItems()[i].getRecommended() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <yellow>" + shop.getItems()[i].getCost() + " Gold<white> | <red>Not purchased<white>";
                }
                if (i < shop.getLength() - 1) shopState += "\n";
            }

            System.out.println(new Text("ITEM SHOP\n<yellow>Your gold: " + player.getGold() + "<white>\n\n" + shopState));
            prompt(enemy, shop, player, manager);
            return;
        } else if (split[0].equalsIgnoreCase("inventory")) {
            shopState = "";

            for (i = 0; i < player.getInventory().length; i++) {
                if (player.getInventory()[i] != null) {
                    shopState += (i + 1) + ". <yellow>" + player.getInventory()[i].getName() + " <white>| Recommended: " + shop.getItems()[i].getRecommended() + " <white>| " + shop.getItems()[i].getStats() + " <white>| <yellow>" + shop.getItems()[i].getCost() + " Gold<white>";
                    shopState += "\n";
                }
            }

            if (shopState.equals("")) shopState = "No items";

            System.out.println(new Text("YOUR INVENTORY\n<yellow>" + player.getGold() + " Gold<white>\n" + shopState));
            prompt(enemy, shop, player, manager);
            return;
        } else if (split[0].equalsIgnoreCase("buy")) {
            if (player.getGold() < shop.getItems()[Integer.parseInt(split[1]) - 1].getCost()) {
                System.out.println(new Text("<red>You cannot buy this item because you do not have enough <yellow>Gold<red>!<white> Please try again."));
                prompt(enemy, shop, player, manager);
                return;
            } else if (shop.getItems()[Integer.parseInt(split[1]) - 1].isPurchased()) {
                System.out.println(new Text("<red>You cannot buy this item because you already own it!<white> Please try again."));
                prompt(enemy, shop, player, manager);
                return;
            }

            shop.buy(player, Integer.parseInt(split[1]));
            prompt(enemy, shop, player, manager);
            return;
        } else if (split[0].equalsIgnoreCase("sell")) {
            for (i = 0; i < player.getInventory().length; i++) {
                if (player.getInventory()[i] != null && player.getInventory()[i].getId() == Integer.parseInt(split[1])) {
                    shop.sell(player, Integer.parseInt(split[1]));
                    prompt(enemy, shop, player, manager);
                    return;
                }
            }

            System.out.println(new Text("<red>You cannot sell this item because you do not own it!<white> Please try again."));
            prompt(enemy, shop, player, manager);
            return;
        } else if (split[0].equalsIgnoreCase("stats")) {
            if (split.length == 1 || !split[1].equalsIgnoreCase("enemy")) {
                abilityState = "";

                for (i = 0; i < player.getAbilities().length; i++) {
                    if (player.getAbilities()[i].getType() == 1) {
                        if (!player.getAbilities()[i].isAoe()) {
                            abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                        } else {
                            abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <red>" + player.getAbilities()[i].getDamage(player.getAttackDamage()) + " physical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                        }
                    } else if (player.getAbilities()[i].getType() == 2) {
                        if (!player.getAbilities()[i].isAoe()) {
                            abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Single target | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                        } else {
                            abilityState += abilityIds[i] + ". " + player.getAbilities()[i].getName() + " | <blue>" + player.getAbilities()[i].getDamage(player.getAbilityPower()) + " magical damage <white>| <blue>" + player.getAbilities()[i].getMana() + " Mana <white>| Area of effect | <green>" + player.getAbilities()[i].getVamp() + "% vamp <white>| " + (player.getAbilities()[i].getCooldown() - 1) + "-turn cooldown | " + player.getAbilities()[i].getCurrentCooldown() + " turns left";
                        }
                    }

                    if (i < player.getAbilities().length - 1) abilityState += "\n";
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

                System.out.println(new Text("YOUR CHAMPION (" + player.getName() + ") STATS\n<green>HP: " + player.getHp() + "/" + player.getMaxHp() + "\n<blue>Mana: " + player.getMana() + "\n<red>Attack damage: " + player.getAttackDamage() + "\n<blue>Ability power: " + player.getAbilityPower() + "\n<yellow>Armor: " + player.getArmor() + "\n<cyan>Magic resist: " + player.getMagicResist() + "\n<purple>Penetration: " + player.getPenetration() + "%\n<red>Critical strike chance: " + player.getCriticalChance() + "%\n<white>Move speed: " + player.getSpeed() + "\n\nCrests: " + buffState + "\n\nAbilities\n" + abilityState + "\n"));
            } else if (split.length > 1 && split[1].equalsIgnoreCase("enemy")) {
                enemyState = "";
                for (i = 0; i < enemy.getTotal(); i++) {
                    if (enemy.getHp()[i] > 0) {
                        enemyState += (i + 1) + ". <green>" + enemy.getHp()[i] + " HP<white>";
                        if (i < enemy.getTotal() - 1) enemyState += "\n";
                    }
                }

                System.out.println(new Text("ENEMY (" + enemy.getName() + ") STATS (" + enemy.getName() + ")\n<red>Attack damage: " + enemy.getAttackDamage() + "\n<blue>Ability power: " + enemy.getAbilityPower() + "\n<yellow>Armor: " + enemy.getArmor() + "\n<cyan>Magic resist: " + enemy.getMagicResist() + "\n<purple>Penetration: " + enemy.getPenetration() + "\n<green>HP<white>:\n" + enemyState + "\n"));
            }

            prompt(enemy, shop, player, manager);
            return;
        } else if (split[0].equalsIgnoreCase("instructions")) {
            System.out.println(new Text("INSTRUCTIONS\n\n" +
                    "<!> HOW TO PLAY\nEvery turn, you can perform one of the following actions:\n1. <red>Attack<white>: Type <cyan>\"attack [enemy index]\"<white> to deal damage to the specified enemy.\n2. <blue>Cast an ability<white>: Type <cyan>\"cast [Q/W/E] [optional enemy index]\"<white> to cast one of your abilities on the specified enemy, or all alive enemies if the ability has an area of effect.\n3. <yellow>Dodge<white>: Type <cyan>\"dodge\"<white> to go into a dodge position, increasing your dodge rate and healing you if you successfully dodge.\n4. Shop: Type <cyan>\"shop\"<white> to view the item shop.\n5. Inventory: Type <cyan>\"inventory\"<white> to view your current item inventory.\n6. <green>Buy<white>: Type <cyan>\"buy [item index]\"<white> to buy the specified item from the shop.\n7. <green>Sell<white>: Type <cyan>\"sell [item index]\"<white> to sell the specified item in your inventory.\n8. <cyan>Stats<white>: Type <cyan>\"stats\"<white> to view detailed stats about your current Champion. Type <cyan>\"stats enemy\"<white> to view detailed stats about the current enemy.\n\n" +
                    "<!> ABILITIES\nYour Champion is equipped with powerful Abilities that can be cast in battle. However, be mindful of your abilities' <blue>Mana costs<white> and cooldowns. Each ability has a different <blue>Mana cost<white> and cooldown. To check, use the <cyan>\"stats\"<white> command.\n\n" +
                    "<!> DAMAGE\nYour Champion will deal and receive three types of damage: <red>physical<white>, <blue>magical<white>, and true. <red>Physical damage<white> scales with your <red>Attack Damage<white>. <blue>Magical damage<white> scales with your <blue>Ability Power<white>. True damage is absolute and has no scaling.\n\n" +
                    "<!> RESISTANCES AND PENETRATION\nYour Champion and enemies both have resistances that reduce the amount of incoming damage. <yellow>Armor<white> reduces the amount of incoming <red>physical damage<white>. <cyan>Magic resist<white> reduces the amount of incoming <blue>magical damage<white>. <purple>Penetration<white> ignores a portion of both <yellow>Armor<white> and <cyan>Magic resist<white>.\n\n" +
                    "<!> RESOURCES\nYour Champion has a series of resources that you need to carefully manage.\n<green>HP<white>: The amount of health points your Champion has left. If this value drops to 0, it is game over!\n<green>Max HP<white>: This is the maximum amount of health points your Champion can have. <green>Healing<white> is limited by this value.\n<blue>Mana<white>: This is one of the resources required to cast abilities.\n<yellow>Gold<white>: This is the currency required to buy Items.\n<green>HP<white>, <blue>Mana<white>, and <yellow>Gold<white> will be gained upon slaying an enemy.\n\n" +
                    "<!> SHOP AND ITEMS\nYou can purchase powerful Items from the shop. Items will give your Champion additional statistics to improve their damage, max HP, resistances, and dodge rate. <yellow>Gold<white> is required to purchase Items, and 70% of the <yellowGold<white> spent will be refunded upon selling an item. In addition, each item is unique; you cannot purchase more than one copy of each item.\n\n" +
                    "<!> CRESTS\nCrests are an additional buff for your Champion. Each Crest lasts for 3 waves.\n<red>Crest of Cinders<white>: You will deal <yellow>10%<white> of the target's current HP as true damage upon attacking. It can only be obtained by defeating the <red>Red Brambleback<white>.\n<blue>Crest of Insight<white>: You will use <yellow>10%<white> less <blue>Mana<white> upon casting an ability. You will also gain <yellow>5%<white> additional <blue>Mana<white> upon slaying an enemy. It can only be obtained from the <blue>Blue Sentinel<white>."));

            prompt(enemy, shop, player, manager);
            return;
        }

        if (enemy.getCount() > 0) {
            System.out.println(new Text("It is now <red>the enemy's <white>turn."));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println(new Text("<red>Failed to wait. Skipping wait time..."));
            }

            enemy.attack(player);

            if (player.getHp() <= 0) {
                System.out.println(new Text("<red>You died... Game over!"));
                manager.setWave(100);
                return;
            }

            prompt(enemy, shop, player, manager);
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

        if (manager.getWave() < 4) {
            random = randomNumber(1, 2);
        } else if (manager.getWave() >= 4 && manager.getWave() < 6) {
            random = randomNumber(3, 4);
        } else if (manager.getWave() >= 6 && manager.getWave() < 9) {
            random = randomNumber(5, 7);
        } else {
            random = randomNumber(1, 7);
        }

        return random;
    }

    public static int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}

class Manager {
    private int wave;

    public Manager() {
        this.wave = 0;
    }

    public int getWave() {
        return this.wave;
    }

    public void incrementWave() {
        this.wave++;
    }
    public void setWave(int wave) { this.wave = wave; }
}

class Biotic {
    private int speed, armor, magicResist, penetration, attackDamage, abilityPower;

    public Biotic(int speed, int armor, int magicResist, int penetration, int attackDamage, int abilityPower) {
        this.speed = speed;
        this.armor = armor;
        this.magicResist = magicResist;
        this.penetration = penetration;
        this.attackDamage = attackDamage;
        this.abilityPower = abilityPower;
    }

    public int getArmor() {
        return this.armor;
    }
    public int getMagicResist() {
        return this.magicResist;
    }
    public int getPenetration() {
        return this.penetration;
    }
    public int getAttackDamage() {
        return this.attackDamage;
    }
    public int getAbilityPower() {
        return this.abilityPower;
    }
    public int getSpeed() {
        return this.speed;
    }

    public void setAttackDamage(int attackDamage, int type) {
        if (type == 0) {
            this.attackDamage += attackDamage;
        } else if (type == 1) {
            this.attackDamage -= attackDamage;
        }
    }
    public void setAbilityPower(int abilityPower, int type) {
        if (type == 0) {
            this.abilityPower += abilityPower;
        } else if (type == 1) {
            this.abilityPower -= abilityPower;
        }
    }
    public void setArmor(int armor, int type) {
        if (type == 0) {
            this.armor += armor;
        } else if (type == 1) {
            this.armor -= armor;
        }
    }
    public void setMagicResist(int magicResist, int type) {
        if (type == 0) {
            this.magicResist += magicResist;
        } else if (type == 1) {
            this.magicResist -= magicResist;
        }
    }
    public void setPenetration(int penetration, int type) {
        if (type == 0) {
            this.penetration += penetration;
        } else if (type == 1) {
            this.penetration -= penetration;
        }
    }
    public void setSpeed(int speed, int type) {
        if (type == 0) {
            this.speed += speed;
        } else if (type == 1) {
            this.speed -= speed;
        }
    }
}

class Player extends Biotic {
    private int champId; // Yasuo: 1, Jinx: 2, Malphite: 3
    private int hp, maxHp, gold, mana, criticalChance;
    private Ability[] abilities = new Ability[3];
    private Item[] inventory = new Item[6];
    private Buff[] buffs = new Buff[2];
    private String name;
    private boolean dodging;

    public Player(int hp, int speed, int criticalChance, int armor, int magicResist, int penetration, int attackDamage, int abilityPower, int mana, int champId, String name) {
        super(speed, armor, magicResist, penetration, attackDamage, abilityPower);
        this.hp = hp;
        this.maxHp = hp;
        this.criticalChance = criticalChance;
        this.champId = champId;
        this.gold = 100;
        this.mana = mana;
        this.name = name;
        this.dodging = false;

        switch (this.champId) {
            case 1:
                abilities[0] = new Ability(75, 110, 1, 1, 0, 30, false, "Steel Tempest");
                abilities[1] = new Ability(50, 50, 2, 2, 20, 100, true, "Wind Wall");
                abilities[2] = new Ability(150, 120, 1, 3, 50, 0, true, "Galeforce");
                break;
            case 2:
                abilities[0] = new Ability(90, 100, 1, 1, 30, 0, false, "Zap!");
                abilities[1] = new Ability(50, 70, 2, 1,50, 40, true, "Flame Chompers");
                abilities[2] = new Ability(160, 150, 1, 3, 100, 10, true, "Super Mega Death Rocket");
                break;
            case 3:
                abilities[0] = new Ability(100, 120, 2, 1, 30, 50, false, "Seismic Shard");
                abilities[1] = new Ability(80, 90, 2, 2, 25, 20, true, "Thunderclap");
                abilities[2] = new Ability(160, 110, 2, 3, 70, 0, true, "Unstoppable Force");
                break;
            case 4:
                break;
            default:
                abilities[0] = new Ability(80, 110, 1, 1, 0, 30, false, "Steel Tempest");
                abilities[1] = new Ability(20, 50, 2, 2, 0, 100, true, "Wind Wall");
                abilities[2] = new Ability(150, 120, 1, 3, 50, 0, true, "Galeforce");
                break;
        }
    }

    public void attack(Enemy target, int i) {
        int random = (int) Math.floor(Math.random() * 100);
        int damage = this.getAttackDamage() - (int) (100 / (100 - (target.getArmor() * ((float) (100 - this.getPenetration()) / 100.0f))));
        int heal, mana, gold;

        if (this.buffs[0] != null && this.buffs[0].getType() == 1) {
            damage += (int) (0.1 * target.getHp()[i]);
        }
        
        damage = randomNumber((int) (damage * 0.9), (int) (damage * 1.1));

        if (random <= this.getCriticalChance()) {
            if (this.buffs[0] != null && this.buffs[0].getType() == 1) {
                System.out.println(new Text("You commanded " + this.name + " to attack, dealing <red>" + (int) (damage * 1.2) + " physical damage <yellow>(+" + (int) (damage * 1.2 - damage) + " critical damage) (+" + (int) (0.1 * target.getHp()[i]) + " Crest of Cinders damage)<white> to the " + (i + 1) + "th enemy."));
            } else {
                System.out.println(new Text("You commanded " + this.name + " to attack, dealing <red>" + (int) (damage * 1.2) + " physical damage <yellow>(+" + (int) (damage * 1.2 - damage) + " critical damage) <white>to the " + (i + 1) + "th enemy."));
            }
            
            target.setHp((int) (damage * 1.2), i, 1);
        } else {
            if (this.buffs[0] != null && this.buffs[0].getType() == 1) {
                System.out.println(new Text("You commanded " + this.name + " to attack, dealing <red>" + damage + " physical damage <yellow>(+" + (int) (0.1 * target.getHp()[i]) + " Crest of Cinders damage)<white> to the " + (i + 1) + "th enemy."));
            } else {
                System.out.println(new Text("You commanded " + this.name + " to attack, dealing <red>" + damage + " physical damage <white>to the " + (i + 1) + "th enemy."));
            }

            target.setHp(damage, i, 1);
        }

        if (target.getHp()[i] <= 0) {
            target.setCount(1);
            
            mana = randomNumber(20, 40);
            if (this.buffs[1] != null && this.buffs[1].getType() == 2) {
                mana = (int) (1.05 * mana);
            }
            this.mana += mana;
            
            if (this.hp < this.maxHp) {
                heal = randomNumber((int) ((2 * target.getCount() + 80) * 0.9), (int) ((2 * target.getCount() + 80) * 1.1));
                if (heal > this.maxHp - this.hp) heal = this.maxHp - this.hp;
                this.hp += heal;
            } else {
                heal = 0;
            }
            
            gold = randomNumber((int) (target.getReward() * 0.9), (int) (target.getReward() * 1.1));
            this.gold += gold;

            slaySingle(target, heal, mana, gold);
        }
    }

    public void cast(Enemy target, String id, int i) {
        int damage, heal, mana, cost, gold, hasSlain, hasDamaged;
        int index;
        String[] abilityIds = new String[]{"Q", "W", "E"};
        
        if (id.equalsIgnoreCase("q")) {
            index = 0;
        } else if (id.equalsIgnoreCase("w")) {
            index = 1;
        } else if (id.equalsIgnoreCase("e")) {
            index = 2;
        } else {
            index = 0;
        }

        this.abilities[index].setCurrentCooldown(abilities[index].getCooldown(), 0);
        
        if (this.buffs[1] != null && this.buffs[1].getType() == 2) {
            cost = (int) (0.8 * abilities[index].getMana());
        } else {
            cost = abilities[index].getMana();
        }

        this.mana -= cost;

        if (abilities[index].getType() == 1) {
            damage = abilities[index].getDamage(this.getAttackDamage()) - (int) (100 / (100 - (target.getArmor() * ((float) (100 - this.getPenetration()) / 100.0f))));
        } else if (abilities[index].getType() == 2) {
            damage = abilities[index].getDamage(this.getAbilityPower()) - (int) (100 / (100 - (target.getMagicResist() * ((float) (100 - this.getPenetration()) / 100.0f))));
        } else {
            damage = 0;
        }

        damage = randomNumber((int) (damage * 0.9), (int) (damage * 1.1));

        if (!abilities[index].isAoe()) {
            target.setHp(damage, i, 1);

            if (abilities[index].getVamp() == 0 || this.hp >= this.maxHp) {
                if (abilities[index].getType() == 1) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to the " + (i + 1) + "th enemy and consuming <blue>" + cost + " Mana<white>."));
                } else if (abilities[index].getType() == 2) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to the " + (i + 1) + "th enemy and consuming <blue>" + cost + " Mana<white>."));
                }
            } else {
                heal = (int) (damage * ((float) (abilities[index].getVamp()) / 100.0f));
                if (heal > this.maxHp - this.hp) heal = this.maxHp - this.hp;
                this.hp += heal;

                if (abilities[index].getType() == 1) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to the " + (i + 1) + "th enemy and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                } else if (abilities[index].getType() == 2) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to the " + (i + 1) + "th enemy and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                }
            }

            if (target.getHp()[i] <= 0) {
                target.setCount(1);

                mana = randomNumber(20, 40);
                this.mana += mana;

                if (this.hp < this.maxHp) {
                    heal = randomNumber((int) ((2 * target.getCount() + 80) * 0.9), (int) ((2 * target.getCount() + 80) * 1.1));
                    if (heal > this.maxHp - this.hp) heal = this.maxHp - this.hp;
                    this.hp += heal;
                } else {
                    heal = 0;
                }

                gold = randomNumber((int) (target.getReward() * 0.9), (int) (target.getReward() * 1.1));
                this.gold += gold;

                slaySingle(target, heal, mana, gold);
            }
        } else {
            hasDamaged = 0;

            for (i = 0; i < target.getTotal(); i++) {
                if (target.getHp()[i] > 0) hasDamaged++;
            }

            if (abilities[index].getVamp() == 0 || this.hp >= this.maxHp) {
                if (abilities[index].getType() == 1) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>."));
                } else if (abilities[index].getType() == 2) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>."));
                }
            } else {
                heal = (int) (damage * ((float) (abilities[index].getVamp()) / 100.0f)) * hasDamaged;
                if (heal > this.maxHp - this.hp) heal = this.maxHp - this.hp;
                this.hp += heal;

                if (abilities[index].getType() == 1) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                } else if (abilities[index].getType() == 2) {
                    System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                }
            }

            hasSlain = 0;
            mana = 0;
            gold = 0;
            heal = 0;

            if (this.hp < this.maxHp) {
                for (i = 0; i < target.getTotal(); i++) {
                    if (target.getHp()[i] > 0) {
                        target.setHp(damage, i, 1);

                        if (target.getHp()[i] <= 0) {
                            hasSlain++;
                            target.setCount(1);
                            mana += randomNumber(20, 40);
                            heal += randomNumber((int) ((2 * target.getCount() + 80) * 0.9), (int) ((2 * target.getCount() + 80) * 1.1));
                            gold += randomNumber((int) (target.getReward() * 0.9), (int) (target.getReward() * 1.1));
                        }
                    }
                }

                if (heal > this.maxHp - this.hp) heal = this.maxHp - this.hp;
            } else {
                for (i = 0; i < target.getTotal(); i++) {
                    if (target.getHp()[i] > 0) {
                        hasDamaged++;
                        target.setHp(damage, i, 1);

                        if (target.getHp()[i] <= 0) {
                            hasSlain++;
                            target.setCount(1);
                            mana += randomNumber(20, 40);
                            gold += randomNumber((int) (target.getReward() * 0.9), (int) (target.getReward() * 1.1));
                        }
                    }
                }
            }

            if (hasSlain > 0) {
                if (heal > 0) this.hp += heal;
                this.mana += mana;
                this.gold += gold;

                slayMulti(target, hasSlain, heal, mana, gold);
            }
        }
    }

    private int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
    private void slaySingle(Enemy target, int heal, int mana, int gold) {
        if (heal > 0) {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain one of the " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>5%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>max HP<white> and <yellow>5% Penetration<white>."));
                        break;
                }
            }
        } else {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain one of the " + target.getName() + "! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>5%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>max HP<white> and <yellow>5% Penetration<white>."));
                        break;
                }
            }
        }
    }
    private void slayMulti(Enemy target, int hasSlain, int heal, int mana, int gold) {
        if (heal > 0) {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain <yellow>" + hasSlain + " <white>enemies with this ability! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow> " + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>5%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>HP<white> and <yellow>5% Penetration<white>."));
                        break;
                }
            }
        } else {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain <yellow>" + hasSlain + " <white>enemies with this ability! You regained <blue>" + mana + " Mana<white>, and <yellow> " + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>5%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "! You regained <blue>" + mana + " Mana<white>, and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>HP<white> and <yellow>5% Penetration<white>."));
                        break;
                }
            }
        }
    }

    public void dodge() {
        this.dodging = true;

        System.out.println(new Text("You are now in <cyan>dodge <white>position. You cannot attack for this turn, but you have an increased dodge rate for this turn and will heal a portion of your HP for every enemy dodged."));
    }

    public int getHp() {
        return this.hp;
    }
    public int getMaxHp() {
        return this.maxHp;
    }
    public int getMana() {
        return this.mana;
    }
    public int getCriticalChance() {
        return this.criticalChance;
    }
    public Ability[] getAbilities() {
        return this.abilities;
    }
    public Item[] getInventory() {
        return this.inventory;
    }
    public Buff[] getBuffs() {
        return this.buffs;
    }
    public boolean isDodging() {
        return this.dodging;
    }
    public int getGold() { return this.gold; }
    public String getName() { return this.name; }

    public void setHp(int hp, int type) {
        if (type == 0) {
            this.hp += hp;
        } else if (type == 1) {
            this.hp -= hp;
        }
    }
    public void setMaxHp(int maxHp, int type) {
        if (type == 0) {
            this.maxHp += maxHp;
        } else if (type == 1) {
            this.maxHp -= maxHp;
        }
    }
    public void setGold(int gold, int type) {
        if (type == 0) {
            this.gold += gold;
        } else if (type == 1) {
            this.gold -= gold;
        }
    }
    public void setMana(int mana, int type) {
        if (type == 0) {
            this.mana += mana;
        } else if (type == 1) {
            this.mana -= mana;
        }
    }
    public void setCriticalChance(int criticalChance, int type) {
        if (type == 0) {
            this.criticalChance += criticalChance;
        } else if (type == 1) {
            this.criticalChance -= criticalChance;
        }
    }
    public void setBuff(Buff buff, int index) {
        this.buffs[index] = buff;
    }
    public void setDodging(boolean dodging) { this.dodging = dodging; }
    public void setInventory(int index, Item item) {
        this.inventory[index] = item;
    }
}

class Enemy extends Biotic {
    private int[] hp;
    private int type; // AD: 1, AP: 2, Both: 3
    private int count, total;
    private int id; // Raptors: 1, Gromp: 2, Red: 3, Blue: 4, Infernal: 5, Mountain: 6, Ocean: 7, Kayn: 8
    private String name;
    private int reward;

    public Enemy(int[] hp, int speed, int armor, int magicResist, int penetration, int attackDamage, int abilityPower, int type, int count, int id, String name, int reward) {
        super(speed, armor, magicResist, penetration, attackDamage, abilityPower);
        this.hp = hp;
        this.type = type;
        this.count = count;
        this.total = count;
        this.id = id;
        this.name = name;
        this.reward = reward;
    }

    public void attack(Player target) {
        int random = (int) Math.floor(Math.random() * 100);
        int damage;

        if (!target.isDodging()) {
            if (random > target.getSpeed() * 0.03) {
                if (this.type == 1) {
                    damage = (this.getAttackDamage() - (100 / (100 - (target.getArmor() * ((100 - this.getPenetration()) / 100))))) * this.count;
                    damage = randomNumber((int) (damage * 0.9), (int) (damage * 1.1));
                    target.setHp(damage, 1);
                    System.out.println(new Text("You took <red>" + damage + " physical damage<white> from the " + this.name + ". It is now <green>your <white>turn."));
                } else if (this.type == 2) {
                    damage = (this.getAbilityPower() - (100 / (100 - (target.getMagicResist() * ((100 - this.getPenetration()) / 100))))) * this.count;
                    damage = randomNumber((int) (damage * 0.9), (int) (damage * 1.1));
                    target.setHp(damage, 1);
                    System.out.println(new Text("You took <cyan>" + damage + " magical damage<white> from the " + this.name + ". It is now <green>your <white>turn."));
                }
            } else {
                System.out.println(new Text("You managed to <yellow>dodge<white> the enemy's attacks! It is now <green>your <white>turn."));
            }
        } else {
            if (random > target.getSpeed() * 0.03 + 50) {
                if (this.type == 1) {
                    damage = (this.getAttackDamage() - (100 / (100 - (target.getArmor() * ((100 - this.getPenetration()) / 100))))) * this.count;
                    damage = randomNumber((int) (damage * 0.9), (int) (damage * 1.1));
                    target.setHp(damage, 1);
                    System.out.println(new Text("You took <red>" + damage + " physical damage<white> from the " + this.name + ". It is now <green>your <white>turn."));
                } else if (this.type == 2) {
                    damage = (this.getAbilityPower() - (100 / (100 - (target.getMagicResist() * ((100 - this.getPenetration()) / 100))))) * this.count;
                    damage = randomNumber((int) (damage * 0.9), (int) (damage * 1.1));
                    target.setHp(damage, 1);
                    System.out.println(new Text("You took <cyan>" + damage + " magical damage<white> from the " + this.name + ". It is now <green>your <white>turn."));
                }
            } else {
                target.setHp(25 * this.count, 0);
                System.out.println(new Text("You managed to <yellow>dodge<white> the enemy's attacks! You also restored <green>" + (25 * this.count) + " HP <white>for doing so. It is now <green>your <white>turn."));
            }
        }
    }

    private int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public int[] getHp() { return this.hp; }
    public int getCount() { return this.count; }
    public int getTotal() { return this.total; }
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getReward() { return this.reward; }

    public void setCount(int count) {
        this.count -= count;
    }
    public void setHp(int hp, int index, int type) {
        if (type == 0) {
            this.hp[index] += hp;
        } else if (type == 1) {
            this.hp[index] -= hp;
        }
    }
}

class Kayn extends Enemy {
    private Ability[] abilities = new Ability[3];

    public Kayn(int[] hp, int speed, int armor, int magicResist, int penetration, int attackDamage, int abilityPower, int type, int id, String name, int reward) {
        super(hp, speed, armor, magicResist, penetration, attackDamage, abilityPower, type, 1, id, name, reward);
    }
}

class Ability {
    private int baseDamage, scaling, type, cooldown, mana, vamp, currentCooldown = 0;
    private boolean aoe;
    private String name;

    public Ability(int baseDamage, int scaling, int type, int cooldown, int mana, int vamp, boolean aoe, String name) {
        this.baseDamage = baseDamage;
        this.scaling = scaling;
        this.type = type; // AD: 1, AP: 2
        this.cooldown = cooldown + 1;
        this.mana = mana;
        this.vamp = vamp;
        this.aoe = aoe;
        this.name = name;
    }

    public int getDamage(int adOrAp) {
        return (int) (this.baseDamage + (adOrAp * ((float) (this.scaling) / 100.0f)));
    }
    public int getBaseDamage() {
        return this.baseDamage;
    }
    public int getScaling() {
        return this.scaling;
    }
    public int getType() {
        return this.type;
    }
    public int getCooldown() {
        return this.cooldown;
    }
    public int getMana() {
        return this.mana;
    }
    public int getVamp() { return this.vamp; }
    public int getCurrentCooldown() {
        return this.currentCooldown;
    }
    public boolean isAoe() {
        return this.aoe;
    }
    public String getName() { return this.name; }

    public void setCurrentCooldown(int cooldown, int type) { // 0: =, 1: -=
        if (type == 0) {
            this.currentCooldown = cooldown;
        } else if (type == 1) {
            this.currentCooldown -= cooldown;
        }
    }
}

class Buff {
    private int type, wavesLeft;

    public Buff(int type) { // Red: 1, Blue: 2
        this.type = type;
        this.wavesLeft = 3;
    }

    public int getType() {
        return this.type;
    }
    public int getWavesLeft() {
        return this.wavesLeft;
    }
    public void decrementWaves() { this.wavesLeft--; }
    public void setWavesLeft(int wavesLeft) { this.wavesLeft = wavesLeft; }
}

class Item {
    private int cost;

    /*
    STARTER
    * Doran's Blade: 1
    * Doran's Ring: 2
    * Doran's Shield: 3

    FIGHTER
    * Divine Sunderer: 4

    ASSASSIN
    * Duskblade: 5

    MAGE:
    * Everfrost: 6

    BOOTS
    * Berserker's Greaves: 7
    * Sorcerer's Shoes: 8
     */
    private int id;
    private String name, recommended, stats;
    private boolean purchased;

    public Item(int id, int cost, String name, String recommended, String stats) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.recommended = recommended;
        this.stats = stats;
        this.purchased = false;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getCost() {
        return this.cost;
    }
    public String getRecommended() { return this.recommended; }
    public String getStats() { return this.stats; }
    public boolean isPurchased() { return this.purchased; }

    public void setPurchased(boolean purchased) { this.purchased = purchased; }
}

class Shop {
    private Item[] shop = new Item[8];

    public Shop() {
        shop[0] = new Item(1, 400, "Doran's Blade", "<yellow>Yasuo<white>, <green>Jinx", "<red>25 AD<white>, <green>80 Max HP<white>");
        shop[1] = new Item(2, 400, "Doran's Ring", "<red>Malphite", "<blue>35 AP<white>, <green>75 Max HP<white>");
        shop[2] = new Item(3, 400, "Doran's Shield", "All", "<yellow>15 Armor<white>, <cyan>15 MR<white>, <green>100 Max HP");
        shop[3] = new Item(4, 1000, "Divine Sunderer", "<yellow>Yasuo", "<red>55 AD<white>, <red>60% Crit<white>, <green>200 Max HP<white>");
        shop[4] = new Item(5, 1000, "Duskblade", "<green>Jinx", "<red>90 AD<white>, <purple>40% Penetration<white>, <green>110 Max HP<white>");
        shop[5] = new Item(6, 1000, "Everfrost", "<red>Malphite", "<blue>100 AP<white>, <purple>25% Penetration<white>, <green>150 HP<white>");
        shop[6] = new Item(7, 650, "Berserker's Greaves", "<yellow>Yasuo<white>, <green>Jinx", "45 Speed, <red>35 AD<white>, <purple>5% Penetration");
        shop[7] = new Item(8, 650, "Sorcerer's Shoes", "<red>Malphite", "50 Speed, <blue>45 AP<white>, <purple>8% Penetration");
    }

    public int getLength() {
        return shop.length;
    }
    public Item[] getItems() { return shop; }
    
    public void buy(Player target, int id) {
        target.setGold(this.shop[id - 1].getCost(), 1);
        shop[id - 1].setPurchased(true);

        int i;
        for (i = 0; i < target.getInventory().length; i++) {
            if (target.getInventory()[i] == null) {
                target.setInventory(i, shop[id - 1]);
                break;
            }
        }

        switch (id) {
            case 1:
                target.setAttackDamage(25, 0);
                target.setMaxHp(80, 0);
                break;
            case 2:
                target.setAbilityPower(35, 0);
                target.setMaxHp(75, 0);
                break;
            case 3:
                target.setArmor(15, 0);
                target.setMagicResist(15, 0);
                target.setMaxHp(100, 0);
                break;
            case 4:
                target.setAttackDamage(55, 0);
                target.setCriticalChance(60, 0);
                target.setMaxHp(200, 0);
                break;
            case 5:
                target.setAttackDamage(90, 0);
                target.setPenetration(40, 0);
                target.setMaxHp(110, 0);
                break;
            case 6:
                target.setAbilityPower(100, 0);
                target.setPenetration(25, 0);
                target.setMaxHp(150, 0);
                break;
            case 7:
                target.setSpeed(45, 0);
                target.setAttackDamage(35, 0);
                target.setPenetration(5, 0);
                break;
            case 8:
                target.setSpeed(50, 0);
                target.setAbilityPower(45, 0);
                target.setPenetration(8, 0);
                break;
        }

        System.out.println(new Text("You have successfully purchased a <cyan>" + this.shop[id - 1].getName() + " <white>for <yellow>" + this.shop[id - 1].getCost() + " Gold<white>. You now have <yellow>" + target.getGold() + " Gold<white>."));
    }

    public void sell(Player target, int id) {
        target.setGold((int) (this.shop[id - 1].getCost() * 0.7), 0);
        shop[id - 1].setPurchased(false);

        int i;
        for (i = 0; i < target.getInventory().length; i++) {
            if (target.getInventory()[i] != null && target.getInventory()[i].getId() == id) {
                target.setInventory(i, null);
            }
        }

        switch (id) {
            case 1:
                target.setAttackDamage(25, 1);
                target.setMaxHp(80, 1);
                break;
            case 2:
                target.setAbilityPower(35, 1);
                target.setMaxHp(75, 1);
                break;
            case 3:
                target.setArmor(15, 1);
                target.setMagicResist(15, 1);
                target.setMaxHp(100, 1);
                break;
            case 4:
                target.setAttackDamage(55, 1);
                target.setCriticalChance(60, 1);
                target.setMaxHp(200, 1);
                break;
            case 5:
                target.setAttackDamage(90, 1);
                target.setPenetration(40, 1);
                target.setMaxHp(110, 0);
                break;
            case 6:
                target.setAbilityPower(100, 1);
                target.setPenetration(25, 1);
                target.setMaxHp(150, 1);
                break;
            case 7:
                target.setSpeed(45, 1);
                target.setAttackDamage(35, 1);
                target.setPenetration(5, 1);
                break;
            case 8:
                target.setSpeed(50, 1);
                target.setAbilityPower(45, 1);
                target.setPenetration(8, 1);
                break;
        }

        System.out.println(new Text("You have successfully sold your <cyan>" + this.shop[id - 1].getName() + " <white>for <yellow>" + (int) (this.shop[id - 1].getCost() * 0.7) + " Gold<white>. You now have <yellow>" + target.getGold() + " Gold<white>."));
    }
}

class Text {
    private String text;

    public Text(String text) {
        this.text = "\u001B[0m" + text;
    }

    public String toString() {
        return text.replaceAll("<white>", "\u001B[0m").replaceAll("<red>", "\u001B[31m").replaceAll("<green>", "\u001B[32m").replaceAll("<blue>", "\u001B[34m").replaceAll("<cyan>", "\u001B[36m").replaceAll("<yellow>", "\u001B[33m").replaceAll("<purple>", "\u001B[35m");
    }
}