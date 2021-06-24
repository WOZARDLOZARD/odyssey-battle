package OdysseyBattle;

public class Manager {
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
