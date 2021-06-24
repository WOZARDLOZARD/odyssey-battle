package OdysseyBattle;

public class Text {
    private String text;

    public Text(String text) {
        this.text = "\u001B[0m" + text;
    }

    public String toString() {
        return text.replaceAll("<white>", "\u001B[0m").replaceAll("<red>", "\u001B[31m").replaceAll("<green>", "\u001B[32m").replaceAll("<blue>", "\u001B[34m").replaceAll("<cyan>", "\u001B[36m").replaceAll("<yellow>", "\u001B[33m").replaceAll("<purple>", "\u001B[35m");
    }
}
