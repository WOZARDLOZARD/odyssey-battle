package OdysseyBattle;

public class Biotic {
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
