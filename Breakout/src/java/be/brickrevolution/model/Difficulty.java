package be.brickrevolution.model;

public class Difficulty {

    private int palletwidth;
    private double palletspeed;
    private int ballwidth;
    private double ballspeed;
    private int botskill;
    private int botresponciveness;
    private int modifierchance;
    private int chanceforpositive;

    public Difficulty(int palletwidth, double palletspeed, int ballwidth, double ballspeed, int botskill, int botresponciveness, int modifierchance, int chanceforpositive) {
        this.palletwidth = palletwidth;
        this.palletspeed = palletspeed;
        this.ballwidth = ballwidth;
        this.ballspeed = ballspeed;
        this.botskill = botskill;
        this.botresponciveness = botresponciveness;
        this.modifierchance = modifierchance;
        this.chanceforpositive = chanceforpositive;
    }

    public int getPalletwidth() {
        return palletwidth;
    }

    public double getPalletspeed() {
        return palletspeed;
    }

    public int getBallwidth() {
        return ballwidth;
    }

    public double getBallspeed() {
        return ballspeed;
    }

    public int getBotskill() {
        return botskill;
    }

    public int getBotresponciveness() {
        return botresponciveness;
    }

    public int getModifierchance() {
        return modifierchance;
    }

    public int getChanceforpositive() {
        return chanceforpositive;
    }
}
