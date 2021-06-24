package OdysseyBattle;

public class Buff {
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
