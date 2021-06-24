package OdysseyBattle;

public class Player extends Biotic {
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
        this.gold = 200;
        this.mana = mana;
        this.name = name;
        this.dodging = false;

        switch (this.champId) {
            case 1:
                abilities[0] = new Ability(75, 110, 1, 1, 0, 30, false, false, "Steel Tempest");
                abilities[1] = new Ability(50, 50, 2, 2, 20, 100, true, false, "Wind Wall");
                abilities[2] = new Ability(150, 120, 1, 3, 50, 0, true, false, "Galeforce");
                break;
            case 2:
                abilities[0] = new Ability(90, 95, 1, 1, 5, 20, false, false, "Zap!");
                abilities[1] = new Ability(45, 70, 2, 1,30, 50, true, true, "Flame Chompers");
                abilities[2] = new Ability(160, 150, 1, 3, 55, 30, true, false, "Super Mega Death Rocket");
                break;
            case 3:
                abilities[0] = new Ability(100, 120, 2, 1, 30, 70, false, false, "Seismic Shard");
                abilities[1] = new Ability(80, 90, 2, 2, 20, 20, true, false, "Thunderclap");
                abilities[2] = new Ability(160, 110, 2, 3, 70, 0, true, true, "Unstoppable Force");
                break;
            case 4:
                break;
            default:
                abilities[0] = new Ability(75, 110, 1, 1, 0, 30, false, false, "Steel Tempest");
                abilities[1] = new Ability(50, 50, 2, 2, 20, 100, true, false, "Wind Wall");
                abilities[2] = new Ability(150, 120, 1, 3, 50, 0, true, false, "Galeforce");
                break;
        }
    }

    public void attack(Enemy target, int i) {
        int random = randomNumber(1, 100);
        int damage = this.getAttackDamage() - (int) (100 / (100 - (target.getArmor() * ((float) (100 - this.getPenetration()) / 100.0f))));
        int heal, mana, gold;

        if (target.getSpeed() == 0 || random > target.getSpeed() * 0.03) {
            if (this.buffs[0] != null && this.buffs[0].getType() == 1) {
                damage += (int) (0.1 * target.getHp()[i]);
            }

            damage = randomNumber((int) (damage * 0.9), (int) (damage * 1.1));

            random = randomNumber(1, 100);

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
                    mana = (int) (1.15 * mana);
                }
                this.mana += mana;

                if (this.hp < this.maxHp) {
                    heal = randomNumber((int) ((2 * target.getCount() + 80) * 0.9), (int) ((2 * target.getCount() + 80) * 1.1));
                    if (this.buffs[0] != null && this.buffs[0].getType() == 1) heal = (int) (heal * 1.3);
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
            System.out.println(new Text("The enemy managed to <yellow>dodge<white> your attack!"));
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

        if (abilities[index].isImmobilizing()) {
            target.setImmobilized(true);
        }

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
                if (this.buffs[1] != null && this.buffs[1].getType() == 2) {
                    mana = (int) (1.15 * mana);
                }
                this.mana += mana;

                if (this.hp < this.maxHp) {
                    heal = randomNumber((int) ((2 * target.getCount() + 80) * 0.9), (int) ((2 * target.getCount() + 80) * 1.1));
                    if (this.buffs[0] != null && this.buffs[0].getType() == 1) heal = (int) (heal * 1.3);
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
                    if (!abilities[index].isImmobilizing()) {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>."));
                    } else {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to and <purple>immobilizing<white> all alive enemies and consuming <blue>" + cost + " Mana<white>."));
                    }
                } else if (abilities[index].getType() == 2) {
                    if (!abilities[index].isImmobilizing()) {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>."));
                    } else {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to and <purple>immobilizing<white> all alive enemies and consuming <blue>" + cost + " Mana<white>."));
                    }
                }
            } else {
                heal = (int) (damage * ((float) (abilities[index].getVamp()) / 100.0f)) * hasDamaged;
                if (heal > this.maxHp - this.hp) heal = this.maxHp - this.hp;
                this.hp += heal;

                if (abilities[index].getType() == 1) {
                    if (!abilities[index].isImmobilizing()) {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                    } else {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <red>" + damage + " physical damage <white>to and <purple>immobilizing<white> all alive enemies and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                    }
                } else if (abilities[index].getType() == 2) {
                    if (!abilities[index].isImmobilizing()) {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to all alive enemies and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                    } else {
                        System.out.println(new Text("You commanded " + this.name + " to cast their " + abilityIds[index] + " ability (" + abilities[index].getName() + "), dealing <blue>" + damage + " magical damage <white>to and <purple>immobilizing<white> all alive enemies and consuming <blue>" + cost + " Mana<white>. You also healed for <green>" + abilities[index].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                    }
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

                if (this.buffs[0] != null && this.buffs[0].getType() == 1) heal = (int) (heal * 1.3);
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

            if (this.buffs[1] != null && this.buffs[1].getType() == 2) {
                mana = (int) (1.15 * mana);
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
        int i;

        if (heal > 0) {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain one of the " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, you will restore an extra <yellow>30%<white> <green>HP<white> on enemy takedown, and your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as true damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>15%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>max HP<white> and <yellow>5% Penetration<white>."));
                        break;
                    case 8:
                        this.setSpeed((int) (0.15 * this.getSpeed()), 0);
                        this.maxHp += (int) (1.2 * this.getMaxHp());

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(9, 0, "Eye of the Herald", "All", "15% Speed, <green>30% Max HP<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Eye of the Herald<white>! You gained 15% Speed and <green>30% Max HP<white>."));
                        break;
                    case 9:
                        this.setSpeed((int) (0.2 * this.getSpeed()), 0);
                        this.setCriticalChance(20, 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(10, 0, "Aspect of the Dragon", "All", "20% Speed, <red>20% Crit<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <cyan>Aspect of the Dragon<white>! You gained 20% Speed and <red>20% Critical Strike Chance<white>."));
                        break;
                    case 10:
                        this.setAttackDamage((int) (0.15 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.15 * this.getAbilityPower()), 0);
                        this.setArmor((int) (0.2 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.2 * this.getMagicResist()), 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(11, 0, "Hand of Baron", "All", "<red>15% AD<white>, <blue>15% AP<white>, <yellow>20% Armor<white>, <cyan>20% MR<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Hand of Baron<white>! You gained <red>15% Attack Damage<white>, <blue>15% Ability Power<white>, <yellow>20% Armor<white>, and <cyan>20% Magic Resist<white>."));
                        break;
                }
            }
        } else {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain one of the " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, you will restore an extra <yellow>30%<white> <green>HP<white> on enemy takedown, and your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as true damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>15%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>max HP<white> and <yellow>5% Penetration<white>."));
                        break;
                    case 8:
                        this.setSpeed((int) (0.15 * this.getSpeed()), 0);
                        this.maxHp += (int) (1.2 * this.getMaxHp());

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(9, 0, "Eye of the Herald", "All", "15% Speed, <green>30% Max HP<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Eye of the Herald<white>! You gained 15% Speed and <green>30% Max HP<white>."));
                        break;
                    case 9:
                        this.setSpeed((int) (0.2 * this.getSpeed()), 0);
                        this.setCriticalChance(20, 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(10, 0, "Aspect of the Dragon", "All", "20% Speed, <red>20% Crit<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <cyan>Aspect of the Dragon<white>! You gained 20% Speed and <red>20% Critical Strike Chance<white>."));
                        break;
                    case 10:
                        this.setAttackDamage((int) (0.15 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.15 * this.getAbilityPower()), 0);
                        this.setArmor((int) (0.2 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.2 * this.getMagicResist()), 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(11, 0, "Hand of Baron", "All", "<red>15% AD<white>, <blue>15% AP<white>, <yellow>20% Armor<white>, <cyan>20% MR<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Hand of Baron<white>! You gained <red>15% Attack Damage<white>, <blue>15% Ability Power<white>, <yellow>20% Armor<white>, and <cyan>20% Magic Resist<white>."));
                        break;
                }
            }
        }
    }
    private void slayMulti(Enemy target, int hasSlain, int heal, int mana, int gold) {
        int i;

        if (heal > 0) {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain <yellow>" + hasSlain + " <white>enemies with this ability! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, you will restore an extra <yellow>30%<white> <green>HP<white> on enemy takedown, and your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as true damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>15%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>HP<white> and <yellow>5% Penetration<white>."));
                        break;
                    case 8:
                        this.setSpeed((int) (0.15 * this.getSpeed()), 0);
                        this.maxHp += (int) (1.2 * this.getMaxHp());

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(9, 0, "Eye of the Herald", "All", "15% Speed, <green>30% Max HP<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Eye of the Herald<white>! You gained 15% Speed and <green>30% Max HP<white>."));
                        break;
                    case 9:
                        this.setSpeed((int) (0.2 * this.getSpeed()), 0);
                        this.setCriticalChance(20, 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(10, 0, "Aspect of the Dragon", "All", "20% Speed, <red>20% Crit<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <cyan>Aspect of the Dragon<white>! You gained 20% Speed and <red>20% Critical Strike Chance<white>."));
                        break;
                    case 10:
                        this.setAttackDamage((int) (0.15 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.15 * this.getAbilityPower()), 0);
                        this.setArmor((int) (0.2 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.2 * this.getMagicResist()), 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(11, 0, "Hand of Baron", "All", "<red>15% AD<white>, <blue>15% AP<white>, <yellow>20% Armor<white>, <cyan>20% MR<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <green>" + heal + " HP<white>, <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Hand of Baron<white>! You gained <red>15% Attack Damage<white>, <blue>15% Ability Power<white>, <yellow>20% Armor<white>, and <cyan>20% Magic Resist<white>."));
                        break;
                }
            }
        } else {
            if (target.getId() < 3) {
                System.out.println(new Text("You have slain <yellow>" + hasSlain + " <white>enemies with this ability! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>."));
            } else {
                switch (target.getId()) {
                    case 3:
                        this.buffs[0] = new Buff(1);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Crest of Cinders<white>! For the next 3 waves, you will restore an extra <yellow>30%<white> <green>HP<white> on enemy takedown, and your attacks will deal an extra <yellow>10%<white> of the target's <green>current HP<white> as true damage."));
                        break;
                    case 4:
                        this.buffs[1] = new Buff(2);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <blue>Crest of Insight<white>! For the next 3 waves, you will restore an extra <yellow>15%<white> <blue>Mana<white> on enemy takedown, and your abilities will use <yellow>20%<white> less <blue>Mana<white>."));
                        break;
                    case 5:
                        this.setAttackDamage((int) (0.25 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.25 * this.getAbilityPower()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <red>Fire Insignia<white>! You gained <yellow>25%<white> <red>attack damage<white> and <blue>ability power<white>."));
                        break;
                    case 6:
                        this.setArmor((int) (0.25 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.25 * this.getMagicResist()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <yellow>Mountain Insignia<white>! You gained <yellow>25%<white> <yellow>armor<white> and <cyan>magic resist<white>."));
                        break;
                    case 7:
                        this.maxHp = (int) (1.25 * this.getMaxHp());
                        this.setPenetration((int) (0.05 * this.getPenetration()), 0);
                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <green>Ocean Insignia<white>! You gained <yellow>25%<white> <green>HP<white> and <yellow>5% Penetration<white>."));
                        break;
                    case 8:
                        this.setSpeed((int) (0.15 * this.getSpeed()), 0);
                        this.maxHp += (int) (1.2 * this.getMaxHp());

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(9, 0, "Eye of the Herald", "All", "15% Speed, <green>30% Max HP<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Eye of the Herald<white>! You gained 15% Speed and <green>30% Max HP<white>."));
                        break;
                    case 9:
                        this.setSpeed((int) (0.2 * this.getSpeed()), 0);
                        this.setCriticalChance(20, 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(10, 0, "Aspect of the Dragon", "All", "20% Speed, <red>20% Crit<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <cyan>Aspect of the Dragon<white>! You gained 20% Speed and <red>20% Critical Strike Chance<white>."));
                        break;
                    case 10:
                        this.setAttackDamage((int) (0.15 * this.getAttackDamage()), 0);
                        this.setAbilityPower((int) (0.15 * this.getAbilityPower()), 0);
                        this.setArmor((int) (0.2 * this.getArmor()), 0);
                        this.setMagicResist((int) (0.2 * this.getMagicResist()), 0);

                        for (i = 0; i < this.getInventory().length; i++) {
                            if (this.getInventory()[i] == null) {
                                this.getInventory()[i] = new Item(11, 0, "Hand of Baron", "All", "<red>15% AD<white>, <blue>15% AP<white>, <yellow>20% Armor<white>, <cyan>20% MR<white>", false);
                                break;
                            }
                        }

                        System.out.println(new Text("You have slain a " + target.getName() + "<white>! You regained <blue>" + mana + " Mana<white> and <yellow>" + gold + " Gold<white>.\n<!> You also gained the <purple>Hand of Baron<white>! You gained <red>15% Attack Damage<white>, <blue>15% Ability Power<white>, <yellow>20% Armor<white>, and <cyan>20% Magic Resist<white>."));
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
