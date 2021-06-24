package OdysseyBattle;

public class Item {
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
    private boolean purchased, purchasable;

    public Item(int id, int cost, String name, String recommended, String stats, boolean purchasable) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.recommended = recommended;
        this.stats = stats;
        this.purchased = false;
        this.purchasable = purchasable;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getCost() {
        return this.cost;
    }
    public String getRecommended() { return this.recommended; }
    public String getStats() { return this.stats; }
    public boolean isPurchased() { return this.purchased; }
    public boolean isPurchasable() { return this.purchasable; }

    public void setPurchased(boolean purchased) { this.purchased = purchased; }
}
