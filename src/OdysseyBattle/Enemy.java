package OdysseyBattle;

public class Enemy extends Biotic {
    private int[] hp;
    private int type; // AD: 1, AP: 2, Both: 3
    private int count, total;
    private int id; // Raptors: 1, Gromp: 2, Red: 3, Blue: 4, Infernal: 5, Mountain: 6, Ocean: 7, Kayn: 8
    private String name;
    private int reward;
    private boolean immobilized;

    public Enemy(int[] hp, int speed, int armor, int magicResist, int penetration, int attackDamage, int abilityPower, int type, int count, int id, String name, int reward) {
        super(speed, armor, magicResist, penetration, attackDamage, abilityPower);
        this.hp = hp;
        this.type = type;
        this.count = count;
        this.total = count;
        this.id = id;
        this.name = name;
        this.reward = reward;
        this.immobilized = false;
    }

    public void attack(Player target) {
        int random = randomNumber(1, 100);
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
    public int getType() { return this.type; }
    public boolean isImmobilized() { return this.immobilized; }

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
    public void setImmobilized(boolean immobilized) {
        this.immobilized = immobilized;
    }
}
