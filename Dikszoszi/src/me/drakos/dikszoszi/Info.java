package me.drakos.dikszoszi;

public class Info {

    public static String getPrefix() {
        return "§bDikszoszi §8> ";
    }

    public static String getInfoPrefix() {
        return "§6§l!? ";
    }




    public enum getText {
        UNKNOWN("Ismeretlen");

        public final String text;

        getText(String text) {
            this.text = text;
        }
    }
}
