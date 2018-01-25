package be.brickrevolution.model;

import be.brickrevolution.Playfield.IPlayfield;

public class Modifier {

    private double scoreamplifier;
    private double ballspeedamplifier;
    private double ballwidthamplifier;
    private double palletwidthamplifier;
    private int iconid;
    private boolean effect;
    private String specialability;
    private boolean executed;
    private int timeinticks;
    private int afectedPlayer;

    public Modifier(int timeinticks, double ballspeedamplifier, double ballwidthamplifier, double palletwidthamplifier, int iconid) {
        this.executed = false;
        this.timeinticks = (timeinticks);
        this.ballspeedamplifier = (ballspeedamplifier);
        this.ballwidthamplifier = (ballwidthamplifier);
        this.effect = (effect);
        this.palletwidthamplifier = (palletwidthamplifier);
        this.iconid = iconid;
    }

    //copyconstructor
    public Modifier(Modifier m) {
        this.executed = false;
        this.timeinticks = m.timeinticks;
        this.ballspeedamplifier = m.ballspeedamplifier;
        this.ballwidthamplifier = m.ballwidthamplifier;
        this.effect = m.effect;
        this.palletwidthamplifier = m.palletwidthamplifier;
        this.iconid = m.iconid;
    }

    public boolean getExecuted() {
        return this.executed;
    }

    public void execute(IPlayfield pf) {
        this.executed = true;
        this.afectedPlayer = pf.getPlayerthathitlast();
        pf.getBall().setSpeed(pf.getBall().getSpeed() * this.ballspeedamplifier);
        pf.getBall().setSize(pf.getBall().getSize() * this.ballwidthamplifier);

        if (this.afectedPlayer == 1) {
            pf.getBotumPallet().setWidth(pf.getBotumPallet().getWidth() * this.palletwidthamplifier);
        }
        if (this.afectedPlayer == 2) {
            pf.getTopPallet().setWidth(pf.getTopPallet().getWidth() * this.palletwidthamplifier);
        }

        updateplayfield(pf);
    }

    public void undo(IPlayfield pf) {
        pf.getBall().setSpeed(pf.getBall().getSpeed() / this.ballspeedamplifier);
        pf.getBall().setSize(pf.getBall().getSize() / this.ballwidthamplifier);

        if (this.afectedPlayer == 1) {
            pf.getBotumPallet().setWidth(pf.getBotumPallet().getWidth() / this.palletwidthamplifier);
        }
        if (this.afectedPlayer == 2) {
            pf.getTopPallet().setWidth(pf.getTopPallet().getWidth() / this.palletwidthamplifier);
        }
        updateplayfield(pf);
    }

    public void lowertimeinticks() {
        this.timeinticks--;
    }

    private void updateplayfield(IPlayfield pf) {
        pf.getG().reload();
    }

    /*
    *GETTERS AND SETTERS
     */
    public int getTimeinticks() {
        return timeinticks;
    }

    public int geticonid() {
        return this.iconid;
    }

}
