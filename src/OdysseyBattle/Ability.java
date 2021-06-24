package OdysseyBattle;

public class Ability {
    private int baseDamage, scaling, type, cooldown, mana, vamp, currentCooldown = 0;
    private boolean aoe, immobilizing;
    private String name;

    public Ability(int baseDamage, int scaling, int type, int cooldown, int mana, int vamp, boolean aoe, boolean immobilizing, String name) {
        this.baseDamage = baseDamage;
        this.scaling = scaling;
        this.type = type; // AD: 1, AP: 2
        this.cooldown = cooldown + 1;
        this.mana = mana;
        this.vamp = vamp;
        this.aoe = aoe;
        this.immobilizing = immobilizing;
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
    public boolean isImmobilizing() { return this.immobilizing; }
    public String getName() { return this.name; }

    public void setCurrentCooldown(int cooldown, int type) { // 0: =, 1: -=
        if (type == 0) {
            this.currentCooldown = cooldown;
        } else if (type == 1) {
            this.currentCooldown -= cooldown;
        }
    }
}
