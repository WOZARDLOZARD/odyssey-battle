package OdysseyBattle;

public class Kayn extends Enemy {
    private Ability[] abilities = new Ability[3];
    private int criticalChance, mana;
    private int maxHp;

    public Kayn(int[] hp, int speed, int armor, int magicResist, int penetration, int attackDamage, int abilityPower, int criticalChance, int mana, int type, int id, String name, int reward) {
        super(hp, speed, armor, magicResist, penetration, attackDamage, abilityPower, type, 1, id, name, reward);
        this.maxHp = hp[0];
        this.criticalChance = criticalChance;
        this.mana = mana;

        abilities[0] = new Ability(80, 90, 1, 1, 10, 50, false, false, "Reaping Slash");
        abilities[1] = new Ability(90, 100, 2, 2, 30, 0, false, false, "Blade's Reach");
        abilities[2] = new Ability(0, 0, 1, 3, 60, 0, false, false, "Shadow Infusion");
    }

    public void choose(Player target) {
        int random;

        if ((this.getHp()[0] * 100.0f) / this.getMaxHp() > 0.6) {
            if (abilities[0].getCurrentCooldown() == 0 && this.mana >= abilities[0].getMana() || abilities[1].getCurrentCooldown() == 0 && this.mana >= abilities[1].getMana()) {
                random = randomNumber(1, 3);
            } else {
                random = 1;
            }
        } else {
            if (abilities[0].getCurrentCooldown() == 0 && this.mana >= abilities[0].getMana() || abilities[1].getCurrentCooldown() == 0 && this.mana >= abilities[1].getMana() || abilities[2].getCurrentCooldown() == 0 && this.mana >= abilities[2].getMana()) {
                random = randomNumber(1, 4);
            } else {
                random = 1;
            }
        }

        if (random == 1) {
            this.attack(target);
        } else {
            this.cast(target);
        }
    }

    public void cast(Player target) {
        int random, damage, heal;

        if ((float) (this.getHp()[0]) / (float) (this.maxHp) > 0.6) {
            random = randomNumber(1, 2);
        } else {
            random = randomNumber(1, 6);
        }

        if (random == 1) {
            random = 0;
        } else if (random == 2) {
            random = 1;
        } else {
            random = 2;
        }

        while (abilities[random].getCurrentCooldown() > 0 || this.mana < abilities[random].getMana()) {
            if ((float) (this.getHp()[0]) / (float) (this.maxHp) > 0.6) {
                random = randomNumber(1, 2);
            } else {
                random = randomNumber(1, 6);
            }

            if (random == 1) {
                random = 0;
            } else if (random == 2) {
                random = 1;
            } else {
                random = 2;
            }
        }

        this.mana -= abilities[random].getMana();
        abilities[random].setCurrentCooldown(abilities[random].getCooldown(), 0);

        switch (random) {
            case 0:
                damage = abilities[0].getDamage(this.getAttackDamage()) - (int) (100 / (100 - (target.getArmor() * ((float) (100 - this.getPenetration()) / 100.0f))));
                damage = randomNumber((int) (0.9 * damage), (int) (1.1 * damage));

                target.setHp(damage, 1);

                if (this.getHp()[0] < this.maxHp) {
                    heal = (int) (damage * ((float) (abilities[0].getVamp()) / 100.0f));
                    if (heal > this.maxHp - this.getHp()[0]) heal = this.maxHp - this.getHp()[0];
                    this.setHp(heal, 0, 0);

                    System.out.println(new Text("<red>Kayn<white> cast their Q ability (" + abilities[0].getName() + "), dealing <red>" + damage + " physical damage<white> to you and consuming <blue>" + abilities[0].getMana() + " Mana<white>. They also healed themselves for <green>" + abilities[0].getVamp() + "% of the damage dealt (" + heal + " HP)<white>."));
                } else {
                    System.out.println(new Text("<red>Kayn<white> cast their Q ability (" + abilities[0].getName() + "), dealing <red>" + damage + " physical damage<white> to you and consuming <blue>" + abilities[0].getMana() + " Mana<white>."));
                }

                break;
            case 1:
                damage = abilities[1].getDamage(this.getAbilityPower()) - (int) (100 / (100 - (target.getMagicResist() * ((float) (100 - this.getPenetration()) / 100.0f))));
                target.setHp(damage, 1);

                System.out.println(new Text("<red>Kayn<white> cast their W ability (" + abilities[1].getName() + "), dealing <blue>" + damage + " magical damage<white> to you and consuming <blue>" + abilities[0].getMana() + " Mana<white>."));

                break;
            case 2:
                heal = (int) (0.3 * (this.maxHp - this.getHp()[0]));
                this.setHp(heal, 0, 0);

                System.out.println(new Text("<red>Kayn<white> cast their E ability (" + abilities[2].getName() + "), healing themselves for <green>30% of their current missing HP (" + heal + " HP)<white> and consuming <blue>" + abilities[2].getMana() + " Mana<white>."));

                break;
        }
    }

    private static int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public int getMaxHp() { return this.maxHp; }
    public int getMana() { return this.mana; }
    public int getCriticalChance() { return this.criticalChance; }
    public Ability[] getAbilities() { return this.abilities; }
}
