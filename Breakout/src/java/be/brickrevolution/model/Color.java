package be.brickrevolution.model;

public class Color {

    private String lighthex = "";
    private String middlehex = "";
    private String darkhex = "";

    public Color(int col) {
        if (col % 2 == 1) {
            addcollorhexlight();
        } else {
            addcollorhexdark();
        }
        if (col / 2 == 1 || col / 2 == 3) {
            addcollorhexlight();
        } else {
            addcollorhexdark();
        }
        if (col >= 4) {
            addcollorhexlight();
        } else {
            addcollorhexdark();
        }
    }

    private void addcollorhexlight() {
        lighthex += "f0";
        middlehex += "c6";
        darkhex += "9b";
    }

    private void addcollorhexdark() {
        lighthex += "00";
        middlehex += "0a";
        darkhex += "0e";
    }

    public String getLighthex() {
        return lighthex;
    }

    public String getMiddlehex() {
        return middlehex;
    }

    public String getDarkhex() {
        return darkhex;
    }
}
