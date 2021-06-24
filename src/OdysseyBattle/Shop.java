package OdysseyBattle;

public class Shop {
    private Item[] shop = new Item[11];

    public Shop() {
        shop[0] = new Item(1, 400, "Doran's Blade", "<yellow>Yasuo<white>, <green>Jinx", "<red>25 AD<white>, <green>80 Max HP<white>", true);
        shop[1] = new Item(2, 400, "Doran's Ring", "<red>Malphite", "<blue>35 AP<white>, <green>75 Max HP<white>", true);
        shop[2] = new Item(3, 400, "Doran's Shield", "All", "<yellow>15 Armor<white>, <cyan>15 MR<white>, <green>100 Max HP<white>", true);
        shop[3] = new Item(4, 1000, "Divine Sunderer", "<yellow>Yasuo", "<red>55 AD<white>, <red>60% Crit<white>, <green>200 Max HP<white>", true);
        shop[4] = new Item(5, 1000, "Duskblade", "<green>Jinx", "<red>90 AD<white>, <purple>40% Penetration<white>, <green>110 Max HP<white>", true);
        shop[5] = new Item(6, 1000, "Everfrost", "<red>Malphite", "<blue>100 AP<white>, <purple>25% Penetration<white>, <green>150 Max HP<white>", true);
        shop[6] = new Item(7, 650, "Berserker's Greaves", "<yellow>Yasuo<white>, <green>Jinx", "45 Speed, <red>35 AD<white>, <purple>5% Penetration<white>", true);
        shop[7] = new Item(8, 650, "Sorcerer's Shoes", "<red>Malphite", "50 Speed, <blue>45 AP<white>, <purple>8% Penetration<white>", true);
        shop[8] = new Item(9, 0, "Eye of the Herald", "All", "15% Speed, <green>30% Max HP<white>", false);
        shop[9] = new Item(10, 0, "Aspect of the Dragon", "All", "20% Speed, <red>20% Crit<white>", false);
        shop[10] = new Item(11, 0, "Hand of Baron", "All", "<red>15% AD<white>, <blue>15% AP<white>, <yellow>20% Armor<white>, <cyan>20% MR<white>", false);
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
